package com.smartinventory.smartwarehouseproject.repository;

import com.smartinventory.smartwarehouseproject.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
