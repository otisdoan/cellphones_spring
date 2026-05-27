package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySlug(String slug);

    Optional<Product> findBySlug(String slug);

    @Override
    @Query("""
                    SELECT DISTINCT p FROM Product p
                    LEFT JOIN FETCH p.images
                    ORDER BY p.id
            """)
    List<Product> findAll();

    @Override
    @EntityGraph(attributePaths = { "images" })
    Optional<Product> findById(Long id);
}
