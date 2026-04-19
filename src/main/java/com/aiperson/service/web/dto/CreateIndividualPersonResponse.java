package com.aiperson.service.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateIndividualPersonResponse(
    @JsonProperty("person_id")
    UUID personId,

    @JsonProperty("first_name")
    String firstName,

    @JsonProperty("last_name")
    String lastName,

    @JsonProperty("tax_id")
    String taxId,

    @JsonProperty("country_code")
    String countryCode,

    @JsonProperty("created_at")
    LocalDateTime createdAt
) {}