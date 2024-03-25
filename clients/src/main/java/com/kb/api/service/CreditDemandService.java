package com.kb.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kb.api.model.CreditDemand;
import com.kb.api.repository.CreditDemandRepository;

@Service
public class CreditDemandService {
    
    private CreditDemandRepository creditDemandRepository;

    public CreditDemandService(CreditDemandRepository creditDemandRepository) {
        this.creditDemandRepository = creditDemandRepository;
    }
    
    public CreditDemand createCreditDemand(CreditDemand creditDemand) {
        return creditDemandRepository.save(creditDemand);
    }

    public CreditDemand updateCreditDemand(CreditDemand creditDemand) {
        return creditDemandRepository.findById(creditDemand.getId()).map(c -> {
            c.setClientId(creditDemand.getClientId());
            c.setAdvisorId(creditDemand.getAdvisorId());
            c.setAmount(creditDemand.getAmount());
            c.setDuration(creditDemand.getDuration());
            c.setLastname(creditDemand.getLastname());
            c.setFirstName(creditDemand.getFirstName());
            c.setBirthDate(creditDemand.getBirthDate());
            c.setIncomesOnDemand(creditDemand.getIncomesOnDemand());
            c.setEmploymentOnDemand(creditDemand.getEmploymentOnDemand());
            c.setStatus(creditDemand.getStatus());
            c.setCreationDate(creditDemand.getCreationDate());
            c.setDecisionDate(creditDemand.getDecisionDate());
            return creditDemandRepository.save(c);
        }).orElse(null);
    }

    public boolean deleteCreditDemand(int id) {
        return creditDemandRepository.findById(id).map(c -> {
            creditDemandRepository.delete(c);
            return true;
        }).orElse(false);
    }

    public CreditDemand getCreditDemand(int id) {
        return creditDemandRepository.findById(id).orElse(null);
    }

    public List<CreditDemand> getCreditDemands() {
        return creditDemandRepository.findAll();
    }
}
