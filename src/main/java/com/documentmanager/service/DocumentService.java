package com.documentmanager.service;

import com.documentmanager.model.Document;
import com.documentmanager.repository.IDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentService implements IDocumentService {

    private final IDocumentRepository documentRepository;

    @Override
    public void create(Document document) {
        documentRepository.save(document);
    }

    @Override
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public Optional<Document> findById(String id) {
        return documentRepository.findById(id);
    }

    @Override
    public boolean update(Document document) {
        Optional<Document> documentOpt = documentRepository.findById(document.getId());
        if (documentOpt.isEmpty()) return false;
        Document newDocument = documentOpt.get();
        newDocument.setId(document.getId());
        newDocument.setTitle(document.getTitle());
        return true;
    }

    @Override
    public void deleteById(String id) {
        documentRepository.deleteById(id);
    }
}
