package com.example.googleProxyServer.controller;

import com.example.googleProxyServer.entity.Member;
import com.example.googleProxyServer.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllUsers() {
        return memberService.findAll();
    }

//    @PostMapping
//    public Member createUser(@RequestBody Member user) {
//        return memberService.save(user);
//    }
}
