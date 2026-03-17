package com.epw.products.controller;

import com.epw.products.dto.BrandResponse;
import com.epw.products.dto.CreateBrandRequest;
import com.epw.products.entity.Brand;
import com.epw.products.repository.BrandRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandRepository repository;

    public BrandController(BrandRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandResponse create(@Valid @RequestBody CreateBrandRequest request) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setCountry(request.getCountry());

        Brand saved = repository.save(brand);
        return toResponse(saved);
    }

    @GetMapping
    public List<BrandResponse> list() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private BrandResponse toResponse(Brand brand) {
        BrandResponse response = new BrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());
        response.setCountry(brand.getCountry());
        return response;
    }
}
