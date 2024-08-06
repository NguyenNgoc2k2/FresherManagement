package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.entity.Manager;
import com.example.freshermanagement.entity.Role;
import com.example.freshermanagement.entity.RoleName;
import com.example.freshermanagement.exception.ResourceNotFoundException;
import com.example.freshermanagement.repository.ManagerRepository;
import com.example.freshermanagement.service.ManagerService;
import com.example.freshermanagement.service.RoleService;
import com.example.freshermanagement.validator.ManagerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ManagerValidator managerValidator;

    @Override
    public boolean save(Manager manager) {
        managerValidator.validateCreate(manager);

        manager.setPassword(passwordEncoder.encode(manager.getPassword()));

        Role role = roleService.findByName(RoleName.MANAGER);
        manager.setRoles(Collections.singletonList(role));

        managerRepository.save(manager);
        return true;
    }


    @Override
    public Manager getActiveUserById(Long id) {
        return managerRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> {
                    log.error("Manager not found with id: " + id);
                    return new ResourceNotFoundException("Manager not found with id: " + id);
                });
    }

    @Override
    public List<Manager> findAll() {
        return managerRepository.findAllByStatusTrue();
    }

    @Override
    public Manager getCurrentManagerByCenterId(Long centerId) {
        return managerRepository.findManagerByCenterIdAndEndDateIsNull(centerId);
    }
}
