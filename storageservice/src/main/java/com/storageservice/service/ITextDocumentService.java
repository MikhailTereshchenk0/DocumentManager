package com.storageservice.service;

import com.storageservice.model.TextDocument;

import java.util.List;

public interface ITextDocumentService {
    void save(TextDocument textDocument);
    List<TextDocument> findAll();
    TextDocument findById(String id);
    void update(TextDocument document);
    void deleteById(String id);
}
