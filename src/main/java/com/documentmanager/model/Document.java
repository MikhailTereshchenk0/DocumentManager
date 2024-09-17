package com.documentmanager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@org.springframework.data.mongodb.core.mapping.Document("documents")
public class Document {
    @Id
    private String id;
    private String title;
}
