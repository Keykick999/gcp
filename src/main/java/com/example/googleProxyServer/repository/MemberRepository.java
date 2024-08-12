package com.example.googleProxyServer.repository;

import com.example.googleProxyServer.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByMemberName(String username);
    Optional<Member> findByMemberNameAndPassword(String username, String password);
}
