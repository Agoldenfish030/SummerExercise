package com.exercise.backend.controller;

import com.exercise.backend.crawler.Sight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/sights")
public class SightController {

    @Autowired
    private KeelungSightsService service;

    @GetMapping("/{zone}")
    public ResponseEntity<List<Sight>> getSightsControl(@PathVariable("zone") String zone) {
        List<Sight> sights = service.getSightService(zone);
        if(sights == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sights);
    }
}