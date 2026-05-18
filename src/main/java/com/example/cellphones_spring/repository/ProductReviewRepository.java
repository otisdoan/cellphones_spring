package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
}
