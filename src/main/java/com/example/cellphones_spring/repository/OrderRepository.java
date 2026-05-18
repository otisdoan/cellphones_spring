package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
