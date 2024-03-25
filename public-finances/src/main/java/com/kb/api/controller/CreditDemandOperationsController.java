package com.kb.api.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kb.api.exceptions.CreditDemandNotFoundException;
import com.kb.api.exceptions.UnallowedStatusTransitionException;
import com.kb.api.model.CreditDemand;
import com.kb.api.service.CreditDemandOperationsService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/creditdemands/operations")
public class CreditDemandOperationsController {
    private final CreditDemandOperationsService creditDemandOperationsService;

    public CreditDemandOperationsController(CreditDemandOperationsService creditDemandOperationsService) {
        this.creditDemandOperationsService = creditDemandOperationsService;
    }

    @PatchMapping("/{creditDemandId}/link/{bankAdvisorId}")
    public ResponseEntity<EntityModel<CreditDemand>> linkCreditDemand(@PathVariable int creditDemandId,
            @PathVariable int bankAdvisorId) {
        return ResponseEntity.ok(EntityModel.of(
                creditDemandOperationsService.linkBankAdvisorToCreditDemand(creditDemandId, bankAdvisorId),
                linkTo(methodOn(CreditDemandOperationsController.class).linkCreditDemand(creditDemandId, bankAdvisorId))
                        .withSelfRel(),
                linkTo(methodOn(CreditDemandOperationsController.class).reviewCreditDemand(creditDemandId)).withRel("review")));
    }

    @PatchMapping("/{creditDemandId}/review")
    public ResponseEntity<EntityModel<CreditDemand>> reviewCreditDemand(@PathVariable int creditDemandId) {
        try {
            CreditDemand creditDemand = creditDemandOperationsService.reviewCreditDemand(creditDemandId);
            return ResponseEntity.ok(EntityModel.of(creditDemand,
                    linkTo(methodOn(CreditDemandOperationsController.class).reviewCreditDemand(creditDemandId))
                            .withSelfRel(),
                    linkTo(methodOn(CreditDemandOperationsController.class).validationCreditDemand(creditDemandId)).withRel("validation")));
        } catch (CreditDemandNotFoundException e) {
            throw new CreditDemandNotFoundException("Credit demand " + creditDemandId + " not found");
        } catch (IllegalStateException e) {
            throw new UnallowedStatusTransitionException("You can't review a credit demand that is not pending");
        }
    }

    @PatchMapping("/{creditDemandId}/validation")
    public ResponseEntity<EntityModel<CreditDemand>> validationCreditDemand(@PathVariable int creditDemandId) {
        try {
            CreditDemand creditDemand = creditDemandOperationsService.validationCreditDemand(creditDemandId);
            return ResponseEntity.ok(EntityModel.of(creditDemand,
                    linkTo(methodOn(CreditDemandOperationsController.class).validationCreditDemand(creditDemandId))
                            .withSelfRel(),
                    linkTo(methodOn(CreditDemandOperationsController.class).acceptCreditDemand(creditDemandId)).withRel("accept")));
        } catch (CreditDemandNotFoundException e) {
            throw new CreditDemandNotFoundException("Credit demand " + creditDemandId + " not found");
        } catch (IllegalStateException e) {
            throw new UnallowedStatusTransitionException("You can't validate a credit demand that is not reviewing");
        }
    }

    @PatchMapping("/{creditDemandId}/accept")
    public ResponseEntity<EntityModel<CreditDemand>> acceptCreditDemand(@PathVariable int creditDemandId) {
        try {
            CreditDemand creditDemand = creditDemandOperationsService.acceptCreditDemand(creditDemandId);
            return ResponseEntity.ok(EntityModel.of(creditDemand,
                    linkTo(methodOn(CreditDemandOperationsController.class).acceptCreditDemand(creditDemandId))
                            .withSelfRel()));
        } catch (CreditDemandNotFoundException e) {
            throw new CreditDemandNotFoundException("Credit demand " + creditDemandId + " not found");
        } catch (IllegalStateException e) {
            throw new UnallowedStatusTransitionException("You can't accept a credit demand that is not validated");
        }
    }

    @PatchMapping("/{creditDemandId}/refuse")
    public ResponseEntity<EntityModel<CreditDemand>> refuseCreditDemand(@PathVariable int creditDemandId) {
        try {
            CreditDemand creditDemand = creditDemandOperationsService.refuseCreditDemand(creditDemandId);
            return ResponseEntity.ok(EntityModel.of(creditDemand,
                    linkTo(methodOn(CreditDemandOperationsController.class).refuseCreditDemand(creditDemandId))
                            .withSelfRel()));
        } catch (CreditDemandNotFoundException e) {
            throw new CreditDemandNotFoundException("Credit demand " + creditDemandId + " not found");
        } catch (IllegalStateException e) {
            throw new UnallowedStatusTransitionException("You can't refuse a credit demand that is not pending, reviewing or validated");
        }
    }
}
