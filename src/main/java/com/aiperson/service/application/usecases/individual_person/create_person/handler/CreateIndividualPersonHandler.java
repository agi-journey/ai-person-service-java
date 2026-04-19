package com.aiperson.service.application.usecases.individual_person.create_person.handler;

import com.aiperson.service.application.result.Result;
import com.aiperson.service.application.usecases.individual_person.create_person.command.CreateIndividualPersonCommand;
import com.aiperson.service.application.usecases.individual_person.create_person.response.CreateIndividualPersonResponse;
import com.aiperson.service.application.validator.CreateIndividualPersonValidator;
import com.aiperson.service.domain.entity.IndividualPerson;
import com.aiperson.service.domain.entity.Person;
import com.aiperson.service.domain.enumerator.EPersonType;
import com.aiperson.service.domain.exception.InvalidTaxIdException;
import com.aiperson.service.domain.exception.PersonAlreadyExistsException;
import com.aiperson.service.domain.repository.IIndividualPersonRepository;
import com.aiperson.service.domain.repository.IPersonRepository;
import com.aiperson.service.domain.repository.IUnitOfWork;
import com.aiperson.service.domain.util.CpfValidator;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class CreateIndividualPersonHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CreateIndividualPersonHandler.class);

    private final IPersonRepository personRepository;
    private final IIndividualPersonRepository individualPersonRepository;
    private final IUnitOfWork unitOfWork;
    private final CreateIndividualPersonValidator validator;

    public CreateIndividualPersonHandler(
            IPersonRepository personRepository,
            IIndividualPersonRepository individualPersonRepository,
            IUnitOfWork unitOfWork,
            CreateIndividualPersonValidator validator) {
        this.personRepository = personRepository;
        this.individualPersonRepository = individualPersonRepository;
        this.unitOfWork = unitOfWork;
        this.validator = validator;
    }

    public Result<CreateIndividualPersonResponse> handle(CreateIndividualPersonCommand command) {
        LOG.info("Starting CreateIndividualPerson use case for tenant: {}", command.tenantId());

        var validationErrors = validator.validate(command);
        if (!validationErrors.isEmpty()) {
            String errors = validationErrors.stream()
                    .map(e -> e.field() + ": " + e.message())
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
            LOG.warn("Validation failed: {}", errors);
            return new Result.Failure<>("VALIDATION_ERROR", errors);
        }

        try {
            unitOfWork.beginTransaction();

            String cleanedTaxId = CpfValidator.clean(command.taxId());
            if (personRepository.existsByTaxIdAndCountryCode(cleanedTaxId, command.countryCode())) {
                LOG.warn("Person already exists with tax_id: {} and country_code: {}", cleanedTaxId,
                        command.countryCode());
                throw new PersonAlreadyExistsException(cleanedTaxId, command.countryCode());
            }

            UUID personId = UUID.randomUUID();
            LocalDateTime now = LocalDateTime.now();

            Person person = new Person();
            person.setId(personId);
            person.setType(EPersonType.INDIVIDUAL);
            person.setActive(true);
            person.setCountryCode(command.countryCode());
            person.setTaxId(cleanedTaxId);
            person.setTenantId(command.tenantId());
            person.setCreatedBy(command.createdBy());
            person.setCreatedAt(now);

            personRepository.save(person);

            IndividualPerson individualPerson = new IndividualPerson();
            individualPerson.setPersonId(personId);
            individualPerson.setFirstName(command.firstName());
            individualPerson.setLastName(command.lastName());
            individualPerson.setBirthDate(command.birthDate());
            individualPerson.setGender(command.gender());
            individualPerson.setMaritalStatus(command.maritalStatus());
            individualPerson.setStateId(command.stateId());
            individualPerson.setTenantId(command.tenantId());
            individualPerson.setCreatedBy(command.createdBy());
            individualPerson.setCreatedAt(now);

            individualPersonRepository.save(individualPerson);

            unitOfWork.commitTransaction();

            LOG.info("Successfully created individual person with id: {}", personId);

            return new Result.Success<>(new CreateIndividualPersonResponse(
                    personId,
                    command.firstName(),
                    command.lastName(),
                    cleanedTaxId,
                    command.countryCode(),
                    now));

        } catch (PersonAlreadyExistsException | InvalidTaxIdException e) {
            unitOfWork.rollbackTransaction();
            LOG.error("Business error: {}", e.getMessage());
            return new Result.Failure<>("BUSINESS_ERROR", e.getMessage());
        } catch (Exception e) {
            unitOfWork.rollbackTransaction();
            LOG.error("Unexpected error creating individual person", e);
            return new Result.Failure<>("INTERNAL_ERROR", "An unexpected error occurred");
        } finally {
            unitOfWork.closeTransaction();
        }
    }
}