package com.storageservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.storageservice.model.TextDocument;
import com.storageservice.service.ITextDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ITextDocumentService textDocumentService;

    @KafkaListener(topics = "documents", groupId = "groupId")
    public void listener(String document) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TextDocument textDocument = objectMapper.readValue(document, TextDocument.class);
        textDocumentService.save(textDocument);
    }
}
