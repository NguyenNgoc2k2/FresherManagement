package com.example.freshermanagement.controller;

import com.example.freshermanagement.security.jwt.TokenUtilJwt;
import com.example.freshermanagement.service.CenterStatService;
import com.example.freshermanagement.service.ScoreStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats/")
public class StatController {

    private final CenterStatService centerStatService;
    private final ScoreStatService scoreStatService;

    private final TokenUtilJwt tokenUtilJwt;

    @GetMapping("/fresher-center/filter")
    public ResponseEntity<?> getFresherCountByCenterWithPeriod(@RequestParam("startTime")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                               @RequestParam("endTime")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                               @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(centerStatService.getFresherCountByCenterWithPeriod(startDate, endDate, getUsernameByToken(token.substring(7))));
    }

    @GetMapping("/fresher-center")
    public ResponseEntity<?> getFresherCountByCenter(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(centerStatService.getFresherCountByCenterWithCurrentDate(getUsernameByToken(token.substring(7))));
    }

    @GetMapping("/fresher-score")
    public ResponseEntity<?> getFresherCountByScore(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(scoreStatService.getFresherCountByScore(getUsernameByToken(token.substring(7))));
    }

    private String getUsernameByToken(String token){
        return tokenUtilJwt.getUsernameFromToken(token);
    }
}
