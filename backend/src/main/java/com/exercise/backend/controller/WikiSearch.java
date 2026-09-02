package com.exercise.backend.controller;

import org.springframework.beans.factory.annotation.Value;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class WikiSearch {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Value("AUTHOR_EMAIL")
    private static String authorEmail;
    private static final String USER_AGENT = "KeelungSightsWebsite/1.0 ("+authorEmail+")";

    public String searchPhotosByKeyword(String sightName) throws Exception {
        String encodedName = URLEncoder.encode(sightName, StandardCharsets.UTF_8);
        String url = String.format(
                "https://commons.wikimedia.org/w/api.php?action=query" +
                        "&generator=search" +
                        "&gsrsearch=%s" +
                        "&gsrnamespace=6" +
                        "&gsrlimit=1" +
                        "&prop=imageinfo" +
                        "&iiprop=url" +
                        "&format=json",
                encodedName
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", USER_AGENT)
                .GET().build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode rootNode = OBJECT_MAPPER.readTree(response.body());
        JsonNode pagesNode = rootNode.path("query").path("pages");
        if (pagesNode.isMissingNode()) {
            return "";
        }

        String imageUrl = "";
        for (JsonNode page : pagesNode) {
            String title = page.path("title").asText();
            System.out.println(sightName+": ");
            System.out.print("title->"+title);
            JsonNode imageInfoArray = page.path("imageinfo");
            if (imageInfoArray.isArray() && !imageInfoArray.isEmpty()) {
                JsonNode firstInfo = imageInfoArray.get(0);
                imageUrl = firstInfo.path("url").asText();
                System.out.print(String.format(" imageUrl->%s", imageUrl));
            }
            System.out.println();
        }
        return imageUrl;
    }
}
