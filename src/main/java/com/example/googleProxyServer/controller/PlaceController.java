// PlaceController.java
package com.example.googleProxyServer.controller;

import com.example.googleProxyServer.entity.Place;
import com.example.googleProxyServer.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public List<Place> getAllPlaces() {
        return placeService.findAll();
    }

    @PostMapping
    public Place createPlace(@RequestBody Place place) {
        return placeService.save(place);
    }

    // 기타 필요한 엔드포인트들
}