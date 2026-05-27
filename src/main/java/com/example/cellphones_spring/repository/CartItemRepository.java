package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.CartItem;
import com.example.cellphones_spring.entity.Product;
import com.example.cellphones_spring.entity.ProductVariant;
import com.example.cellphones_spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByUserAndProductAndVariant(User user, Product product, ProductVariant variant);
    List<CartItem> findByUserId(Long userId);
}
