package com.example.freshermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CenterRequest {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Long managerId;
    private Long areaId;
}
