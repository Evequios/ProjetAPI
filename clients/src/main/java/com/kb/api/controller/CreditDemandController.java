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

import com.kb.api.model.CreditDemand;
import com.kb.api.service.CreditDemandService;

@RestController
@RequestMapping("/api/creditdemands")
public class CreditDemandController {
    
    private final CreditDemandService creditDemandService;

    public CreditDemandController(CreditDemandService creditDemandService) {
        this.creditDemandService = creditDemandService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<CreditDemand>>> getCreditDemands() {
        List<EntityModel<CreditDemand>> creditDemands = creditDemandService.getCreditDemands().stream()
                .map(creditDemand -> EntityModel.of(creditDemand,
                        linkTo(methodOn(CreditDemandController.class).getCreditDemands()).withSelfRel(),
                        linkTo(methodOn(CreditDemandController.class).getCreditDemands()).withRel("creditdemands")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(creditDemands,
                linkTo(methodOn(CreditDemandController.class).getCreditDemands()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CreditDemand>> getCreditDemand(@PathVariable int id) {
        CreditDemand creditDemand = creditDemandService.getCreditDemand(id);
        return ResponseEntity.ok(EntityModel.of(creditDemand,
                linkTo(methodOn(CreditDemandController.class).getCreditDemand(id)).withSelfRel(),
                linkTo(methodOn(CreditDemandController.class).getCreditDemands()).withRel("creditdemands")));
    }

    @PostMapping
    public ResponseEntity<EntityModel<CreditDemand>> createCreditDemand(@RequestBody CreditDemand creditDemand) {
        return ResponseEntity.ok(EntityModel.of(creditDemandService.createCreditDemand(creditDemand),
                linkTo(methodOn(CreditDemandController.class).getCreditDemand(creditDemand.getId())).withSelfRel(),
                linkTo(methodOn(CreditDemandController.class).getCreditDemands()).withRel("creditdemands")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CreditDemand>> updateCreditDemand(@PathVariable int id, @RequestBody CreditDemand creditDemand) {
        creditDemand.setId(id);
        return ResponseEntity.ok(EntityModel.of(creditDemandService.updateCreditDemand(creditDemand),
                linkTo(methodOn(CreditDemandController.class).getCreditDemand(id)).withSelfRel(),
                linkTo(methodOn(CreditDemandController.class).getCreditDemands()).withRel("creditdemands")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditDemand(@PathVariable int id) {
        return creditDemandService.deleteCreditDemand(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}
