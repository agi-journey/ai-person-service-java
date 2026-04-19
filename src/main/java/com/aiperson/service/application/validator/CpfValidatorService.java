package com.aiperson.service.application.validator;

import com.aiperson.service.domain.util.CpfValidator;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CpfValidatorService {

    public List<ValidationError> validate(String cpf) {
        List<ValidationError> errors = new ArrayList<>();

        if (cpf == null || cpf.isBlank()) {
            errors.add(new ValidationError("taxId", "CPF is required"));
            return errors;
        }

        String cleanedCpf = CpfValidator.clean(cpf);

        if (!CpfValidator.isValid(cleanedCpf)) {
            errors.add(new ValidationError("taxId", "Invalid CPF format"));
        }

        return errors;
    }

    public record ValidationError(String field, String message) {}
}