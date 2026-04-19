package com.aiperson.service.domain.repository;

import com.aiperson.service.domain.entity.Person;

import java.util.Optional;
import java.util.UUID;

public interface IPersonRepository extends IGenericRepository<Person, UUID> {

    Optional<Person> findByTaxIdAndCountryCode(String taxId, String countryCode);

    boolean existsByTaxIdAndCountryCode(String taxId, String countryCode);
}