package com.smartinventory.smartwarehouseproject.dto;

import jakarta.validation.constraints.*;
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
    @PositiveOrZero
    private Integer quantity;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    private String supplier;

    @NotBlank
    private String sku;


}

