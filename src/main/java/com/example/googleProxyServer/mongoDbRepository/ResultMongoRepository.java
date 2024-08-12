package com.example.googleProxyServer.mongoDbRepository;

import com.example.googleProxyServer.document.ResultDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultMongoRepository extends MongoRepository<ResultDocument, String> {
}
