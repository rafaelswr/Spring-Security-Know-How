package com.rafaelswr.springsecurityindeep.repository;

import com.rafaelswr.springsecurityindeep.model.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleUserRepository extends JpaRepository<SimpleUser, Integer> {

}
