package com.aiperson.service.application.query;

import java.util.List;

public record PagedQuery(
    int page,
    int limit,
    String sortBy,
    String sortDirection,
    List<FilterCriteria> filters
) {}
