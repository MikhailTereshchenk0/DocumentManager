package com.documentmanager.service;

import com.documentmanager.dto.DocumentDto;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface IDocumentService {
    void create(DocumentDto documentDto, Principal principal);
    List<?> findAll(Principal principal);
    DocumentDto findById(UUID id);
    void update(DocumentDto documentDto);
    void deleteById(UUID id);
}
