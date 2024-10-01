package com.documentmanager.service;

import com.documentmanager.model.Document;

import java.util.List;
import java.util.Optional;

public interface IDocumentService {
    void create(Document document);
    List<Document> findAll();
    Optional<Document> findById(String id);
    boolean update(Document document);
    void deleteById(String id);
}
