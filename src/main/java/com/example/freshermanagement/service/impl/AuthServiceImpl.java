package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.dto.BearerToken;
import com.example.freshermanagement.dto.LoginRequest;
import com.example.freshermanagement.dto.TokenRefreshRequest;
import com.example.freshermanagement.entity.Admin;
import com.example.freshermanagement.entity.Fresher;
import com.example.freshermanagement.entity.Manager;
import com.example.freshermanagement.entity.User;
import com.example.freshermanagement.exception.ValidationException;
import com.example.freshermanagement.repository.UserRepository;
import com.example.freshermanagement.security.CustomUserDetails;
import com.example.freshermanagement.security.CustomUserDetailsService;
import com.example.freshermanagement.security.jwt.TokenUtilJwt;
import com.example.freshermanagement.service.AuthService;
import com.example.freshermanagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final TokenUtilJwt tokenUtilJwt;
    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleCheckService;
    private final UserRepository userRepository;

    @Override
    public BearerToken login(LoginRequest loginRequest) {
        authenticate(loginRequest);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        checkDelete(userDetails);

        final String accessToken = tokenUtilJwt.generateToken(userDetails);
        final String refreshToken = tokenUtilJwt.generateRefreshToken(userDetails);
        return new BearerToken(accessToken, refreshToken);
    }

    private void authenticate(LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            log.error("Incorrect username or password");
            throw new BadCredentialsException("Incorrect username or password");
        }
    }

    private void checkDelete(UserDetails userDetails){
        CustomUserDetails customUserDetails;
        if (userDetails instanceof CustomUserDetails) {
            customUserDetails = (CustomUserDetails) userDetails;
            if (!customUserDetails.getStatus()) {
                log.error("User account has been deleted");
                throw new ValidationException("User account has been deleted");
            }
            return;
        }
        log.error("Invalid user details");
        throw new ValidationException("Invalid user details");
    }

    @Override
    public BearerToken refreshAccessToken(TokenRefreshRequest tokenRefreshRequest) {
        String refreshToken = tokenRefreshRequest.getRefreshToken();
        String newAccessToken =  tokenUtilJwt.generateAccessTokenFromRefreshToken(refreshToken);
        return new BearerToken(newAccessToken, refreshToken);
    }

    @Override
    public User getInfo(String username) {
        if(roleCheckService.isAdmin()){
            return (Admin) userRepository.findByUsername(username);
        }
        if(roleCheckService.isManager()){
            return (Manager) userRepository.findByUsername(username);
        }
        return (Fresher) userRepository.findByUsername(username);
    }

}
