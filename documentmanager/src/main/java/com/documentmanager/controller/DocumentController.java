package com.documentmanager.controller;

import com.documentmanager.dto.DocumentDto;
import com.documentmanager.service.IDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final IDocumentService documentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DocumentDto documentDto) {
        documentService.create(documentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(documentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable("id") String id) {
        return new ResponseEntity<>(documentService.findById(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody DocumentDto documentDto) {
        documentService.update(documentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        documentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
