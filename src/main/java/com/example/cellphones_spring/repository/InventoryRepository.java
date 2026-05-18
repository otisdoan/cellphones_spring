package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
