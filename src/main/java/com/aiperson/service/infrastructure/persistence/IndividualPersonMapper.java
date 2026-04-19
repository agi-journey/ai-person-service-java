package com.aiperson.service.infrastructure.persistence;

import com.aiperson.service.domain.entity.IndividualPerson;
import com.aiperson.service.infrastructure.entity.IndividualPersonEntity;

public class IndividualPersonMapper {

    private IndividualPersonMapper() {}

    public static IndividualPerson toDomain(IndividualPersonEntity entity) {
        if (entity == null) {
            return null;
        }
        IndividualPerson domain = new IndividualPerson();
        domain.setPersonId(entity.getPersonId());
        domain.setStateId(entity.getStateId());
        domain.setFirstName(entity.getFirstName());
        domain.setLastName(entity.getLastName());
        domain.setBirthDate(entity.getBirthDate());
        domain.setGender(entity.getGender());
        domain.setMaritalStatus(entity.getMaritalStatus());
        domain.setTenantId(entity.getTenantId());
        domain.setCreatedBy(entity.getCreatedBy());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedBy(entity.getUpdatedBy());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    public static IndividualPersonEntity toEntity(IndividualPerson domain) {
        if (domain == null) {
            return null;
        }
        IndividualPersonEntity entity = new IndividualPersonEntity();
        entity.setPersonId(domain.getPersonId());
        entity.setStateId(domain.getStateId());
        entity.setFirstName(domain.getFirstName());
        entity.setLastName(domain.getLastName());
        entity.setBirthDate(domain.getBirthDate());
        entity.setGender(domain.getGender());
        entity.setMaritalStatus(domain.getMaritalStatus());
        entity.setTenantId(domain.getTenantId());
        entity.setCreatedBy(domain.getCreatedBy());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedBy(domain.getUpdatedBy());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }
}