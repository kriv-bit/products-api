package com.epw.products.repository;

import com.epw.products.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByProductId(Long productId);

    Optional<Review> findByIdAndProductId(Long id, Long productId);
}
