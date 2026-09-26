package com.smartinventory.smartwarehouseproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String SKU;

    @NotBlank
    private String name;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    @LastModifiedDate
    private LocalDateTime lastPurchase;

    @NotBlank
    private String Supplier;


}
