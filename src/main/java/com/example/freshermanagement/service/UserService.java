package com.example.freshermanagement.service;

import com.example.freshermanagement.entity.User;

public interface UserService<T extends User>{
    T getActiveUserById(Long id);
    boolean save(T user);
}
