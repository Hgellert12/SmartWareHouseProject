package com.smartinventory.smartwarehouseproject.service;

import com.smartinventory.smartwarehouseproject.entity.Item;
import com.smartinventory.smartwarehouseproject.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WarehouseService {

    @Autowired
    private ItemRepository itemRepository;

    public Item addNewItem(Item item)
    {
        return itemRepository.save(item);
    }

    public List<Item> listAllItems()
    {
        return itemRepository.findAll();
    }

    public  Boolean deleteItem(Long id)
    {
        itemRepository.deleteById(id);
        if(itemRepository.findAllById(List.of(id)).isEmpty())
        {
            return true;
        }
        return false;
    }

    public Item findItemById(Long id)
    {
        return itemRepository.findById(id).orElse(null);
    }

    public Item editExistingItem (Long id, Item newitem)
    {
        Item item = findItemById(id);
        if(item != null)
        {
            if(newitem.getSupplier() != null)
            {
                item.setSupplier(newitem.getSupplier());
            }
            if(newitem.getName() != null)
            {
                item.setName(newitem.getName());
            }
            if(newitem.getPrice() != null)
            {
                item.setPrice(newitem.getPrice());
            }
            if(newitem.getQuantity() != null)
            {
                item.setQuantity(newitem.getQuantity());
            }
            if (newitem.getSKU() != null)
            {
                item.setSKU(newitem.getSKU());
            }
            return itemRepository.save(item);
        }
        return item;
    }

}
