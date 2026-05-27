package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.dto.response.ProductVariantResponse;
import com.example.cellphones_spring.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    @Query("""
        SELECT DISTINCT pv.capacity
        FROM ProductVariant pv
        JOIN pv.product p
        WHERE p.groupName = :group_name
""")
    Optional<List<String>> findCapacityByGroupName(@Param("group_name") String group_name);

    @Query("""
        SELECT pv
        FROM ProductVariant pv
        JOIN pv.product p
        WHERE pv.capacity = :capacity AND p.groupName = :group_name
""")
    Optional<List<ProductVariant>> findVariantByCapacity(@Param("capacity") String capacity, @Param("group_name") String group_name);
}
