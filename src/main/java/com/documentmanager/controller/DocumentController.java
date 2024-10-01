package com.documentmanager.controller;

import com.documentmanager.model.Document;
import com.documentmanager.service.IDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final IDocumentService documentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Document document) {
        documentService.create(document);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Document>> findAll() {
        final List<Document> documents = documentService.findAll();
        if (documents == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> find(@PathVariable("id") String id) {
        final Optional<Document> optDocument = documentService.findById(id);
        if (optDocument.isPresent()) {
            Document document = optDocument.get();
            return new ResponseEntity<>(document, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Document document) {
        if (documentService.update(document)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        documentService.deleteById(id);
    }
}
