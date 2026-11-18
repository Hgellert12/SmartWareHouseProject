package com.smartinventory.smartwarehouseproject.controller;

import com.smartinventory.smartwarehouseproject.dto.ItemRequestDTO;
import com.smartinventory.smartwarehouseproject.dto.ItemResponseDTO;
import com.smartinventory.smartwarehouseproject.entity.Item;
import com.smartinventory.smartwarehouseproject.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Void> addNewItem(@Valid @RequestBody ItemRequestDTO item) {
        return itemService.addNewItem(item);
    }

    @GetMapping
    public List<ItemResponseDTO> getAllItem()
    {
        return itemService.listAllItems();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@RequestHeader Integer id) {
        return itemService.deleteItem(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editItem(@RequestHeader Integer id, @RequestBody Item item)
    {
        return itemService.editExistingItem(id, item);
    }
    @GetMapping("/{id}")
    public ItemResponseDTO findItemById(@RequestHeader Integer id)
    {
        return itemService.findItemById(id);
    }


}
