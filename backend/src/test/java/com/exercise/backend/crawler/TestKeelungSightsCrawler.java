package com.exercise.backend.crawler;

import java.util.Scanner;

public class TestKeelungSightsCrawler {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("輸入qidu/zhongshan/zhongzheng/renai/anle/xinyi/nuannuan：");
        String keelungZone = input.next();

        KeelungSightsCrawler crawler = new KeelungSightsCrawler();
        Sight[] sights = crawler.getItems(keelungZone);
        for (Sight s: sights) {
            System.out.println(s);
        }
    }
}