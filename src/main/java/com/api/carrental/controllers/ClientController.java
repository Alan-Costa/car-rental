package com.api.carrental.controllers;

import com.api.carrental.dtos.ClientDto;
import com.api.carrental.models.Client;
import com.api.carrental.services.ClientService;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDto clientDto) throws Exception {

        URL url = new URL("https://viacep.com.br/ws/" + clientDto.getCep() + "/json/");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        String cep = "";
        StringBuilder jsonCep = new StringBuilder();

        while ((cep = br.readLine()) != null) {
            jsonCep.append(cep);
        }

        ClientDto clientDtoAux = new Gson().fromJson(jsonCep.toString(), ClientDto.class);
        clientDto.setCep(clientDtoAux.getCep());
        clientDto.setLogradouro(clientDtoAux.getLogradouro());
        clientDto.setComplemento(clientDtoAux.getComplemento());
        clientDto.setBairro(clientDtoAux.getBairro());
        clientDto.setLocalidade(clientDtoAux.getLocalidade());
        clientDto.setUf(clientDtoAux.getUf());

        var client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        client.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") Long id) {
        Optional<Client> clientOptional = clientService.findById(id);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") Long id) {
        Optional<Client> clientOptional = clientService.findById(id);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        clientService.delete(clientOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") Long id, @RequestBody @Valid ClientDto clientDto) {
        Optional<Client> clientOptional = clientService.findById(id);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        var client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        client.setId(clientOptional.get().getId());
        client.setRegistrationDate(clientOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(client));
    }
}