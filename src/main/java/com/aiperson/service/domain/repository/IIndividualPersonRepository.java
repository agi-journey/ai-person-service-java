package com.aiperson.service.domain.repository;

import com.aiperson.service.domain.entity.IndividualPerson;

import java.util.Optional;
import java.util.UUID;

public interface IIndividualPersonRepository extends IGenericRepository<IndividualPerson, UUID> {

    Optional<IndividualPerson> findByPersonId(UUID personId);

    boolean existsByPersonId(UUID personId);
}