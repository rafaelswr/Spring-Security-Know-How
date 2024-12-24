package com.rafaelswr.springsecurityindeep.methodAuthorization;

import com.rafaelswr.springsecurityindeep.exception.DocumentException;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class DocumentRepository {

    private final Logger logger = Logger.getLogger(String.valueOf(DocumentRepository.class));

    private Map<String, Document> documents = Map.of(
            "1", new Document("natalie"),
            "abc124", new Document("emma"),
            "abc125", new Document("ines")
    );

    public Document findDocumentById(String code){
        if(documents.containsKey(code)){
            return documents.get(code);
        }else{
            throw new DocumentException("Document " + code + " Not Found!");
        }
    }
}