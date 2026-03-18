package com.epw.products.repository;

import com.epw.products.entity.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, Long> {

    Optional<ProductSpecification> findByProductId(Long productId);

    boolean existsByProductId(Long productId);
}
