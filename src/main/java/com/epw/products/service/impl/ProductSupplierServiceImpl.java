package com.epw.products.service.impl;

import com.epw.products.dto.ProductSupplierResponse;
import com.epw.products.dto.SupplierResponse;
import com.epw.products.entity.Product;
import com.epw.products.entity.Supplier;
import com.epw.products.exception.ResourceNotFoundException;
import com.epw.products.repository.ProductRepository;
import com.epw.products.repository.SupplierRepository;
import com.epw.products.service.ProductSupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductSupplierServiceImpl implements ProductSupplierService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductSupplierServiceImpl(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public ProductSupplierResponse assignSupplier(Long productId, Long supplierId) {
        Product product = findProduct(productId);
        Supplier supplier = findSupplier(supplierId);

        product.getSuppliers().add(supplier);
        supplier.getProducts().add(product);

        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    @Override
    public ProductSupplierResponse removeSupplier(Long productId, Long supplierId) {
        Product product = findProduct(productId);
        Supplier supplier = findSupplier(supplierId);

        product.getSuppliers().remove(supplier);
        supplier.getProducts().remove(product);

        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product " + productId + " not found"));
    }

    private Supplier findSupplier(Long supplierId) {
        return supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier " + supplierId + " not found"));
    }

    private ProductSupplierResponse toResponse(Product product) {
        ProductSupplierResponse response = new ProductSupplierResponse();
        response.setProductId(product.getId());
        response.setProductName(product.getName());
        response.setSuppliers(product.getSuppliers().stream()
                .map(this::toSupplierResponse)
                .toList());
        return response;
    }

    private SupplierResponse toSupplierResponse(Supplier supplier) {
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setContactEmail(supplier.getContactEmail());
        response.setPhone(supplier.getPhone());
        return response;
    }
}
