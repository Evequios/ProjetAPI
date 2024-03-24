package com.kb.api.model;

import java.util.Date;
import java.util.List;

import com.kb.api.utils.CreditDemandStatus;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CreditDemand")
public class CreditDemand {

    @Id
    @GeneratedValue
    private int id;
    private int clientId;
    private int advisorId;
    private int amount;
    private int duration;
    private String lastname;
    private String firstName;
    private Date birthDate;
    private List<Integer> incomesOnDemand;
    private String employmentOnDemand;
    private CreditDemandStatus status;
    private String decision;
    private Date creationDate;
    private Date decisionDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(int advisorId) {
        this.advisorId = advisorId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Integer> getIncomeOnDemand() {
        return incomesOnDemand;
    }

    public void setIncomeOnDemand(List<Integer> incomesOnDemand) {
        this.incomesOnDemand = incomesOnDemand;
    }

    public String getEmploymentOnDemand() {
        return employmentOnDemand;
    }

    public void setEmploymentOnDemand(String employmentOnDemand) {
        this.employmentOnDemand = employmentOnDemand;
    }

    public CreditDemandStatus getStatus() {
        return status;
    }

    public void setStatus(CreditDemandStatus status) {
        this.status = status;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }
    
}
