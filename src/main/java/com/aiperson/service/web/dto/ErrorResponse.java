package com.aiperson.service.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ErrorResponse(
    @JsonProperty("error")
    String error,

    @JsonProperty("message")
    String message,

    @JsonProperty("details")
    List<FieldError> details
) {

    public record FieldError(
        @JsonProperty("field")
        String field,

        @JsonProperty("message")
        String message
    ) {}
}