package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.entity.CenterStat;
import com.example.freshermanagement.repository.CenterRepository;
import com.example.freshermanagement.service.CenterStatService;
import com.example.freshermanagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterStatServiceImpl implements CenterStatService {

    private final CenterRepository centerRepository;

    private final RoleService roleCheckService;

    @Override
    public List<CenterStat> getFresherCountByCenterWithPeriod(Date statisticStartDate, Date statisticEndDate, String username) {
        if(roleCheckService.isAdmin()){
            return centerRepository.getFresherCountByCenterWithPeriod(statisticStartDate, statisticEndDate);
        }
        if(roleCheckService.isManager()){
            return centerRepository.getFresherCountByCenterWithPeriodAndManagerUsername(statisticStartDate, statisticEndDate, username);
        }
        return null;
    }

    @Override
    public List<CenterStat> getFresherCountByCenterWithCurrentDate(String username) {
        if(roleCheckService.isAdmin()){
            return centerRepository.getFresherCountByCenterWithCurrentDate();
        }
        if(roleCheckService.isManager()){
            return centerRepository.getFresherCountByCenterWithCurrentDateAndManagerUsername(username);
        }
        return null;
    }

}
