package com.api.carrental.models;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true, length = 11)
    @CPF
    private String cpf;
    @Column(nullable = false, length = 130)
    private String name;
    @Column(nullable = false, length = 130)
    private String email;
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}