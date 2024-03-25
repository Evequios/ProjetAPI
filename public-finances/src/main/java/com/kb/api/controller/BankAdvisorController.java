package com.kb.api.controller;

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

import java.util.List;
import java.util.stream.Collectors;

import com.kb.api.model.BankAdvisor;
import com.kb.api.service.BankAdvisorService;

@RestController
@RequestMapping("/api/bankadvisors")
public class BankAdvisorController {
    
    private final BankAdvisorService bankAdvisorService;

    public BankAdvisorController(BankAdvisorService bankAdvisorService) {
        this.bankAdvisorService = bankAdvisorService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<BankAdvisor>>> getBankAdvisors() {
        List<EntityModel<BankAdvisor>> bankAdvisors = bankAdvisorService.getBankAdvisors().stream()
                .map(bankAdvisor -> EntityModel.of(bankAdvisor,
                        linkTo(methodOn(BankAdvisorController.class).getBankAdvisors()).withSelfRel(),
                        linkTo(methodOn(BankAdvisorController.class).getBankAdvisors()).withRel("bankadvisors")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(bankAdvisors,
                linkTo(methodOn(BankAdvisorController.class).getBankAdvisors()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BankAdvisor>> getBankAdvisor(@PathVariable int id) {
        BankAdvisor bankAdvisor = bankAdvisorService.getBankAdvisor(id);
        return ResponseEntity.ok(EntityModel.of(bankAdvisor,
                linkTo(methodOn(BankAdvisorController.class).getBankAdvisor(id)).withSelfRel(),
                linkTo(methodOn(BankAdvisorController.class).getBankAdvisors()).withRel("bankadvisors")));
    }

    @PostMapping
    public ResponseEntity<EntityModel<BankAdvisor>> createBankAdvisor(@RequestBody BankAdvisor bankAdvisor) {
        return ResponseEntity.ok(EntityModel.of(bankAdvisorService.createBankAdvisor(bankAdvisor),
                linkTo(methodOn(BankAdvisorController.class).getBankAdvisor(bankAdvisor.getId())).withSelfRel(),
                linkTo(methodOn(BankAdvisorController.class).getBankAdvisors()).withRel("bankadvisors")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<BankAdvisor>> updateBankAdvisor(@PathVariable int id, @RequestBody BankAdvisor bankAdvisor) {
        bankAdvisor.setId(id);
        return ResponseEntity.ok(EntityModel.of(bankAdvisorService.updateBankAdvisor(bankAdvisor),
                linkTo(methodOn(BankAdvisorController.class).getBankAdvisor(id)).withSelfRel(),
                linkTo(methodOn(BankAdvisorController.class).getBankAdvisors()).withRel("bankadvisors")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBankAdvisor(@PathVariable int id) {
        return bankAdvisorService.deleteBankAdvisor(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
