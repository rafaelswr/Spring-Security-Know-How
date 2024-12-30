package com.rafaelswr.springsecurityindeep.FilteringMethodAuth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Long, Product> {

    @Query("""
    select p from Product p where p.product LIKE %:txt% and p.owner=?#{authentication.getName()}
   """)
    List<Product> findDocumentsByOwner(String txt);


}
