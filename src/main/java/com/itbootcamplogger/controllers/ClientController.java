package com.itbootcamplogger.controllers;


import com.itbootcamplogger.controllers.model.CreateClientRequest;
import com.itbootcamplogger.servivces.ClientService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> create(@Valid @RequestBody CreateClientRequest request) {
        return clientService.createClient(request);
    }
}
