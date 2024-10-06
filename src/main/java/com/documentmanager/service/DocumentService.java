package com.documentmanager.service;

import com.documentmanager.exception.NotFoundException;
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
    public Document findById(String id) {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isEmpty())
            throw new NotFoundException("Document Not Found.");
        return document.get();
    }

    @Override
    public void update(Document document) {
        Optional<Document> documentOpt = documentRepository.findById(document.getId());
        if (documentOpt.isEmpty())
            throw new NotFoundException("Document Not Found.");
        Document newDocument = documentOpt.get();
        newDocument.setTitle(document.getTitle());
    }

    @Override
    public void deleteById(String id) {
        if (!documentRepository.existsById(id))
            throw new NotFoundException("Document Not Found.");
        documentRepository.deleteById(id);
    }
}
