package com.documentmanager.service;

import com.documentmanager.model.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements IDocumentService {
    @Override
    public void create(Document document) {
        //todo
    }

    @Override
    public List<Document> readAllDocuments() {
        //todo
        return null;
    }

    @Override
    public Document read(int id) {
        //todo
        return null;
    }

    @Override
    public boolean update(int id) {
        //todo
        return false;
    }

    @Override
    public boolean delete(int id) {
        //todo
        return false;
    }
}
