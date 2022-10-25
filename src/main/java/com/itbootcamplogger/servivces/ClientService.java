package com.itbootcamplogger.servivces;

import com.itbootcamplogger.controllers.model.CreateClientRequest;
import com.itbootcamplogger.mapper.ClientMapper;
import com.itbootcamplogger.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;

    public ResponseEntity<String> createClient(CreateClientRequest client) {
        final var model = mapper.toClientModel(client);
        if (isUsernameExists(client.getUsername()) != 0 || isEmailExists(client.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or email already exists");
        } else if (!client.isPasswordValid(client.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password input incorrect");
        } else {
            repository.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body("Client successfully created");
        }

    }

    public int isUsernameExists(String username){
       return repository.findByUsername(username);
    }
    public int isEmailExists(String email){
        return repository.findByEmail(email);
    }

}
