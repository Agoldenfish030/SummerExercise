package com.exercise.backend.runner;

import com.exercise.backend.controller.KeelungSightsRepository;
import com.exercise.backend.controller.KeelungSightsService;
import com.exercise.backend.crawler.KeelungSightsCrawler;
import com.exercise.backend.crawler.Sight;
import com.exercise.backend.zoneName.ZoneConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SightsApplicationRunner implements ApplicationRunner {
    @Autowired
    private KeelungSightsService service;
    @Autowired
    private KeelungSightsRepository repository;

    @Override
    public void run(ApplicationArguments args){
        if(repository.count() == 0){
            System.out.println("No sights. Start crawling...");
            KeelungSightsCrawler crawler = new KeelungSightsCrawler();
            for(String zone : ZoneConstant.KEELUNG_ZONES_ENGLISH){
                System.out.println("Start crawling " + zone + "...");
                Sight[] sights = crawler.getItems(zone);
                for(Sight sight : sights){
                    service.postSightsService(sight);
                }
            }
        }
        System.out.println("Already crawled.");
    }
}