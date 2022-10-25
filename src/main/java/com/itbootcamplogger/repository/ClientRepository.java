package com.itbootcamplogger.repository;

import com.itbootcamplogger.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByUsernameEquals(String username);
    boolean existsByEmailEquals(String email);



}
