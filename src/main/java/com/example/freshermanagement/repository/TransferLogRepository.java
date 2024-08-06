package com.example.freshermanagement.repository;

import com.example.freshermanagement.entity.TransferLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferLogRepository extends JpaRepository<TransferLog, Long> {
}
