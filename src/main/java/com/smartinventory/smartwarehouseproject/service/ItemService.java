package com.smartinventory.smartwarehouseproject.service;

import com.smartinventory.smartwarehouseproject.dto.ItemRequestDTO;
import com.smartinventory.smartwarehouseproject.dto.ItemResponseDTO;
import com.smartinventory.smartwarehouseproject.entity.Item;
import com.smartinventory.smartwarehouseproject.repository.ItemRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ResponseEntity<Void> addNewItem(ItemRequestDTO item)
    {
        if (itemRepository.findBySku(item.getSku()) != null)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        itemRepository.save(ItemRequestDTO.itemRequestToItem(item));
        return ResponseEntity.ok().build();
    }

    public List<ItemResponseDTO> listAllItems()
    {
        return itemRepository.findAll().stream().filter(item -> item.getDeleteFlag()==false).map(item -> ItemResponseDTO.itemToResponseDTO(item)).toList();
    }

    public ResponseEntity<Void> deleteItem(Integer id)
    {
        if (id==null || id<1)
        {
            return ResponseEntity.badRequest().build();
        }
        Item itemToDelete = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (itemToDelete.getDeleteFlag()==true)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        itemToDelete.setDeleteFlag(true);
        itemToDelete.setLastModifiedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        itemRepository.save(itemToDelete);
        return ResponseEntity.ok().build();

    }

    public ItemResponseDTO findItemById(Integer id)
    {
        if (id == null || id<1)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return ItemResponseDTO.itemToResponseDTO(itemRepository.findById(id).filter(item -> item.getDeleteFlag()==false).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Transactional
    public ResponseEntity<Void> editExistingItem (Integer id, ItemRequestDTO newItem)
    {
        //validating
        if (id == null || id<1)
        {
            return ResponseEntity.badRequest().build();
        }
        if (newItem.getPrice() == null && newItem.getName() == null && newItem.getSupplier() == null && newItem.getSku() == null)
        {
            return ResponseEntity.badRequest().build();
        }
        if(newItem.getName() != null && newItem.getName().isBlank())
        {
            return  ResponseEntity.badRequest().build();
        }
        if(newItem.getSupplier() != null && newItem.getSupplier().isBlank())
        {
            return  ResponseEntity.badRequest().build();
        }
        if(newItem.getSku() != null && newItem.getSku().isBlank())
        {
            return  ResponseEntity.badRequest().build();
        }
        if(newItem.getPrice() != null && newItem.getPrice().compareTo(new BigDecimal("0.00")) <= 0)
        {
            return  ResponseEntity.badRequest().build();
        }

        Item oldItem = itemRepository.findById(id).filter(item -> item.getDeleteFlag() ==false).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(newItem.getSku() != null)
        {
            Item existingItem = itemRepository.findBySku(newItem.getSku());
            if (existingItem != null && !existingItem.getId().equals(id))
            {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        }
        //Applying validated changes
        if (newItem.getName() != null)
        {
            oldItem.setName(newItem.getName());
        }
        if (newItem.getSupplier() != null)
        {
            oldItem.setSupplier(newItem.getSupplier());
        }
        if(newItem.getPrice() != null)
        {
            oldItem.setPrice(newItem.getPrice());
        }
        if(newItem.getSku() != null)
        {
            oldItem.setSku(newItem.getSku());
        }
        oldItem.setLastModifiedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        itemRepository.save(oldItem);
        return ResponseEntity.ok().build();
    }

    public List<ItemResponseDTO> listAllDeletedItems()
    {
        return itemRepository.findAll().stream().filter(item -> item.getDeleteFlag()==true).map(item -> ItemResponseDTO.itemToResponseDTO(item)).toList();
    }
    public ResponseEntity<Void> reactivateItem(Integer id)
    {
        if (id==null || id<1)
        {
            return ResponseEntity.badRequest().build();
        }
        Item itemToDelete = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (itemToDelete.getDeleteFlag()==false)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        itemToDelete.setDeleteFlag(false);
        itemToDelete.setLastModifiedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        itemRepository.save(itemToDelete);
        return ResponseEntity.ok().build();
    }
}

