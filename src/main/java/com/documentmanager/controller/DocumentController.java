package com.documentmanager.controller;

import com.documentmanager.model.Document;
import com.documentmanager.service.IDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> find(@PathVariable("id") String id) {
        Document document = documentService.findById(id);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Document document) {
        documentService.update(document);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        documentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
