package com.aiperson.service.application.usecases.individual_person.list_people.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ListIndividualPersonResponse(
    UUID personId,
    String firstName,
    String lastName,
    String taxId,
    String countryCode,
    LocalDate birthDate,
    LocalDateTime createdAt
) {}
