package com.documentmanager.service;

import com.documentmanager.model.Document;

import java.util.List;

public interface IDocumentService {
    void create(Document document);
    List<Document> findAll();
    Document findById(String id);
    void update(Document document);
    void deleteById(String id);
}
