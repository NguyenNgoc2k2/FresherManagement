package com.example.freshermanagement.service;

import com.example.freshermanagement.entity.Admin;

public interface AdminService extends UserService<Admin> {
    void saveDefault();
}
