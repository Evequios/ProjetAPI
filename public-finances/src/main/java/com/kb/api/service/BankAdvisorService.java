package com.kb.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kb.api.model.BankAdvisor;
import com.kb.api.repository.BankAdvisorRepository;

@Service
public class BankAdvisorService {
    
    private BankAdvisorRepository bankAdvisorRepository;

    public BankAdvisorService(BankAdvisorRepository bankAdvisorRepository) {
        this.bankAdvisorRepository = bankAdvisorRepository;
    }

    public BankAdvisor createBankAdvisor(BankAdvisor bankAdvisor) {
        return bankAdvisorRepository.save(bankAdvisor);
    }
    
    public BankAdvisor updateBankAdvisor(BankAdvisor bankAdvisor) {
        return bankAdvisorRepository.findById(bankAdvisor.getId()).map(ba -> {
            ba.setLastName(bankAdvisor.getLastName());
            ba.setFirstName(bankAdvisor.getFirstName());
            return bankAdvisorRepository.save(ba);
        }).orElse(null);
    }

    public boolean deleteBankAdvisor(int id) {
        return bankAdvisorRepository.findById(id).map(ba -> {
            bankAdvisorRepository.delete(ba);
            return true;
        }).orElse(false);
    }

    public BankAdvisor getBankAdvisor(int id) {
        return bankAdvisorRepository.findById(id).orElse(null);
    }

    public List<BankAdvisor> getBankAdvisors() {
        return bankAdvisorRepository.findAll();
    }
}
