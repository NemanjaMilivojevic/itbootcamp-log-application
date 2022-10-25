package com.itbootcamplogger.servivces;

import com.itbootcamplogger.controllers.model.CreateClientRequest;
import com.itbootcamplogger.controllers.model.CreateLoginRequest;
import com.itbootcamplogger.mapper.ClientMapper;
import com.itbootcamplogger.model.Client;
import com.itbootcamplogger.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<String> createClient(CreateClientRequest client) {
        final var model = mapper.toClientModel(client);
        if (repository.existsByUsernameEquals(client.getUsername()) || repository.existsByEmailEquals(client.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or email already exists");
        } else if (!client.isPasswordValid(client.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password input incorrect");
        } else {
            String encodedPassword = this.passwordEncoder.encode(model.getPassword());
            model.setPassword(encodedPassword);
            repository.save(model);
            return ResponseEntity.status(HttpStatus.CREATED).body("Client successfully created");
        }

    }

    public ResponseEntity<Client> logInClient(CreateLoginRequest loginRequest){

        if(!repository.existsByEmailEquals(loginRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Client client = repository.findByEmail(loginRequest.getEmail());
        if(!passwordEncoder.matches(loginRequest.getPassword(), client.getPassword() )){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Token", String.valueOf(client.getId()));
        return ResponseEntity.ok()
                .headers(responseHeaders).build();
    };


}
