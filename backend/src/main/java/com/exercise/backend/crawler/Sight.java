package com.exercise.backend.crawler;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sights")
public class Sight {
    private String sightName;
    private String zone;
    private String category;
    private String photoURL;
    private String address;
    private String description;
    private String fallbackPhoto;

    public String getSightName() {
        return sightName;
    }

    public void setSightName(String sightName) {
        this.sightName = sightName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFallbackPhoto(){
        return fallbackPhoto;
    }

    public void setFallbackPhoto(String fallbackPhoto){
        this.fallbackPhoto = fallbackPhoto;
    }

    @Override
    public String toString(){
        return String.format("SightName: %s\n" +
                "Zone: %s\n" +
                "Category: %s\n" +
                "PhotoURL: %s\n" +
                "Description: %s\n" +
                "Address: %s\n" +
                "FallbackPhoto: %s\n", sightName, zone, category, photoURL, description, address, fallbackPhoto);
    }
}