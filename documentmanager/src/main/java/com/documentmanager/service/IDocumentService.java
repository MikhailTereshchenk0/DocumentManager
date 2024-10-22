package com.documentmanager.service;

import com.documentmanager.dto.DocumentDto;

import java.util.List;

public interface IDocumentService {
    void create(DocumentDto documentDto);
    List<?> findAll();
    DocumentDto findById(String id);
    void update(DocumentDto documentDto);
    void deleteById(String id);
}
