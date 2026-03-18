package com.epw.products.dto;

import java.util.List;

public class ProductSupplierResponse {

    private Long productId;
    private String productName;
    private List<SupplierResponse> suppliers;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<SupplierResponse> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierResponse> suppliers) {
        this.suppliers = suppliers;
    }
}
