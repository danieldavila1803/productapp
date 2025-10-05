package com.example.productapp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("products")
public class Product {
    @Id
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
}