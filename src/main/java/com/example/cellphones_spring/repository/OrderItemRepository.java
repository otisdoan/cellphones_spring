package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
