package com.storageservice.service;

import com.storageservice.exception.NotFoundException;
import com.storageservice.model.TextDocument;
import com.storageservice.repository.TextDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TextDocumentService implements ITextDocumentService {
    private final TextDocumentRepository textDocumentRepository;

    @Override
    public void save(TextDocument textDocument) {
        textDocumentRepository.save(textDocument);
    }

    @Override
    public List<TextDocument> findAll() {
        return textDocumentRepository.findAll();
    }

    @Override
    public TextDocument findById(String id) {
        Optional<TextDocument> document = textDocumentRepository.findById(id);
        if (document.isEmpty())
            throw new NotFoundException("Document Not Found.");
        return document.get();
    }

    @Override
    public void update(TextDocument document) {
        Optional<TextDocument> documentOpt = textDocumentRepository.findById(document.getId());
        if (documentOpt.isEmpty())
            throw new NotFoundException("Document Not Found.");
        TextDocument newDocument = documentOpt.get();
        newDocument.setTitle(document.getTitle());
    }

    @Override
    public void deleteById(String id) {
        if (!textDocumentRepository.existsById(id))
            throw new NotFoundException("Document Not Found.");
        textDocumentRepository.deleteById(id);
    }
}

