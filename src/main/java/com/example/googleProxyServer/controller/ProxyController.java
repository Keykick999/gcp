package com.example.googleProxyServer.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/proxy")
public class ProxyController {

    @Autowired
    private RestTemplate restTemplate;

    private final String API_KEY = "AIzaSyDtWXoaZG5kJLxYap_KigZvzNmrrq6FG28";

    @GetMapping("/places")
    public ResponseEntity<String> getPlaces(@RequestParam String location, @RequestParam int radius, @RequestParam String type) {
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                "?location=" + location +
                "&radius=" + radius +
                "&type=" + type +
                "&key=" + API_KEY;

        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/details")
    public ResponseEntity<String> getDetails(@RequestParam String place_id) {
        System.out.println("api실행");
        String url = "https://maps.googleapis.com/maps/api/place/details/json" +
                "?place_id=" + place_id
                +"&key=" + API_KEY;

        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }
}








