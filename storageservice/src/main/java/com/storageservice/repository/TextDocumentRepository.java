package com.storageservice.repository;

import com.storageservice.model.TextDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TextDocumentRepository extends MongoRepository<TextDocument, String> {

}