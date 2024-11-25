package com.rafaelswr.springsecurityindeep.repository;

import com.rafaelswr.springsecurityindeep.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
}
