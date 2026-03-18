package com.epw.products.dto;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CreateProductSpecificationRequest {

    private BigDecimal weight;

    @Size(max = 50, message = "color must be <= 50 chars")
    private String color;

    @Size(max = 100, message = "material must be <= 100 chars")
    private String material;

    @Size(max = 150, message = "dimensions must be <= 150 chars")
    private String dimensions;

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
}
