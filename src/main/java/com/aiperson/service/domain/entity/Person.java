package com.aiperson.service.domain.entity;

import com.aiperson.service.domain.enumerator.EPersonType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Person {

    private UUID id;
    private EPersonType type;
    private boolean isActive;
    private String countryCode;
    private String taxId;
    private UUID tenantId;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;

    public Person() {
    }

    public Person(
            UUID id,
            EPersonType type,
            boolean isActive,
            String countryCode,
            String taxId,
            UUID tenantId,
            UUID createdBy,
            LocalDateTime createdAt,
            UUID updatedBy,
            LocalDateTime updatedAt) {
        this.id = id;
        this.type = type;
        this.isActive = isActive;
        this.countryCode = countryCode;
        this.taxId = taxId;
        this.tenantId = tenantId;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EPersonType getType() {
        return type;
    }

    public void setType(EPersonType type) {
        this.type = type;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
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