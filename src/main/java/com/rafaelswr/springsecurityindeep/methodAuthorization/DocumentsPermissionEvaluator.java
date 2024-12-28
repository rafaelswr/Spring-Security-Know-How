package com.rafaelswr.springsecurityindeep.methodAuthorization;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.logging.Logger;

@Component
public class DocumentsPermissionEvaluator implements PermissionEvaluator {

    private final Logger logger = Logger.getLogger(String.valueOf(DocumentsPermissionEvaluator.class));
    private final DocumentRepository documentRepository;

    public DocumentsPermissionEvaluator(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        return false;
      /*  Document doc = (Document) targetDomainObject;
        String perm = (String) permission;

        boolean adm  = authentication.getAuthorities().stream().anyMatch(c->c.getAuthority().equals(perm));

        return adm || authentication.getName().equals(doc.getOwner());
*/
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        String id = (String) targetId;
        String perm = (String) permission;
        Document doc = documentRepository.findDocumentById(id);

        var isOwner = doc.getOwner().equals(authentication.getName());
        var isAdmin = authentication.getAuthorities().stream().anyMatch(ele->ele.getAuthority().equals("ROLE_" + perm));

        return isOwner || isAdmin;
     }

}
