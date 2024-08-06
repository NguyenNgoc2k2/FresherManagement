package com.example.freshermanagement.validator;

import com.example.freshermanagement.dto.CenterRequest;
import com.example.freshermanagement.entity.Center;

public interface CenterValidator {
    void validateName(String name);
    void validateEmail(String email);
    void validatePhone(String phone);
    void validateCreate(CenterRequest createRequest);
    void validateUpdate(CenterRequest updateCenter, Center currentCenter);
}
