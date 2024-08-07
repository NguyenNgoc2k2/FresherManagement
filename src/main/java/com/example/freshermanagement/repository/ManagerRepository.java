package com.example.freshermanagement.repository;

import com.example.freshermanagement.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByEmail(String email);
    Manager findByPhone(String phone);
    List<Manager> findAllByStatusTrue();
    Optional<Manager> findByIdAndStatusTrue(Long id);

    @Query("SELECT m.manager FROM Management m WHERE m.center.id = :centerId AND m.endDate IS NULL")
    Manager findManagerByCenterIdAndEndDateIsNull(@Param("centerId") Long centerId);
}
