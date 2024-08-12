package com.example.googleProxyServer.service;

import com.example.googleProxyServer.document.ResultDocument;
import com.example.googleProxyServer.dto.ResultResponse;
import com.example.googleProxyServer.dto.ResultSaveRequest;
import com.example.googleProxyServer.entity.Member;
import com.example.googleProxyServer.entity.Result;
import com.example.googleProxyServer.entity.ResultDetail;
import com.example.googleProxyServer.mongoDbRepository.ResultMongoRepository;
import com.example.googleProxyServer.repository.ResultDetailRepository;
import com.example.googleProxyServer.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ResultMongoRepository resultMongoRepository;

    @Autowired
    private ResultDetailRepository resultDetailRepository;

    //저장
    public Result save(ResultSaveRequest resultSaveRequest, Member member) {
        if(member == null) {
            System.out.println("member = " + member);
            return null;
        }
        Double totalPrice = resultSaveRequest.getTotalPrice();
        List<String> places = resultSaveRequest.getPlaces();

        Result result = Result.builder()
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .totalPrice(totalPrice)
                .member(member)
                .build();
        resultRepository.save(result);      //아래에 넣어도 되면 아래로 옮겨서 한 번에 저장하기

        places.forEach(place -> {
            ResultDetail resultDetail = ResultDetail.builder()
                    .result(result)
                    .place(place)
                    .build();
            resultDetailRepository.save(resultDetail);
        });

        //mongoDb에 저장
        ResultDocument resultDocument = ResultDocument.builder()
                .resultId(result.getResultId())
                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .totalPrice(totalPrice)
                .memberId(member.getMemberId())
                .places(places)
                .build();

        resultMongoRepository.save(resultDocument);
        return result;
    }


    // 회원 id로 조회
    public List<ResultResponse> findByMemberId(String memberId) {
        List<Object[]> results = resultDetailRepository.findResultDetailsByMemberId(memberId);


        Map<Double, List<String>> resultMap = new HashMap<>();

        for (Object[] result : results) {
            Long resultId = (Long) result[0];
            Double totalPrice = (Double) result[1];
            String place = (String) result[2];

            if (!resultMap.containsKey(totalPrice)) {
                resultMap.put(totalPrice, new ArrayList<>());
            }
            resultMap.get(totalPrice).add(place);
        }


        List<ResultResponse> resultResponses = new ArrayList<>();
        for (Map.Entry<Double, List<String>> entry : resultMap.entrySet()) {
            resultResponses.add(new ResultResponse(entry.getKey(), entry.getValue()));
        }

        return resultResponses;
    }
}
















