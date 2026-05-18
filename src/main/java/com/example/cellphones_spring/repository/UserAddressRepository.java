package com.example.cellphones_spring.repository;

import com.example.cellphones_spring.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
