package com.example.googleProxyServer.service;

import com.example.googleProxyServer.entity.Place;
import com.example.googleProxyServer.entity.Member;
import com.example.googleProxyServer.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;



    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    public Place save(Place place) {
        return placeRepository.save(place);
    }
}
