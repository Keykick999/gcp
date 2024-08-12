package com.example.googleProxyServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping
    public String showResultPage(Model model) {
        // 필요한 데이터를 모델에 추가합니다.
        // model.addAttribute("attributeName", attributeValue);
        return "Rsimulator/result"; // Thymeleaf 템플릿 파일명 (예: src/main/resources/templates/result.html)
    }
}
