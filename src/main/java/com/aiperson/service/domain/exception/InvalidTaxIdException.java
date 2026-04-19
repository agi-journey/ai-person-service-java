package com.aiperson.service.domain.exception;

public class InvalidTaxIdException extends DomainException {

    public InvalidTaxIdException(String taxId) {
        super(String.format("Invalid tax_id: %s", taxId));
    }
}