package com.aiperson.service.web.dto;

import java.util.List;

public record PagedResponse<T>(
    List<T> items,
    int page,
    int limit,
    long totalElements,
    int totalPages
) {}
