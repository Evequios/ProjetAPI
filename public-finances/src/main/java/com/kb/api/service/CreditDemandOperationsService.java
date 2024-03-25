package com.kb.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kb.api.exceptions.BankAdvisorNotFoundException;
import com.kb.api.exceptions.CreditDemandNotFoundException;
import com.kb.api.model.BankAdvisor;
import com.kb.api.model.CreditDemand;
import com.kb.api.repository.CreditDemandOperationsRepository;
import com.kb.api.utils.CreditDemandStatus;

@Service
public class CreditDemandOperationsService {
    
    private CreditDemandOperationsRepository creditDemandOperationsRepository;
    private BankAdvisorService bankAdvisorService;

    public CreditDemandOperationsService(CreditDemandOperationsRepository creditDemandOperationsRepository, BankAdvisorService bankAdvisorService) {
        this.creditDemandOperationsRepository = creditDemandOperationsRepository;
        this.bankAdvisorService = bankAdvisorService;
    }

    public CreditDemand linkBankAdvisorToCreditDemand(int creditDemandId, int bankAdvisorId) {
        BankAdvisor bankAdvisor = bankAdvisorService.getBankAdvisor(bankAdvisorId);
        Optional.ofNullable(bankAdvisor)
                .orElseThrow(() -> new BankAdvisorNotFoundException("Bank advisor " + bankAdvisorId + " not found"));
        
        return creditDemandOperationsRepository.findById(creditDemandId).map(c -> {
            c.setAdvisorId(bankAdvisorId);
            return creditDemandOperationsRepository.save(c);
        }).orElseThrow(() -> new CreditDemandNotFoundException("Credit demand " + creditDemandId + " not found"));
    }

    public CreditDemand reviewCreditDemand(int creditDemandId) {
        CreditDemand creditDemand = creditDemandOperationsRepository.findById(creditDemandId)
                .orElseThrow(() -> new CreditDemandNotFoundException("Credit demand " + creditDemandId + " not found"));

        if(creditDemand.getStatus() == CreditDemandStatus.PENDING) {
            creditDemand.setStatus(CreditDemandStatus.REVIEWING);
            
        }else{
            throw new IllegalStateException("Credit demand " + creditDemandId + " status cannot be set as REVIEWING");
        }
        
        return creditDemandOperationsRepository.save(creditDemand);
    }
}
