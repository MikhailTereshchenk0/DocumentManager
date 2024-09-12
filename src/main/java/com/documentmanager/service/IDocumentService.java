package com.documentmanager.service;

import com.documentmanager.model.Document;

import java.util.List;

public interface IDocumentService {
    void create(Document document);
    List<Document> readAllDocuments();
    Document read(int id);
    boolean update(int id);
    boolean delete(int id);
}
