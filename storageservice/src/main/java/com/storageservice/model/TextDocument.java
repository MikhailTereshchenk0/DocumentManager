package com.storageservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document("documents")
public class TextDocument {
    @Id
    private UUID id;
    private String title;
}