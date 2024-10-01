package com.documentmanager.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@org.springframework.data.mongodb.core.mapping.Document("documents")
public class Document {
    @Id
    private String id;
    private String title;
}

