package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
