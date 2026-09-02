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
    private static final WikiSearch WIKI_SEARCH = new WikiSearch();

    public void postSightsService(Sight sight) {
        repository.insert(sight);
    }

    public List<Sight> getSightService(String zone) {
        List<Sight> sights = null;
        for (int z = 0; z < ZoneConstant.KEELUNG_ZONES_ENGLISH.length; z++) {
            String zoneEnglish = ZoneConstant.KEELUNG_ZONES_ENGLISH[z];
            if (Objects.equals(zone, zoneEnglish)) {
                sights = repository.findByZone(ZoneConstant.KEELUNG_ZONES_CHINESE[z]);
                break;
            }
        }
        return sights;
    }

    // update fallback photos of sights
    // tester
    public List<Sight> updateFPsOfSights(String zone) {
        List<Sight> sights = null;
        for (int z = 0; z < ZoneConstant.KEELUNG_ZONES_ENGLISH.length; z++) {
            String zoneEnglish = ZoneConstant.KEELUNG_ZONES_ENGLISH[z];
            if (Objects.equals(zone, zoneEnglish)) {
                sights = repository.findByZone(ZoneConstant.KEELUNG_ZONES_CHINESE[z]);
                for (Sight sight : sights) {
                    try {
                        String fallbackPhoto = WIKI_SEARCH.searchPhotosByKeyword(sight.getSightName());
                        sight.setFallbackPhoto(fallbackPhoto);
                        repository.save(sight);
                    } catch (Exception e) {
                        System.err.println("updateFPsOfSights Exception:");
                        System.err.println(e);
                    }
                }
                break;
            }
        }
        return sights;
    }

    // update fallback photos of sights
    public void updateAllFPsOfSights() {
        for (int z = 0; z < ZoneConstant.KEELUNG_ZONES_CHINESE.length; z++) {
            List<Sight> sights = repository.findByZone(ZoneConstant.KEELUNG_ZONES_CHINESE[z]);
            for (Sight sight : sights) {
                try {
                    String fallbackPhoto = WIKI_SEARCH.searchPhotosByKeyword(sight.getSightName());
                    sight.setFallbackPhoto(fallbackPhoto);
                    repository.save(sight);
                } catch (Exception e) {
                    System.err.println("updateFPsOfSights Exception:");
                    System.err.println(e);
                }
            }
        }
    }
}