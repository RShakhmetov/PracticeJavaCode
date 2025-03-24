package org.example.springmvcmapper.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull(message = "Product`s name must`t be empty")
    private String name;

    private String description;

    @PositiveOrZero(message = "Price can`t be negative")
    private double price;

    @PositiveOrZero(message = "Stock can`t be negative")
    private int quantityInStock;
}
