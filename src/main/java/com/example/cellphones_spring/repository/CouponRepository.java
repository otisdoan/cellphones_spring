package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
