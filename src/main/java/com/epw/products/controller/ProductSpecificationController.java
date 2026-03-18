package com.epw.products.controller;

import com.epw.products.dto.CreateProductSpecificationRequest;
import com.epw.products.dto.ProductSpecificationResponse;
import com.epw.products.entity.Product;
import com.epw.products.entity.ProductSpecification;
import com.epw.products.exception.ResourceNotFoundException;
import com.epw.products.repository.ProductRepository;
import com.epw.products.repository.ProductSpecificationRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products/{productId}/specification")
@Transactional
public class ProductSpecificationController {

    private final ProductRepository productRepository;
    private final ProductSpecificationRepository specificationRepository;

    public ProductSpecificationController(
            ProductRepository productRepository,
            ProductSpecificationRepository specificationRepository
    ) {
        this.productRepository = productRepository;
        this.specificationRepository = specificationRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductSpecificationResponse create(
            @PathVariable Long productId,
            @Valid @RequestBody CreateProductSpecificationRequest request
    ) {
        Product product = findProduct(productId);

        if (specificationRepository.existsByProductId(productId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Product " + productId + " already has a specification"
            );
        }

        ProductSpecification specification = new ProductSpecification();
        specification.setWeight(request.getWeight());
        specification.setColor(request.getColor());
        specification.setMaterial(request.getMaterial());
        specification.setDimensions(request.getDimensions());
        specification.setProduct(product);

        ProductSpecification saved = specificationRepository.save(specification);
        return toResponse(saved);
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ProductSpecificationResponse getByProduct(@PathVariable Long productId) {
        findProduct(productId);

        ProductSpecification specification = specificationRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Specification for product " + productId + " not found"
                ));

        return toResponse(specification);
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product " + productId + " not found"));
    }

    private ProductSpecificationResponse toResponse(ProductSpecification specification) {
        ProductSpecificationResponse response = new ProductSpecificationResponse();
        response.setId(specification.getId());
        response.setWeight(specification.getWeight());
        response.setColor(specification.getColor());
        response.setMaterial(specification.getMaterial());
        response.setDimensions(specification.getDimensions());
        response.setProductId(specification.getProduct().getId());
        return response;
    }
}
