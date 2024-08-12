package com.example.googleProxyServer.controller;

import com.example.googleProxyServer.entity.Place;
import com.example.googleProxyServer.entity.Member;
import com.example.googleProxyServer.repository.MemberRepository;
import com.example.googleProxyServer.service.MemberService;
import com.example.googleProxyServer.service.PlaceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PlaceService placeService;

//물어보기
//    @GetMapping
//    public String showMyPage(Model model, Principal principal) {
//        Member user = memberService.findByMemberName(principal.getName()).orElse(null);
//        List<Place> places = placeService.findByUser(user);
//        model.addAttribute("user", user);
//        model.addAttribute("places", places);
//        return "Rdatabase/myPage";
//    }


//    @GetMapping("/records")
//    public String showRecordPage(Model model, Principal principal) {
//        Member member = memberService.findByMemberName(principal.getName()).orElse(null);
//        List<Place> places = placeService.findByUser(member);
//        model.addAttribute("places", places);
//        return "Rdatabase/MyResult";
//    }


    @GetMapping
    public String showMyPage(Model model, HttpSession session) {
        String memberId = (String) session.getAttribute("memberId");
        Member member = memberService.findById(memberId);
        //
        return "Rdatabase/myPage";
    }


    @GetMapping("/records")
    public String showRecordPage(Model model, HttpSession session) {
//        String memberId = (String) session.getAttribute("memberId");
//        Member member = memberService.findById(memberId);
        return "Rdatabase/myResult";
    }
}
