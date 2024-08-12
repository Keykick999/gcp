package com.example.googleProxyServer.repository;

import com.example.googleProxyServer.entity.Member;
import com.example.googleProxyServer.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}

