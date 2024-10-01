package com.documentmanager.repository;

import com.documentmanager.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IDocumentRepository extends MongoRepository<Document, String> {
}
