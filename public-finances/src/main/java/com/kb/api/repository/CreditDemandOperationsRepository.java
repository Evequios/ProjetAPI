package com.kb.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kb.api.model.CreditDemand;

@Repository
public interface CreditDemandOperationsRepository extends JpaRepository<CreditDemand, Integer>{
    
}
