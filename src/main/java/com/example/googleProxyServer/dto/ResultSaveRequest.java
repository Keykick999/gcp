package com.example.googleProxyServer.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResultSaveRequest {
    private Double totalPrice;
    private List<String> places;
}
