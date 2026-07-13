package com.exercise.backend.crawler;

public class TestKeelungSightsCrawler {
    public static void main(String[] args){
        KeelungSightsCrawler crawler = new KeelungSightsCrawler();
        Sight[] sights = crawler.getItems("qidu");
        for (Sight s: sights) {
            System.out.println(s);
        }
    }
}