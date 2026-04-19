package com.aiperson.service.application.validator;

import com.aiperson.service.application.usecases.individual_person.create_person.command.CreateIndividualPersonCommand;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CreateIndividualPersonValidator {

    private final CpfValidatorService cpfValidatorService;

    public CreateIndividualPersonValidator(CpfValidatorService cpfValidatorService) {
        this.cpfValidatorService = cpfValidatorService;
    }

    public List<ValidationError> validate(CreateIndividualPersonCommand command) {
        List<ValidationError> errors = new ArrayList<>();

        if (command == null) {
            errors.add(new ValidationError("_root", "Command is required"));
            return errors;
        }

        if (command.tenantId() == null) {
            errors.add(new ValidationError("tenantId", "Tenant ID is required"));
        }

        if (command.createdBy() == null) {
            errors.add(new ValidationError("createdBy", "Created By is required"));
        }

        if (command.countryCode() == null || command.countryCode().isBlank()) {
            errors.add(new ValidationError("countryCode", "Country code is required"));
        }

        if (command.taxId() == null || command.taxId().isBlank()) {
            errors.add(new ValidationError("taxId", "Tax ID is required"));
        } else {
            for (var error : cpfValidatorService.validate(command.taxId())) {
                errors.add(new ValidationError(error.field(), error.message()));
            }
        }

        if (command.firstName() == null || command.firstName().isBlank()) {
            errors.add(new ValidationError("firstName", "First name is required"));
        }

        if (command.lastName() == null || command.lastName().isBlank()) {
            errors.add(new ValidationError("lastName", "Last name is required"));
        }

        if (command.birthDate() == null) {
            errors.add(new ValidationError("birthDate", "Birth date is required"));
        }

        return errors;
    }

    public record ValidationError(String field, String message) {
    }
}