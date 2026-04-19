package com.aiperson.service.infrastructure.persistence;

import com.aiperson.service.domain.entity.Person;
import com.aiperson.service.infrastructure.entity.PersonEntity;

public class PersonMapper {

    private PersonMapper() {}

    public static Person toDomain(PersonEntity entity) {
        if (entity == null) {
            return null;
        }
        Person person = new Person();
        person.setId(entity.getId());
        person.setType(entity.getType());
        person.setActive(entity.isActive());
        person.setCountryCode(entity.getCountryCode());
        person.setTaxId(entity.getTaxId());
        person.setTenantId(entity.getTenantId());
        person.setCreatedBy(entity.getCreatedBy());
        person.setCreatedAt(entity.getCreatedAt());
        person.setUpdatedBy(entity.getUpdatedBy());
        person.setUpdatedAt(entity.getUpdatedAt());
        return person;
    }

    public static PersonEntity toEntity(Person domain) {
        if (domain == null) {
            return null;
        }
        PersonEntity entity = new PersonEntity();
        entity.setId(domain.getId());
        entity.setType(domain.getType());
        entity.setActive(domain.isActive());
        entity.setCountryCode(domain.getCountryCode());
        entity.setTaxId(domain.getTaxId());
        entity.setTenantId(domain.getTenantId());
        entity.setCreatedBy(domain.getCreatedBy());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedBy(domain.getUpdatedBy());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }
}