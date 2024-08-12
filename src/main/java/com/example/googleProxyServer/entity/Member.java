package com.example.googleProxyServer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Member {
    @Id
    @Column(name = "member_id", length = 320)
    private String memberId;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "member_name", nullable = false, length = 15)
    private String memberName;
}
