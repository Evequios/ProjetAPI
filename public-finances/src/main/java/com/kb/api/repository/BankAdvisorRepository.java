package com.kb.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kb.api.model.BankAdvisor;

@Repository
public interface BankAdvisorRepository extends JpaRepository<BankAdvisor, Integer>{
    
}
