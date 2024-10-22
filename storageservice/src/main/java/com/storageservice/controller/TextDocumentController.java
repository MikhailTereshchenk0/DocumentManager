package com.storageservice.controller;

import com.storageservice.model.TextDocument;
import com.storageservice.service.ITextDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class TextDocumentController {
    private final ITextDocumentService textDocumentService;

    @GetMapping
    public ResponseEntity<List<TextDocument>> findAll() {
        final List<TextDocument> documents = textDocumentService.findAll();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TextDocument> find(@PathVariable("id") String id) {
        TextDocument document = textDocumentService.findById(id);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody TextDocument document) {
        textDocumentService.update(document);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        textDocumentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}