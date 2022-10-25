package com.api.carrental.dtos;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClientDto {

    @NotBlank
    @Size(max = 11)
    @CPF
    private String cpf;
    @NotBlank
    @Size(max = 130)
    private String name;
    @NotBlank
    @Email
    private String email;

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
}
