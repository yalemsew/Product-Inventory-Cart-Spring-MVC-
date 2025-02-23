package com.example.springmvc.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Product {
    @NotNull(message = "Product number is required")
    @Min(value = 1, message = "Product number must be greater than 0")
    private Integer productNumber;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 10, message = "Size must be between 2 to 10 characters")
    private String name;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
    private Double price;

    public Product() {}

    public Product(Integer productNumber, String name, Double price) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
