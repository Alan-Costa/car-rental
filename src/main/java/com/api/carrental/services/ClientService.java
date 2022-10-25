package com.api.carrental.services;

import com.api.carrental.models.Client;
import com.api.carrental.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public void delete(Client client) {
        clientRepository.delete(client);
    }
}