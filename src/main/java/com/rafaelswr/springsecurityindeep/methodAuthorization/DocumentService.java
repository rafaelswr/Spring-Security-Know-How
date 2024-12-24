package com.rafaelswr.springsecurityindeep.methodAuthorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @PreAuthorize("isAuthenticated() and hasPermission(#id, 'Document', 'admin')")
    public Document getDocumentById(String id){
        return documentRepository.findDocumentById(id);
    }

}
