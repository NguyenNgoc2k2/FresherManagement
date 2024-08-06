package com.example.freshermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.freshermanagement.entity.ScoreStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

@SpringBootTest
public class ScoreStatServiceTest {

    @Autowired
    private ScoreStatService scoreStatService;

    @Test
    @WithMockUser(username = "NGUYENNGOC", roles = {"ADMIN"})
    void getFresherCountByScoreWithAdmin(){
        List<ScoreStat> scoreStats = scoreStatService.getFresherCountByScore("nguyenngoc");

        assertEquals(new ScoreStat("5-8", 1L), scoreStats.get(0));
        assertEquals(new ScoreStat("0-4", 0L), scoreStats.get(1));
        assertEquals(new ScoreStat("3-7", 0L), scoreStats.get(2));
        assertEquals(new ScoreStat("8-9", 0L), scoreStats.get(3));
        assertEquals(new ScoreStat("9-10", 0L), scoreStats.get(4));
    }

    @Test
    @WithMockUser(username = "TRINHHANH", roles = {"MANAGER"})
    void getFresherCountByScoreWithManager(){
        List<ScoreStat> scoreStats = scoreStatService.getFresherCountByScore("trinhhanh");

        assertEquals(new ScoreStat("5-8", 0L), scoreStats.get(0));
        assertEquals(new ScoreStat("0-4", 0L), scoreStats.get(1));
        assertEquals(new ScoreStat("3-7", 0L), scoreStats.get(2));
        assertEquals(new ScoreStat("8-9", 0L), scoreStats.get(3));
        assertEquals(new ScoreStat("9-10", 0L), scoreStats.get(4));
    }
}
