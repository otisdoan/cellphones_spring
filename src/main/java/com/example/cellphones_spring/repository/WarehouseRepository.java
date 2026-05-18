package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
