package com.aiperson.service.domain.entity;

import com.aiperson.service.domain.enumerator.EGender;
import com.aiperson.service.domain.enumerator.EMaritalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class IndividualPerson {

    private UUID personId;
    private String stateId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private EGender gender;
    private EMaritalStatus maritalStatus;
    private UUID tenantId;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;

    public IndividualPerson() {
    }

    public IndividualPerson(
            UUID personId,
            String stateId,
            String firstName,
            String lastName,
            LocalDate birthDate,
            EGender gender,
            EMaritalStatus maritalStatus,
            UUID tenantId,
            UUID createdBy,
            LocalDateTime createdAt,
            UUID updatedBy,
            LocalDateTime updatedAt) {
        this.personId = personId;
        this.stateId = stateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.tenantId = tenantId;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public EMaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(EMaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}