package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
