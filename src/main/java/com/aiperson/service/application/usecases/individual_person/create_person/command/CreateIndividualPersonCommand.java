package com.aiperson.service.application.usecases.individual_person.create_person.command;

import com.aiperson.service.domain.enumerator.EGender;
import com.aiperson.service.domain.enumerator.EMaritalStatus;

import java.time.LocalDate;
import java.util.UUID;

public record CreateIndividualPersonCommand(
        UUID tenantId,
        UUID createdBy,
        String countryCode,
        String taxId,
        String firstName,
        String lastName,
        LocalDate birthDate,
        EGender gender,
        EMaritalStatus maritalStatus,
        String stateId) {
}