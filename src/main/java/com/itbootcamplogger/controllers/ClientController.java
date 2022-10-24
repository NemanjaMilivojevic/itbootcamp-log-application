package com.itbootcamplogger.controllers;


import com.itbootcamplogger.controllers.model.CreateClientRequest;
import com.itbootcamplogger.model.Client;
import com.itbootcamplogger.servivces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("api/clients/register")
    public ResponseEntity<Client> create(@Valid @RequestBody CreateClientRequest request) {
        if (clientService.exists(request.getUsername()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else if (!request.isValidPassword(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            clientService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
    }
}
