package com.exercise.backend.controller;

import com.exercise.backend.crawler.Sight;
import com.exercise.backend.zoneName.ZoneConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class KeelungSightsService {
    @Autowired
    private KeelungSightsRepository repository;

    public void postSightsService(Sight sight){
        repository.insert(sight);
    }

    public List<Sight> getSightService(String zone){
        List<Sight> sights = null;
        for(int z = 0; z < ZoneConstant.KEELUNG_ZONES_ENGLISH.length; z++){
            String zoneChinese = ZoneConstant.KEELUNG_ZONES_ENGLISH[z];
            if(Objects.equals(zone, zoneChinese)){
                sights = repository.findByZone(ZoneConstant.KEELUNG_ZONES_CHINESE[z]);
                break;
            }
        }
        return sights;
    }
}