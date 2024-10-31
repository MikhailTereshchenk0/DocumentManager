package com.storageservice.service;

import com.storageservice.model.TextDocument;

import java.util.List;
import java.util.UUID;

public interface ITextDocumentService {
    void save(TextDocument textDocument);
    List<TextDocument> findAll();
    TextDocument findById(UUID id);
    void update(TextDocument document);
    void deleteById(UUID id);
}
