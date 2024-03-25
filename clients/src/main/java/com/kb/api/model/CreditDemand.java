package com.kb.api.model;

import java.util.Date;

import com.kb.api.utils.CreditDemandStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Creditdemand")
public class CreditDemand {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "clientid")
    private int clientId;

    @Column(name = "advisorid")
    private int advisorId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "duration")
    private int duration;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "incomesondemand")
    private int incomesOnDemand;

    @Column(name = "jobondemand")
    private String jobOnDemand;

    @Column(name = "currentstatus")
    @Enumerated(EnumType.STRING)
    private CreditDemandStatus currentStatus;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "decisiondate")
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
        return lastName;
    }

    public void setLastname(String lastName) {
        this.lastName = lastName;
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

    public int getIncomesOnDemand() {
        return incomesOnDemand;
    }

    public void setIncomesOnDemand(int incomesOnDemand) {
        this.incomesOnDemand = incomesOnDemand;
    }

    public String getJobOnDemand() {
        return jobOnDemand;
    }

    public void setJobOnDemand(String jobOnDemand) {
        this.jobOnDemand = jobOnDemand;
    }

    public CreditDemandStatus getStatus() {
        return currentStatus;
    }

    public void setStatus(CreditDemandStatus currentStatus) {
        this.currentStatus = currentStatus;
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
