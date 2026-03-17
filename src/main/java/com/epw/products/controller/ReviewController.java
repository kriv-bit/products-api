package com.epw.products.controller;

import com.epw.products.dto.CreateReviewRequest;
import com.epw.products.dto.ReviewResponse;
import com.epw.products.entity.Product;
import com.epw.products.entity.Review;
import com.epw.products.exception.ResourceNotFoundException;
import com.epw.products.repository.ProductRepository;
import com.epw.products.repository.ReviewRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/reviews")
@Transactional
public class ReviewController {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ReviewController(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse create(
            @PathVariable Long productId,
            @Valid @RequestBody CreateReviewRequest request
    ) {
        Product product = findProduct(productId);

        Review review = new Review();
        review.setAuthorName(request.getAuthorName());
        review.setComment(request.getComment());
        review.setRating(request.getRating());
        review.setProduct(product);

        Review saved = reviewRepository.save(review);
        return toResponse(saved);
    }

    @GetMapping
    @Transactional(readOnly = true)
    public List<ReviewResponse> listByProduct(@PathVariable Long productId) {
        findProduct(productId);
        return reviewRepository.findAllByProductId(productId).stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{reviewId}")
    @Transactional(readOnly = true)
    public ReviewResponse getById(@PathVariable Long productId, @PathVariable Long reviewId) {
        findProduct(productId);
        Review review = findReview(productId, reviewId);
        return toResponse(review);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long productId, @PathVariable Long reviewId) {
        findProduct(productId);
        Review review = findReview(productId, reviewId);
        reviewRepository.delete(review);
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product " + productId + " not found"));
    }

    private Review findReview(Long productId, Long reviewId) {
        return reviewRepository.findByIdAndProductId(reviewId, productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Review " + reviewId + " not found for product " + productId
                ));
    }

    private ReviewResponse toResponse(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setId(review.getId());
        response.setAuthorName(review.getAuthorName());
        response.setComment(review.getComment());
        response.setRating(review.getRating());
        response.setCreatedAt(review.getCreatedAt());
        response.setProductId(review.getProduct().getId());
        return response;
    }
}
