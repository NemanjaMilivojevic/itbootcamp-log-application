package com.itbootcamplogger.servivces;

import com.itbootcamplogger.controllers.model.CreateClientRequest;
import com.itbootcamplogger.controllers.model.CreateLoginRequest;
import com.itbootcamplogger.mapper.ClientMapper;
import com.itbootcamplogger.model.Client;
import com.itbootcamplogger.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public int createClient(CreateClientRequest client) {
        final var model = mapper.toClientModel(client);
        if (repository.existsByUsernameEquals(client.getUsername()) || repository.existsByEmailEquals(client.getEmail())) {
            return 1;
        } else if (!client.isPasswordValid(client.getPassword())) {
            return 2;
        } else {
            String encodedPassword = this.passwordEncoder.encode(model.getPassword());
            model.setPassword(encodedPassword);
            repository.save(model);
            return 0;
        }

    }

    public int logInClient(CreateLoginRequest loginRequest){

        if(!repository.existsByEmailEquals(loginRequest.getEmail())){
            return 1;
        }
        Client client = repository.findByEmail(loginRequest.getEmail());
        if(!passwordEncoder.matches(loginRequest.getPassword(), client.getPassword() )){
            return 2;
        }
        return 0;
    };


}
