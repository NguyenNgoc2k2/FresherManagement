package com.example.freshermanagement.repository;

import com.example.freshermanagement.entity.Role;
import com.example.freshermanagement.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleName roleName);
}
