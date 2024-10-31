package com.documentmanager.controller;

import com.documentmanager.dto.DocumentDto;
import com.documentmanager.service.IDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final IDocumentService documentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DocumentDto documentDto, Principal principal) {
        documentService.create(documentDto, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll(Principal principal) {
        return new ResponseEntity<>(documentService.findAll(principal), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(documentService.findById(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody DocumentDto documentDto) {
        documentService.update(documentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        documentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
