package com.example.googleProxyServer.restController;

import com.example.googleProxyServer.dto.LoginRequest;
import com.example.googleProxyServer.entity.Member;
import com.example.googleProxyServer.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/member")
public class MemberRestController {

    @Autowired
    private MemberService memberService;


    // commit 반영되나 확인
    @GetMapping("/practice")
    public String example() {
        return "helowowol";
    }

    // 회원 가입
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody Member signUpRequest) {
        Member signedUp = memberService.signUp(signUpRequest);

        return (signedUp == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok("successfully saved");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        String memberId = loginRequest.getMemberId();
        Member foundMember = memberService.findById(loginRequest.getMemberId());

        // 존재 하지 않는 id
        if(foundMember == null) {
            return ResponseEntity.notFound().build();
        }

        Member logined = memberService.login(loginRequest, foundMember);

        // id, 비밀번호 불일치
        if(logined == null) {
            return ResponseEntity.notFound().build();
        }

        //세션 설정
        session.setAttribute("memberId", memberId);
        session.setMaxInactiveInterval(30 * 60);       //세션 만료 기간 30분으로 설정
        return ResponseEntity.ok("successfully logined");
    }

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        String memberId = (String)session.getAttribute("memberId");
        if(memberId == null) {
            return ResponseEntity.badRequest().build();
        }
        session.setMaxInactiveInterval(0);
        return ResponseEntity.ok("logout succeeded");
    }


    // 아이디 중복 체크
    @GetMapping("/idCheck")
    public ResponseEntity<String> isDuplicatedId(@RequestParam("memberId") String memberId) {
        boolean isDuplicated = memberService.isDuplicated(memberId);
        return (isDuplicated == true) ? ResponseEntity.badRequest().build() : ResponseEntity.ok("valid id");
    }
}
