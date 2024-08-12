package com.example.googleProxyServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/home")
    public String home() {
        System.out.println("실행");
        return "/home";
    }

    @GetMapping("/Rsimulator/description")
    public String description() {
        return "Rsimulator/description";
    }

    @GetMapping("/Rsimulator/simulator")
    public String simulator() {
        return "Rsimulator/simulator";
    }

    @GetMapping("/Rsimulator/result")
    public String result() {
        return "Rsimulator/result";
    }

    @GetMapping("/Rsimulator/addPlace")
    public String addPlace() {
        return "Rsimulator/addPlace";
    }

    @GetMapping("/Rdatabase/login")
    public String login() {
        return "Rdatabase/login";
    }

    @GetMapping("/Rdatabase/signUp")
    public String signUp() {
        return "Rdatabase/signUp";
    }
}
