package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.entity.Role;
import com.example.freshermanagement.entity.RoleName;
import com.example.freshermanagement.repository.RoleRepository;
import com.example.freshermanagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public boolean isAdmin() {
        return hasRole("ROLE_ADMIN");
    }

    @Override
    public boolean isManager() {
        return hasRole("ROLE_MANAGER");
    }

    @Override
    public Role findByName(RoleName name) {
        return roleRepository.findByRoleName(name);
    }

    private boolean hasRole(String role){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            return false;
        }

        for(GrantedAuthority authority : authentication.getAuthorities()){
            if(authority.getAuthority().equalsIgnoreCase(role)){
                return true;
            }
        }

        return false;
    }
}
