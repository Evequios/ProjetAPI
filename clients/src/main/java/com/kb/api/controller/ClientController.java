package com.kb.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.kb.api.model.Client;
import com.kb.api.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService; 

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Client>>> getClients() {
        List<EntityModel<Client>> clients = clientService.getClients().stream()
                .map(client -> EntityModel.of(client,
                        linkTo(methodOn(ClientController.class).getClients()).withSelfRel(),
                        linkTo(methodOn(ClientController.class).getClients()).withRel("clients")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(clients,
                linkTo(methodOn(ClientController.class).getClients()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Client>> getClient(@PathVariable int id) {
        Client client = clientService.getClient(id);
        return ResponseEntity.ok(EntityModel.of(client,
                linkTo(methodOn(ClientController.class).getClient(id)).withSelfRel(),
                linkTo(methodOn(ClientController.class).getClients()).withRel("clients")));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Client>> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(EntityModel.of(clientService.createClient(client),
                linkTo(methodOn(ClientController.class).getClient(client.getId())).withSelfRel(),
                linkTo(methodOn(ClientController.class).getClients()).withRel("clients")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Client>> updateClient(@PathVariable int id, @RequestBody Client client) {
        client.setId(id);
        return ResponseEntity.ok(EntityModel.of(clientService.updateClient(client),
                linkTo(methodOn(ClientController.class).getClient(id)).withSelfRel(),
                linkTo(methodOn(ClientController.class).getClients()).withRel("clients")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable int id) {
        return clientService.deleteClient(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
