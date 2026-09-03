package com.exercise.backend.controller;

import com.exercise.backend.crawler.Sight;
import com.exercise.backend.zoneName.ZoneConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class KeelungSightsService {
    @Autowired
    private KeelungSightsRepository repository;
    private static final WikiSearch WIKI_SEARCH = new WikiSearch();
    @Autowired
    private MongoTemplate mongoTemplate;

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
    public List<Sight> updateFPsOfSights(String zone) throws IOException, InterruptedException {
        List<Sight> sights;
        for (int z = 0; z < ZoneConstant.KEELUNG_ZONES_ENGLISH.length; z++) {
            String zoneEnglish = ZoneConstant.KEELUNG_ZONES_ENGLISH[z];
            if (Objects.equals(zone, zoneEnglish)) {
                sights = repository.findByZone(ZoneConstant.KEELUNG_ZONES_CHINESE[z]);
                for (Sight sight : sights) {
                    System.out.println("Update "+sight.getSightName()+" ...");
                    try {
                        String fallbackPhoto = WIKI_SEARCH.searchPhotosByKeyword(sight.getSightName());
                        Query query = new Query(Criteria.where("sightName").is(sight.getSightName()));
                        Update update = new Update().set("fallbackPhoto", fallbackPhoto);
                        mongoTemplate.updateFirst(query, update, Sight.class);
                        /*
                        * 原本的話用repository.save(entity)就好，但是沒有設定@Id的緣故，
                        * 沒辦法正確覆蓋，就只能用mongoTemplate應付一下了。*/
                        Thread.sleep(3000);
                    } catch (InterruptedException | IOException e) {
                        System.err.println("updateFPsOfSights InterruptedException | IOException:");
                        System.err.println(e);
                        Thread.sleep(3000);
                        throw e;
                    } catch (Exception e) {
                        System.err.println("updateFPsOfSights Exception:");
                        System.err.println(e);
                    }
                }
                break;
            }
        }
        return repository.findByZone(zone);
    }

    // update fallback photos of sights
    public void updateAllFPsOfSights() {
        for (int z = 0; z < ZoneConstant.KEELUNG_ZONES_CHINESE.length; z++) {
            List<Sight> sights = repository.findByZone(ZoneConstant.KEELUNG_ZONES_CHINESE[z]);
            for (Sight sight : sights) {
                System.out.println("Update "+sight.getSightName()+" ...");
                try {
                    String fallbackPhoto = WIKI_SEARCH.searchPhotosByKeyword(sight.getSightName());
                    Query query = new Query(Criteria.where("sightName").is(sight.getSightName()));
                    Update update = new Update().set("fallbackPhoto", fallbackPhoto);
                    mongoTemplate.updateFirst(query, update, Sight.class);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    System.err.println("updateFPsOfSights Exception:");
                    System.err.println(e);
                }
            }
        }
    }
}