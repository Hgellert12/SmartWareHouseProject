package com.smartinventory.smartwarehouseproject.dto;

import jakarta.validation.constraints.NotBlank;

public class WarehouseRequestDTO {

    @NotBlank
    private String warehouseName;

    @NotBlank
    private String location;
}
