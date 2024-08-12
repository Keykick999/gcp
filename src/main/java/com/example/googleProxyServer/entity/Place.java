package com.example.googleProxyServer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "places")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "place_name", nullable = false, length = 60)
    private String placeName;

    @Column(name = "place_type", nullable = false, length = 45)
    private String placeType;

    @Column(name = "place_price", nullable = false)
    private Double placePrice;
}
