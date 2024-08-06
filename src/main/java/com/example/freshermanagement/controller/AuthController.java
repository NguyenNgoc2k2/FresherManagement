package com.example.freshermanagement.controller;

import com.example.freshermanagement.dto.BearerToken;
import com.example.freshermanagement.dto.LoginRequest;
import com.example.freshermanagement.dto.TokenRefreshRequest;
import com.example.freshermanagement.security.jwt.TokenUtilJwt;
import com.example.freshermanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenUtilJwt tokenUtilJwt;

    @PostMapping("/tokens")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequestDTO){
        BearerToken response = authService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/tokens/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody TokenRefreshRequest tokenRefreshRequest){
        return ResponseEntity.ok(authService.refreshAccessToken(tokenRefreshRequest));
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(authService.getInfo(tokenUtilJwt.getUsernameFromToken(token.substring(7))));
    }
}
