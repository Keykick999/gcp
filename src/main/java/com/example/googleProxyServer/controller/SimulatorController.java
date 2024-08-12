package com.example.googleProxyServer.controller;

import com.example.googleProxyServer.entity.Place;
import com.example.googleProxyServer.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/simulator")
public class SimulatorController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public String simulator() {
        return "Rsimulator/simulator";
    }

    @PostMapping("/saveResult")
    public String saveResult(@RequestBody Place place) {
        placeService.save(place);
        return "redirect:/result";
    }

    @PostMapping("/reset")
    public String resetSimulator() {
        // 초기화 작업 수행
        return "redirect:/simulator";
    }
}
