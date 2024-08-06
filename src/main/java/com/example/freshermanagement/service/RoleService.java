package com.example.freshermanagement.service;

import com.example.freshermanagement.entity.Role;
import com.example.freshermanagement.entity.RoleName;

public interface RoleService {
    boolean isAdmin();
    boolean isManager();
    Role findByName(RoleName name);
}
