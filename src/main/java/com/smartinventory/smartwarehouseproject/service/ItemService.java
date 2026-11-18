package com.smartinventory.smartwarehouseproject.service;

import com.smartinventory.smartwarehouseproject.dto.ItemRequestDTO;
import com.smartinventory.smartwarehouseproject.dto.ItemResponseDTO;
import com.smartinventory.smartwarehouseproject.entity.Item;
import com.smartinventory.smartwarehouseproject.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ResponseEntity<Void> addNewItem(ItemRequestDTO item)
    {
        if (itemRepository.FindBySku(item.getSku()) != null)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        itemRepository.save(ItemRequestDTO.itemRequestToItem(item));
        return ResponseEntity.ok().build();
    }

    public List<ItemResponseDTO> listAllItems()
    {
        return itemRepository.findAll().stream().map(item -> ItemResponseDTO.itemToResponseDTO(item)).toList();
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

        return ItemResponseDTO.itemToResponseDTO(itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public ResponseEntity<Void> editExistingItem (Integer id, ItemRequestDTO newItem)
    {
        if (id == null || id<1)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Item oldItem = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!newItem.getName().isBlank())
        {
            oldItem.setName(newItem.getName());
        }
        if (!newItem.getSupplier().isBlank())
        {
            oldItem.setSupplier(newItem.getSupplier());
        }
        if(newItem.getPrice() != null)
        {
            oldItem.setPrice(newItem.getPrice());
        }
        if(!newItem.getSku().isBlank())
        {
            oldItem.setSku(newItem.getSku());
        }
        oldItem.setLastModifiedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        itemRepository.save(oldItem);
        return ResponseEntity.ok().build();
    }
}
