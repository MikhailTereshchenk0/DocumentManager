package com.documentmanager.service;

import com.documentmanager.model.Document;
import com.documentmanager.repository.IDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements IDocumentService {

    private final IDocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(IDocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void create(Document document) {
        documentRepository.save(document);
    }

    @Override
    public List<Document> readAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public Optional<Document> read(String id) {
        return documentRepository.findById(id);
    }

    @Override
    public boolean update(String id) {
        return false;
        //todo
    }

    @Override
    public void delete(String id) {
        documentRepository.deleteById(id);
    }
}
