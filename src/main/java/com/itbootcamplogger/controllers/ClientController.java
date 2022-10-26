package com.itbootcamplogger.controllers;


import com.itbootcamplogger.controllers.model.CreateClientRequest;
import com.itbootcamplogger.controllers.model.CreateLoginRequest;
import com.itbootcamplogger.model.Client;
import com.itbootcamplogger.servivces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("api/clients/register")
    public ResponseEntity<String> create(@Valid @RequestBody CreateClientRequest request) {
        var tempCreate = clientService.createClient(request);
        if(tempCreate == 1){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or email already exists");
        }
        else if(tempCreate == 2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password input incorrect");
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Client successfully created");
        }
    }
    @PostMapping("api/clients/login")
    public ResponseEntity<Client> login(@RequestBody CreateLoginRequest request){
        var tempLogin = clientService.logInClient(request);
        if(tempLogin == 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if (tempLogin == 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Token", request.getEmail());
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @GetMapping("/test2")
    public String handleRequestHeader (@RequestHeader Map<String, String> mapValues) {

        System.out.println("printing the header"+mapValues.put("Token","Mili@gmail.com"));
        return "Success";
    }
}
