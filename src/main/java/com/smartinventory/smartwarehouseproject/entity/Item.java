package com.smartinventory.smartwarehouseproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotBlank
    private String sku;

    @NotBlank
    private String name;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal price;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @NotBlank
    private String supplier;

    @NotNull
    private Boolean deleteFlag;


}
