package com.smartinventory.smartwarehouseproject.dto;

import com.smartinventory.smartwarehouseproject.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO
{
    @NotBlank
    private String name;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    @NotBlank
    private String supplier;

    public ItemDTO itemToDTO(Item item)
    {
        ItemDTO response = new ItemDTO();
        response.setSupplier(item.getSupplier());
        response.setName(item.getName());
        response.setQuantity(item.getQuantity());
        response.setPrice(item.getPrice());
        return response;
    }
}
