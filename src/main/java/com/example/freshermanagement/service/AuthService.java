package com.example.freshermanagement.service;

import com.example.freshermanagement.dto.BearerToken;
import com.example.freshermanagement.dto.LoginRequest;
import com.example.freshermanagement.dto.TokenRefreshRequest;
import com.example.freshermanagement.entity.User;

public interface AuthService {
    BearerToken login(LoginRequest loginRequestDTO);
    BearerToken refreshAccessToken(TokenRefreshRequest tokenRefreshRequest);
    User getInfo(String username);
}
