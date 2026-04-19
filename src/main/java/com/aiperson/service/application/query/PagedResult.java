package com.aiperson.service.application.query;

import java.util.List;

public record PagedResult<T>(
    List<T> items,
    int page,
    int limit,
    long totalElements,
    int totalPages
) {}
