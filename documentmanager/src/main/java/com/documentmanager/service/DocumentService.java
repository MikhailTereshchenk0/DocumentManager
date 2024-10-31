package com.documentmanager.service;

import com.documentmanager.dto.DocumentDto;
import com.documentmanager.exception.NotFoundException;
import com.documentmanager.kafka.KafkaProducer;
import com.documentmanager.model.FileMeta;
import com.documentmanager.repository.IFileRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService implements IDocumentService {
    private final IFileRepository fileRepository;
    private final KafkaProducer kafkaProducer;
    private final RestTemplate restTemplate;
    private final UserService userService;

    @Value("${service.url.storage}")
    private String storageServiceUrl;

    @Override
    public void create(DocumentDto documentDto, Principal principal) {
        FileMeta fileMeta = new FileMeta();
        fileMeta.setType("txt");
        fileMeta.setUserId(userService.findByUsername(principal.getName()).getId());
        fileRepository.save(fileMeta);
        documentDto.setId(fileMeta.getId());
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String documentAsString = objectMapper.writeValueAsString(documentDto);
            kafkaProducer.sendTextDocument(documentAsString);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<?> findAll(Principal principal) {
        return fileRepository.findAllByUserId(userService.findByUsername(principal.getName()).getId());
    }

    @Override
    public DocumentDto findById(UUID id) {
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(storageServiceUrl + "/" + id, DocumentDto.class);
            return (DocumentDto) response.getBody();
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NotFoundException("Document Not Found");
        }
    }

    @Override
    public void update(DocumentDto documentDto) {
        try {
            restTemplate.put(storageServiceUrl, DocumentDto.class);
        }
        catch (HttpClientErrorException.NotFound exception) {
            throw new NotFoundException("Document Not Found");
        }
    }

    @Override
    public void deleteById(UUID id) {
        try{
            restTemplate.delete(storageServiceUrl + "/" + id);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NotFoundException("Document Not Found");
        }
        fileRepository.deleteById(id);
    }
}