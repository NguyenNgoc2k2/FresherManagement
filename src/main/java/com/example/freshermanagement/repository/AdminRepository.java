package com.example.freshermanagement.repository;

import com.example.freshermanagement.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByIdAndStatusTrue(Long id);
}
