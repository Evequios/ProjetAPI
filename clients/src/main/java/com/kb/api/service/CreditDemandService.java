package com.kb.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kb.api.exceptions.CreditDemandNotFoundException;
import com.kb.api.model.CreditDemand;
import com.kb.api.repository.CreditDemandRepository;
import com.kb.api.utils.CreditDemandStatus;

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
            c.setJobOnDemand(creditDemand.getJobOnDemand());
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

    public CreditDemand cancelCreditDemand(int id) {
        CreditDemand creditDemand = creditDemandRepository.findById(id)
                .orElseThrow(() -> new CreditDemandNotFoundException("Credit demand " + id + " not found"));

        switch (creditDemand.getStatus()) {
            case PENDING:
            case REVIEWING:
            case VALIDATION:
                creditDemand.setStatus(CreditDemandStatus.REFUSED);
                creditDemand.setDecisionDate(LocalDate.now());
                break;
            case ACCEPTED:
            case REFUSED:
                throw new IllegalStateException("You cannot cancel a credit demand with status "
                        + creditDemand.getStatus().toString());
            default:
                break;
        }
        
        return creditDemandRepository.save(creditDemand);
    }
}
