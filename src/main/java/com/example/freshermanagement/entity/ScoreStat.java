package com.example.freshermanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScoreStat {
    private String scoreRange;
    private long fresherCount;
}
