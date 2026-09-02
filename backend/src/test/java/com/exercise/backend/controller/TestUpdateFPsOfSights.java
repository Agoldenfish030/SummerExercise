package com.exercise.backend.controller;

import com.exercise.backend.crawler.Sight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class TestUpdateFPsOfSights {
    @Autowired
    private KeelungSightsService service;

    @Test
    public void testUpdateFPsOfSights(){
        System.out.println("測試查詢信義區圖片：");
        List<Sight> sights = service.updateFPsOfSights("xinyi");
        System.out.println(sights);
    }
}
