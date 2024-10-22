package com.storageservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("documents")
public class TextDocument {
    @Id
    private String id;
    private String title;
}