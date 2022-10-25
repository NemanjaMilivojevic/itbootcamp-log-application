package com.itbootcamplogger.repository;

import com.itbootcamplogger.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {


    @Query( "select count(*) from Client c where c.username in :username" )
    Integer findByUsername(@Param("username") String username);
    @Query( "select count(*) from Client c where c.email in :email" )
    Integer findByEmail(@Param("email") String email);

}
