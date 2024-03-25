package com.kb.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kb.api.model.Client;
import com.kb.api.repository.ClientRepository;

@Service
public class ClientService {

    
    
    private ClientRepository clientRepository;
    
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    
    public Client updateClient(Client client) {
        return clientRepository.findById(client.getId()).map(c -> {
            c.setLastName(client.getLastName());
            c.setFirstName(client.getFirstName());
            c.setBirthDate(client.getBirthDate());
            c.setAddress(client.getAddress());
            c.setJob(client.getJob());
            c.setIncome(client.getIncome());
            return clientRepository.save(c);
        }).orElse(null);
    }

    public boolean deleteClient(int id) {
        return clientRepository.findById(id).map(c -> {
            clientRepository.delete(c);
            return true;
        }).orElse(false);
    }

    public Client getClient(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }
}
