package com.aiperson.service.web.dto;

import java.util.List;

public record FilterCriteriaDto(
    String field,
    String operator,
    List<String> values
) {}
