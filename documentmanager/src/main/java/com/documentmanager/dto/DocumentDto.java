package com.documentmanager.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class DocumentDto implements Serializable {
    UUID id;
    String title;
}