package com.itbootcamplogger.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Client")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;

    public Client(String username, String password, String email) {

        this.username = username;
        this.password = password;
        this.email = email;
    }

}
