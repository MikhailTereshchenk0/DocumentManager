package com.documentmanager.service;

import com.documentmanager.model.Document;

import java.util.List;
import java.util.Optional;

public interface IDocumentService {
    void create(Document document);
    List<Document> readAllDocuments();
    Optional<Document> read(String id);
    boolean update(String id);
    void delete(String id);
}
