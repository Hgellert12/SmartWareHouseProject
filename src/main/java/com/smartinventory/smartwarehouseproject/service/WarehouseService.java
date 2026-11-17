package com.smartinventory.smartwarehouseproject.service;

import com.smartinventory.smartwarehouseproject.dto.ItemDTO;
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
public class WarehouseService {

    @Autowired
    private ItemRepository itemRepository;

    public ResponseEntity<Void> addNewItem(Item item)
    {
        if (itemRepository.findBySKU(item.getSKU()) != null)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        itemRepository.save(item);
        return ResponseEntity.ok().build();
    }

    public List<ItemDTO> listAllItems()
    {
        return itemRepository.findAll().stream().map(new ItemDTO()::itemToDTO).toList();
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

    public ItemDTO findItemById(Integer id)
    {
        if (id == null || id<1)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return new ItemDTO().itemToDTO(itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public ResponseEntity<Void> editExistingItem (Integer id, Item newitem)
    {
        if (id == null || id<1)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Item oldItem = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!newitem.getName().isBlank())
        {
            oldItem.setName(newitem.getName());
        }
        if (newitem.getDeleteFlag() == true && oldItem.getDeleteFlag() == true)
        {
            oldItem.setDeleteFlag(false);
        }
        if (!newitem.getSupplier().isBlank())
        {
            oldItem.setSupplier(newitem.getSupplier());
        }
        if(newitem.getPrice() != null && newitem.getPrice()>0)
        {
            oldItem.setPrice(newitem.getPrice());
        }
        if(newitem.getQuantity() != null && newitem.getQuantity()>0)
        {
            oldItem.setQuantity(newitem.getQuantity());
        }
        oldItem.setLastModifiedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        itemRepository.save(oldItem);
        return ResponseEntity.ok().build();
    }
}
