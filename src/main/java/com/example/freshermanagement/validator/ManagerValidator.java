package com.example.freshermanagement.validator;

import com.example.freshermanagement.entity.Manager;

public interface ManagerValidator extends UserValidator{
    void validateCreate(Manager manager);
}
