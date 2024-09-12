package com.documentmanager.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Document {
    private int id;
    private String Title;
}
