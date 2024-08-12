package com.example.googleProxyServer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "results")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long resultId;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
