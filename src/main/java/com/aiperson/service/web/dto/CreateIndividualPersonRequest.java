package com.aiperson.service.web.dto;

import com.aiperson.service.domain.enumerator.EGender;
import com.aiperson.service.domain.enumerator.EMaritalStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public record CreateIndividualPersonRequest(
    @JsonProperty("tenant_id")
    UUID tenantId,

    @JsonProperty("created_by")
    UUID createdBy,

    @JsonProperty("country_code")
    String countryCode,

    @JsonProperty("tax_id")
    String taxId,

    @JsonProperty("first_name")
    String firstName,

    @JsonProperty("last_name")
    String lastName,

    @JsonProperty("birth_date")
    LocalDate birthDate,

    @JsonProperty("gender")
    EGender gender,

    @JsonProperty("marital_status")
    EMaritalStatus maritalStatus,

    @JsonProperty("state_id")
    String stateId
) {}