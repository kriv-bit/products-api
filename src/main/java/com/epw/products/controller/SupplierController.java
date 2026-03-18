package com.epw.products.controller;

import com.epw.products.dto.CreateSupplierRequest;
import com.epw.products.dto.SupplierResponse;
import com.epw.products.entity.Supplier;
import com.epw.products.repository.SupplierRepository;
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
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierRepository repository;

    public SupplierController(SupplierRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SupplierResponse> list() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierResponse create(@Valid @RequestBody CreateSupplierRequest request) {
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setContactEmail(request.getContactEmail());
        supplier.setPhone(request.getPhone());

        Supplier saved = repository.save(supplier);
        return toResponse(saved);
    }

    private SupplierResponse toResponse(Supplier supplier) {
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setContactEmail(supplier.getContactEmail());
        response.setPhone(supplier.getPhone());
        return response;
    }
}
