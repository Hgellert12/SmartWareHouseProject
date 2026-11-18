package com.smartinventory.smartwarehouseproject.dto;

import com.smartinventory.smartwarehouseproject.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemResponseDTO
{
    private Integer id;
    private String sku;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String supplier;

    public ItemResponseDTO itemToResponseDTO(Item item)
    {
        ItemResponseDTO response = new ItemResponseDTO();
        response.setId(item.getId());
        response.setSku(item.getSku());
        response.setSupplier(item.getSupplier());
        response.setName(item.getName());
        response.setQuantity(item.getQuantity());
        response.setPrice(item.getPrice());
        return response;
    }
}
