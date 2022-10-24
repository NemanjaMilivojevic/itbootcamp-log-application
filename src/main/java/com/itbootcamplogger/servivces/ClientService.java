package com.itbootcamplogger.servivces;

import com.itbootcamplogger.controllers.model.CreateClientRequest;
import com.itbootcamplogger.mapper.ClientMapper;
import com.itbootcamplogger.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;

    public int create(CreateClientRequest client) {
        final var model = mapper.toModel(client);

        return repository.save(model).getId();
    }

    public int exists(String username){
       return repository.findByUsername(username);
    }

}
