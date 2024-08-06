package com.example.freshermanagement.service;

import com.example.freshermanagement.entity.CenterStat;

import java.util.Date;
import java.util.List;

public interface CenterStatService {
    List<CenterStat> getFresherCountByCenterWithPeriod(Date statisticStartDate, Date statisticEndDate, String username);
    List<CenterStat> getFresherCountByCenterWithCurrentDate(String username);
}
