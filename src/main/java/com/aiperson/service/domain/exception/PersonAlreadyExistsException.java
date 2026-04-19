package com.aiperson.service.domain.exception;

public class PersonAlreadyExistsException extends DomainException {

    public PersonAlreadyExistsException(String taxId, String countryCode) {
        super(String.format("Person with tax_id %s and country_code %s already exists", taxId, countryCode));
    }
}