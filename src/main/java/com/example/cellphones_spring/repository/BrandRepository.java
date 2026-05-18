package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsBySlug(String slug);
}
