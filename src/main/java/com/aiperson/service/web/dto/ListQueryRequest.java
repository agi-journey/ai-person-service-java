package com.aiperson.service.web.dto;

import java.util.List;

public record ListQueryRequest(
    int page,
    int limit,
    String sortBy,
    String sortDirection,
    List<FilterCriteriaDto> filters
) {}
