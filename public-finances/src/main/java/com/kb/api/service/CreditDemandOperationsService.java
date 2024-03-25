package com.kb.api.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kb.api.exceptions.BankAdvisorNotFoundException;
import com.kb.api.exceptions.CreditDemandLinkingImpossibleException;
import com.kb.api.exceptions.CreditDemandNotFoundException;
import com.kb.api.model.BankAdvisor;
import com.kb.api.model.CreditDemand;
import com.kb.api.repository.CreditDemandOperationsRepository;
import com.kb.api.utils.CreditDemandStatus;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CreditDemandOperationsService {
    
    private CreditDemandOperationsRepository creditDemandOperationsRepository;
    private BankAdvisorService bankAdvisorService;

    public CreditDemandOperationsService(CreditDemandOperationsRepository creditDemandOperationsRepository, BankAdvisorService bankAdvisorService) {
        this.creditDemandOperationsRepository = creditDemandOperationsRepository;
        this.bankAdvisorService = bankAdvisorService;
    }

    @CircuitBreaker(name = "creditDemandService", fallbackMethod = "linkBankAdvisorToCreditDemandFallback")
    public CreditDemand linkBankAdvisorToCreditDemand(int creditDemandId, int bankAdvisorId) {
        BankAdvisor bankAdvisor = bankAdvisorService.getBankAdvisor(bankAdvisorId);
        Optional.ofNullable(bankAdvisor)
                .orElseThrow(() -> new BankAdvisorNotFoundException("Bank advisor " + bankAdvisorId + " not found"));
        
        return creditDemandOperationsRepository.findById(creditDemandId).map(c -> {
            c.setAdvisorId(bankAdvisorId);
            return creditDemandOperationsRepository.save(c);
        }).orElseThrow(() -> new CreditDemandNotFoundException("Credit demand " + creditDemandId + " not found"));
    }

    public CreditDemand linkBankAdvisorToCreditDemandFallback(int creditDemandId, int bankAdvisorId, Throwable t) {
        throw new CreditDemandLinkingImpossibleException("Linking credit demand " + creditDemandId + " to bank advisor " + bankAdvisorId + " impossible. Please retry later."); 
    }

    public CreditDemand reviewCreditDemand(int creditDemandId) {
        CreditDemand creditDemand = creditDemandOperationsRepository.findById(creditDemandId)
                .orElseThrow(() -> new CreditDemandNotFoundException("Credit demand " + creditDemandId + " not found"));

        if(creditDemand.getStatus() == CreditDemandStatus.PENDING) {
            creditDemand.setStatus(CreditDemandStatus.REVIEWING);
            creditDemand.setDecisionDate(LocalDate.now());
            
        }else{
            throw new IllegalStateException("Credit demand " + creditDemandId + " status cannot be set as REVIEWING");
        }
        
        return creditDemandOperationsRepository.save(creditDemand);
    }

    public CreditDemand validationCreditDemand(int creditDemandId) {
        CreditDemand creditDemand = creditDemandOperationsRepository.findById(creditDemandId)
                .orElseThrow(() -> new CreditDemandNotFoundException("Credit demand " + creditDemandId + " not found"));

        if(creditDemand.getStatus() == CreditDemandStatus.REVIEWING) {
            creditDemand.setStatus(CreditDemandStatus.VALIDATION);
            creditDemand.setDecisionDate(LocalDate.now());
            
        }else{
            throw new IllegalStateException("Credit demand " + creditDemandId + " status cannot be set as VALIDATED");
        }
        
        return creditDemandOperationsRepository.save(creditDemand);
    }

    public CreditDemand acceptCreditDemand(int creditDemandId) {
        CreditDemand creditDemand = creditDemandOperationsRepository.findById(creditDemandId)
                .orElseThrow(() -> new CreditDemandNotFoundException("Credit demand " + creditDemandId + " not found"));

        if(creditDemand.getStatus() == CreditDemandStatus.VALIDATION) {
            creditDemand.setStatus(CreditDemandStatus.ACCEPTED);
            creditDemand.setDecisionDate(LocalDate.now());
            
        }else{
            throw new IllegalStateException("Credit demand " + creditDemandId + " status cannot be set as ACCEPTED");
        }
        
        return creditDemandOperationsRepository.save(creditDemand);
    }

    public CreditDemand refuseCreditDemand(int creditDemandId) {
        CreditDemand creditDemand = creditDemandOperationsRepository.findById(creditDemandId)
                .orElseThrow(() -> new CreditDemandNotFoundException("Credit demand " + creditDemandId + " not found"));

        switch(creditDemand.getStatus()) {
            case PENDING:
            case REVIEWING:
            case VALIDATION:
                creditDemand.setStatus(CreditDemandStatus.REFUSED);
                creditDemand.setDecisionDate(LocalDate.now());
                break;
            case ACCEPTED:
            case REFUSED:
                throw new IllegalStateException("You cannot refuse a credit demand with status " + creditDemand.getStatus().toString());
            default:
                break;
        }
        
        return creditDemandOperationsRepository.save(creditDemand);
    }
}
