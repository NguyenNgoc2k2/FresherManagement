package com.example.freshermanagement.service;

import com.example.freshermanagement.entity.ScoreStat;

import java.util.List;

public interface ScoreStatService {
    List<ScoreStat> getFresherCountByScore(String username);
}
