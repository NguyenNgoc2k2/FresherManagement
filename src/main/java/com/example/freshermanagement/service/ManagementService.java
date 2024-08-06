package com.example.freshermanagement.service;

import com.example.freshermanagement.entity.Management;

public interface ManagementService {
    boolean save(Management management);
    boolean updateEndDateByCenterIdAndManagerId(Long centerId, Long managerId);
}
