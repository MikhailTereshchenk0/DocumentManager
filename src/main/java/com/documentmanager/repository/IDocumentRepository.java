package com.documentmanager.repository;

import com.documentmanager.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDocumentRepository extends MongoRepository<Document, String> {
}
