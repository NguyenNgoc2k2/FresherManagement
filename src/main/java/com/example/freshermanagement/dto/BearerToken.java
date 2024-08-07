package com.example.freshermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BearerToken{
    private String accessToken;
    private String refreshToken;
}
