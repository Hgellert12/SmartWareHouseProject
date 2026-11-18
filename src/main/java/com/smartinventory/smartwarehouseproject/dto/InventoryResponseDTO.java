package com.smartinventory.smartwarehouseproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponseDTO
{


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
