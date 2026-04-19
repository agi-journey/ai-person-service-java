package com.aiperson.service.application.query;

import java.util.List;

public record FilterCriteria(
    String field,
    FilterOperator operator,
    List<String> values
) {}
