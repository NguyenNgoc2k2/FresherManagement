package com.example.freshermanagement.validator;

import com.example.freshermanagement.entity.Fresher;

public interface FresherValidator extends UserValidator{
    void validateCode(String code);
    void validateCreate(Fresher createFresher);
    void validateUpdate(Fresher updateFresher, Fresher currentFresher);
}
