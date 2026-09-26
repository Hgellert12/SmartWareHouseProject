package com.smartinventory.smartwarehouseproject.repository;

import com.smartinventory.smartwarehouseproject.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
