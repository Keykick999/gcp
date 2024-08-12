package com.example.googleProxyServer.document;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "results")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResultDocument {
    @Id
    private String id;
    private Long resultId;
    private String createdAt;
    private Double totalPrice;
    private String memberId;
    private List<String> places;
}
