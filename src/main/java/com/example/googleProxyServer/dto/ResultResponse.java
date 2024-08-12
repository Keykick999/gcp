package com.example.googleProxyServer.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResultResponse {
    private Double totalPrice;
    private List<String> places;
}
