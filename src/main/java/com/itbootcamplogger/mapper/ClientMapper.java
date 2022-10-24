package com.itbootcamplogger.mapper;

import com.itbootcamplogger.controllers.model.CreateClientRequest;
import com.itbootcamplogger.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client toModel(CreateClientRequest client) {
        return new Client(client.getUsername(), client.getPassword(), client.getEmail());
    }

}
