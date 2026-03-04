package com.epw.products.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

public class CreateProductRequest {

    @NotBlank(message = "name is required")
    @Size(max = 120, message = "name must be <= 120 chars")
    private String name;

    @Size(max = 1000, message = "description must be <= 1000 chars")
    private String description;

    @NotNull(message = "price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "price must be > 0")
    private BigDecimal price;

    @NotNull(message = "stock is required")
    @Min(value = 0, message = "stock must be >= 0")
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}