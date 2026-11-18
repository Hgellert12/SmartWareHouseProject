package com.smartinventory.smartwarehouseproject.repository;

import com.smartinventory.smartwarehouseproject.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}
