package com.smartinventory.smartwarehouseproject.controller;

import com.smartinventory.smartwarehouseproject.entity.Item;
import com.smartinventory.smartwarehouseproject.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public Item addNewItem(@RequestBody Item item) {
        return warehouseService.addNewItem(item);
    }

    @GetMapping
    public List<Item> getAllItem()
    {
        return warehouseService.listAllItems();
    }

    @DeleteMapping("/{id}")
    public Boolean deleteItem(@RequestHeader Long id) {
        return warehouseService.deleteItem(id);
    }

    @PutMapping("/{id}")
    public Item editItem(@RequestHeader Long id, @RequestBody Item item)
    {
        return warehouseService.editExistingItem(id, item);
    }

}
