package com.example.googleProxyServer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "result_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResultDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long detailId;

    @ManyToOne
    @JoinColumn(name = "result_id", nullable = false)
    private Result result;

    @Column(name = "place", nullable = false, length = 60)
    private String place;
}
