package com.example.googleProxyServer.repository;

import com.example.googleProxyServer.dto.ResultResponse;
import com.example.googleProxyServer.entity.ResultDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultDetailRepository extends JpaRepository<ResultDetail, Long> {
    // n + 1 query 문제?
    @Query("select r.resultId, r.totalPrice, rd.place " +
            "from ResultDetail rd join rd.result r " +
            "where r.member.memberId = :memberId")
    List<Object[]> findResultDetailsByMemberId(@Param("memberId") String memberId);
}

