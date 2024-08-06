package com.example.freshermanagement.service;

import com.example.freshermanagement.entity.Manager;

import java.util.List;

public interface ManagerService extends UserService<Manager>{
    Manager getCurrentManagerByCenterId(Long centerId);
    List<Manager> findAll();
}
