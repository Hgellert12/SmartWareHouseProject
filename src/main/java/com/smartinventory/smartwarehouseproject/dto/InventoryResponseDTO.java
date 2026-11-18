package com.smartinventory.smartwarehouseproject.dto;

import com.smartinventory.smartwarehouseproject.entity.Inventory;
import com.smartinventory.smartwarehouseproject.repository.ItemRepository;
import com.smartinventory.smartwarehouseproject.repository.WarehouseRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class InventoryResponseDTO
{
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @NotNull
    private Integer itemID;

    @NotNull
    private Integer warehouseID;

    private String warehouseName;

    private String itemName;


}
