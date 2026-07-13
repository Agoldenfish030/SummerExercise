package com.exercise.backend.controller;

import com.exercise.backend.crawler.KeelungSightsCrawler;
import com.exercise.backend.crawler.Sight;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
public class SightController {

    @GetMapping("/api/sights/{sightName}")
    public ResponseEntity<Sight[]> getSights(@PathVariable("sightName") String sightName){
        KeelungSightsCrawler crawler = new KeelungSightsCrawler();
        Sight[] sights = crawler.getItems(sightName);
        return sights != null ?
                ResponseEntity.status(HttpStatus.OK).body(sights) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}