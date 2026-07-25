package com.exercise.backend.controller;

import com.exercise.backend.crawler.Sight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KeelungSightsRepository extends MongoRepository<Sight, String> {
    List<Sight> findByZone(String zone);
}