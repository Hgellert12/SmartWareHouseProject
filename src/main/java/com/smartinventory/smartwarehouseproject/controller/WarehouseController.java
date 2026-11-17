package com.smartinventory.smartwarehouseproject.controller;

import com.smartinventory.smartwarehouseproject.entity.Item;
import com.smartinventory.smartwarehouseproject.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Void> addNewItem(@Valid @RequestBody Item item) {
        return warehouseService.addNewItem(item);
    }

    @GetMapping
    public List<Item> getAllItem()
    {
        return warehouseService.listAllItems();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@RequestHeader Integer id) {
        return warehouseService.deleteItem(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editItem(@RequestHeader Integer id, @RequestBody Item item)
    {
        return warehouseService.editExistingItem(id, item);
    }

}
