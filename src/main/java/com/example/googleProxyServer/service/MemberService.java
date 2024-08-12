package com.example.googleProxyServer.service;

import com.example.googleProxyServer.dto.LoginRequest;
import com.example.googleProxyServer.entity.Member;
import com.example.googleProxyServer.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // 회원 가입
    public Member signUp(Member signUpRequest) {
        String memberId = signUpRequest.getMemberId();
        String memberPassword = signUpRequest.getPassword();
        String memberName = signUpRequest.getMemberName();

        // id 중복 체크
        boolean isDuplicated = isDuplicated(memberId);

        // 유효성 검사
        if (isDuplicated == true || !isValidId(memberId) || !isValidPassword(memberPassword)) {
            return null;
        }

        // 비밀 번호 보안을 위해 인코딩해서 저장
        String encodedPassword = passwordEncoder.encode(memberPassword);

        // db에 저장
        return memberRepository.save(new Member(memberId, encodedPassword, memberName));
    }


    // 로그인
    public Member login(LoginRequest loginRequest, Member savedMember) {
        String memberId = loginRequest.getMemberId();
        String memberPassword = loginRequest.getPassword();
        String originalPassword = savedMember.getPassword();
        //사용자 입력 비밀 번호랑 저장된 비밀 번호 비교
        if(passwordEncoder.matches(memberPassword, originalPassword)) {
            // 일치 하지 않으면 member 객체 반환
            return savedMember;
        }
        // 일치 하지 않으면 null 반환
        return null;
    }


    // id 중복 체크
    public boolean isDuplicated(String memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if(findMember.isPresent()) {    //존재하는 id
            return true;
        }
        return false;
    }
    
    
    // 전체 조회
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // 단일 조회
    public Member findById(String memberId) {return memberRepository.findById(memberId).orElse(null);}

    // 회원 이름으로 조회
    public Optional<Member> findByMemberName(String memberName) {
        return memberRepository.findByMemberName(memberName);
    }
    
    // 회원 이름, 비밀번호로 조회
    public Optional<Member> findByMemberNameAndPassword(String memberName, String password) {
        return memberRepository.findByMemberNameAndPassword(memberName, password);
    }


    // 유효성 검사
    // 정규식을 사용하여 아이디 유효성 검사
    private boolean isValidId(String memberId) {
        String idPattern = "^[a-zA-Z0-9!@#$%^&*()_+~`|}{\\[\\]:;?,./]{8,20}$";
        return Pattern.matches(idPattern, memberId);
    }

    // 정규식을 사용하여 비밀번호 유효성 검사
    private boolean isValidPassword(String memberPassword) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(passwordPattern, memberPassword);
    }
}
