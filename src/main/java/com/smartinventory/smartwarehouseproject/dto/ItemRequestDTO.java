package com.smartinventory.smartwarehouseproject.dto;

import com.smartinventory.smartwarehouseproject.entity.Item;
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
    @Positive
    private BigDecimal price;

    @NotBlank
    private String supplier;

    @NotBlank
    private String sku;

    public static Item itemRequestToItem(ItemRequestDTO itemRequest)
    {
        Item newItem = new Item();
        newItem.setName(itemRequest.getName());
        newItem.setPrice(itemRequest.getPrice());
        newItem.setSupplier(itemRequest.getSupplier());
        newItem.setSku(itemRequest.getSku());
        newItem.setDeleteFlag(false);
        return newItem;

    }

}

