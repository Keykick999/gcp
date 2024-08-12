package com.example.googleProxyServer.restController;

import com.example.googleProxyServer.dto.ResultResponse;
import com.example.googleProxyServer.dto.ResultSaveRequest;
import com.example.googleProxyServer.entity.Member;
import com.example.googleProxyServer.entity.Result;
import com.example.googleProxyServer.repository.MemberRepository;
import com.example.googleProxyServer.service.ResultService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/result")
@RestController
public class ResultRestController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private MemberRepository memberRepository;


    //저장
    @PostMapping
    public ResponseEntity<String> saveResult(@RequestBody ResultSaveRequest resultSaveRequest, HttpSession session) {
//        String memberId = "user";
        String memberId = (String) session.getAttribute("memberId");
        System.out.println("memberId = " + memberId);
        Member member = memberRepository.findById(memberId).orElse(null);

        Result saved = resultService.save(resultSaveRequest, member);
        return (saved == null) ? ResponseEntity.badRequest().build() : ResponseEntity.ok("successfully saved");
    }


    //조회
    @GetMapping
    public ResponseEntity<List<ResultResponse>> findByMemberId(HttpSession session) {
        String memberId = (String) session.getAttribute("memberId");
        Member member = memberRepository.findById(memberId).orElse(null);

        List<ResultResponse> resultResponses = resultService.findByMemberId(memberId);
        return (member == null) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(resultResponses);
    }
}






