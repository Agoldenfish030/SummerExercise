package com.exercise.backend.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class KeelungSightsCrawler {
    private final String SIGHTS_URL = "https://www.travelking.com.tw";
    private final String SIGHTS_BOX_X_PATH = "/html/body/div[@id='wrapper']/div[@id='content']/div/div[@id='guide-point']/div[@class='box']";
    private final String[] KEELUNG_SIGHTS_LIST = {"qidu", "zhongshan", "zhongzheng", "renai", "anle", "xinyi", "nuannuan"};
    private final String SIGHT_DETAILS_X_PATH = "/html/body/div/div[@id='content']/div[@class='size']/div[@class='list_detail news_large']/div[1]/div[1]/div[1]";
    private final int SIGHT_NAME_INDEX = 0;
    private final int PHOTO_URL_INDEX = 2;
    private final int ADDRESS_INDEX = 4;
    private final int DESCRIPTION_INDEX = 3;

    private Element sightsBox;
    private Sight[] sights;

    public KeelungSightsCrawler(){
        try{
            Document webDoc = Jsoup.connect(SIGHTS_URL + "/tourguide/taiwan/keelungcity/").get();
            sightsBox = webDoc.selectXpath(SIGHTS_BOX_X_PATH).first();
        }catch(IOException e){
            System.err.println("IOException in KeelungSightsCrawler constructor: " + e);
        }
    }

    private void addSight(int index, String zone, String sightUrl){
        Sight newSight = sights[index] = new Sight();
        try{
            Document subWebDoc = Jsoup.connect(sightUrl).get();
            Element sightDetailsBox = subWebDoc.selectXpath(SIGHT_DETAILS_X_PATH).first();

            newSight.setSightName(sightDetailsBox.getElementsByTag("meta").get(SIGHT_NAME_INDEX).attr("content"));
            newSight.setZone(zone);
            newSight.setCategory(sightDetailsBox.getElementsByTag("cite").first()
                    .getElementsByTag("span").first()
                    .getElementsByTag("span").first()
                    .getElementsByTag("strong").first().text());
            newSight.setPhotoURL(sightDetailsBox.getElementsByTag("meta").get(PHOTO_URL_INDEX).attr("content"));
            newSight.setAddress(sightDetailsBox.getElementsByTag("meta").get(ADDRESS_INDEX).attr("content"));
            newSight.setDescription(sightDetailsBox.getElementsByTag("meta").get(DESCRIPTION_INDEX).attr("content"));
        }catch(IOException | NullPointerException e){
            System.err.println("IOException or NullPointerException in KeelungSightsCrawler getSight: " + e);
        }
    }

    public Sight[] getItems(String keelungSights){
        try{
            int count;
            for(count = 0; count < 7; count++){
                if(Objects.equals(keelungSights, KEELUNG_SIGHTS_LIST[count])){
                    ArrayList<Element> districtSights = sightsBox.getElementsByTag("ul")
                            .get(count+1)
                            .getElementsByTag("li");
                    sights = new Sight[districtSights.size()];
                    String zone = sightsBox.getElementsByTag("h4").get(count).text();
                    for(int sightsCount = 0; sightsCount < districtSights.size(); sightsCount++){
                        String sightUrl = SIGHTS_URL + districtSights.get(sightsCount).getElementsByTag("a").attr("href");
                        addSight(sightsCount, zone, sightUrl);
                    }
                    break;
                }
            }
            if(count == 7) throw new Exception("Not found the district.");
        }catch(Exception e) {
            System.err.println("Exception in KeelungSightsCrawler getItems: " + e);
        }

        return sights;
    }
}