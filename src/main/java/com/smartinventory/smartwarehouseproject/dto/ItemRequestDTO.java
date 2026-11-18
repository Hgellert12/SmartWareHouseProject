package com.smartinventory.smartwarehouseproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ItemRequestDTO
{
    @NotBlank
    private String name;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal price;

    @NotBlank
    private String supplier;

    @NotBlank
    private String sku;


}

