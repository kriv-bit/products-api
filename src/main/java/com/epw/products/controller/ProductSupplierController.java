package com.epw.products.controller;

import com.epw.products.dto.ProductSupplierResponse;
import com.epw.products.service.ProductSupplierService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/{productId}/suppliers/{supplierId}")
public class ProductSupplierController {

    private final ProductSupplierService service;

    public ProductSupplierController(ProductSupplierService service) {
        this.service = service;
    }

    @PostMapping
    public ProductSupplierResponse assignSupplier(@PathVariable Long productId, @PathVariable Long supplierId) {
        return service.assignSupplier(productId, supplierId);
    }

    @DeleteMapping
    public ProductSupplierResponse removeSupplier(@PathVariable Long productId, @PathVariable Long supplierId) {
        return service.removeSupplier(productId, supplierId);
    }
}
