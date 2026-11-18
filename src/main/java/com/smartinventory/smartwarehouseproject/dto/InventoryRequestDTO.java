package com.smartinventory.smartwarehouseproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class InventoryRequestDTO
{
    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @NotNull
    private Integer itemID;

    @NotNull
    private Integer warehouseID;
}
