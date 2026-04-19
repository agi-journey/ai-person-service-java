package com.aiperson.service.application.usecases.individual_person.create_person.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateIndividualPersonResponse(
    UUID personId,
    String firstName,
    String lastName,
    String taxId,
    String countryCode,
    LocalDateTime createdAt
) {}