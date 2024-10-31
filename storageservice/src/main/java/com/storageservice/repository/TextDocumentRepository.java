package com.storageservice.repository;

import com.storageservice.model.TextDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TextDocumentRepository extends MongoRepository<TextDocument, UUID> {

}