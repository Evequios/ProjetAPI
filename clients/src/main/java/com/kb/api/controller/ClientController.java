package com.kb.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
