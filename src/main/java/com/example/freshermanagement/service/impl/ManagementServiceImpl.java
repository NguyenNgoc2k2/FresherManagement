package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.entity.Management;
import com.example.freshermanagement.repository.ManagementRepository;
import com.example.freshermanagement.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagementServiceImpl implements ManagementService {

    private final ManagementRepository managementRepository;

    @Override
    public boolean save(Management management) {
        managementRepository.save(management);
        return true;
    }

    @Override
    public boolean updateEndDateByCenterIdAndManagerId(Long centerId, Long managerId) {
        managementRepository.updateEndDateByCenterIdAndManagerId(centerId, managerId);
        return true;
    }

}
