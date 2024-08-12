package com.example.googleProxyServer.controller;


import com.example.googleProxyServer.entity.Member;
import com.example.googleProxyServer.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "Rdatabase/signUp";
    }

//    @PostMapping("/signup")
//    public String signUp(@RequestBody Member member) {
//        memberService.save(member);
//        return "redirect:/auth/login";
//    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "Rdatabase/login";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        Member user = memberService.findByMemberNameAndPassword(username, password).orElse(null);
//        if (user != null) {
//            return "redirect:/home";
//        }
//        return "redirect:/auth/login?error";
//    }
}
