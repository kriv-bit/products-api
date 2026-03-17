package com.epw.products.service.impl;

import com.epw.products.dto.CreateProductRequest;
import com.epw.products.dto.BrandResponse;
import com.epw.products.dto.ProductResponse;
import com.epw.products.dto.UpdateProductRequest;
import com.epw.products.entity.Brand;
import com.epw.products.entity.Product;
import com.epw.products.exception.ResourceNotFoundException;
import com.epw.products.repository.BrandRepository;
import com.epw.products.repository.ProductRepository;
import com.epw.products.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final BrandRepository brandRepository;

    public ProductServiceImpl(ProductRepository repository, BrandRepository brandRepository) {
        this.repository = repository;
        this.brandRepository = brandRepository;
    }

    @Override
    public ProductResponse create(CreateProductRequest request) {
        Product p = new Product();
        p.setName(request.getName());
        p.setDescription(request.getDescription());
        p.setPrice(request.getPrice());
        p.setStock(request.getStock());
        p.setBrand(findBrand(request.getBrandId()));

        Product saved = repository.save(p);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> list() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getById(Long id) {
        Product p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found"));
        return toResponse(p);
    }

    @Override
    public ProductResponse update(Long id, UpdateProductRequest request) {
        Product p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found"));

        p.setName(request.getName());
        p.setDescription(request.getDescription());
        p.setPrice(request.getPrice());
        p.setStock(request.getStock());
        p.setBrand(findBrand(request.getBrandId()));

        return toResponse(repository.save(p));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product " + id + " not found");
        }
        repository.deleteById(id);
    }

    private ProductResponse toResponse(Product p) {
        ProductResponse r = new ProductResponse();
        r.setId(p.getId());
        r.setName(p.getName());
        r.setDescription(p.getDescription());
        r.setPrice(p.getPrice());
        r.setStock(p.getStock());
        r.setCreatedAt(p.getCreatedAt());
        r.setBrand(toBrandResponse(p.getBrand()));
        return r;
    }

    private Brand findBrand(Long brandId) {
        if (brandId == null) {
            return null;
        }

        return brandRepository.findById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand " + brandId + " not found"));
    }

    private BrandResponse toBrandResponse(Brand brand) {
        if (brand == null) {
            return null;
        }

        BrandResponse response = new BrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());
        response.setCountry(brand.getCountry());
        return response;
    }
}
