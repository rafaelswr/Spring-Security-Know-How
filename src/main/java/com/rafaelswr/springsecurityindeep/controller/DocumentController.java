package com.rafaelswr.springsecurityindeep.controller;


import com.rafaelswr.springsecurityindeep.methodAuthorization.Document;
import com.rafaelswr.springsecurityindeep.methodAuthorization.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/document")
public class DocumentController {

    public final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocById(@PathVariable String id){
        return new ResponseEntity<>(documentService.getDocumentById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

}
