package com.epw.products.service;

import com.epw.products.dto.CreateProductRequest;
import com.epw.products.dto.ProductResponse;
import com.epw.products.dto.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    ProductResponse create(CreateProductRequest request);

    List<ProductResponse> list();

    ProductResponse getById(Long id);

    ProductResponse update(Long id, UpdateProductRequest request);

    void delete(Long id);
}