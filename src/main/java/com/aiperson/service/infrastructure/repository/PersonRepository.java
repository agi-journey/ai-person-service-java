package com.aiperson.service.infrastructure.repository;

import com.aiperson.service.domain.entity.Person;
import com.aiperson.service.domain.repository.IPersonRepository;
import com.aiperson.service.infrastructure.entity.PersonEntity;
import com.aiperson.service.infrastructure.persistence.PersonMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PersonRepository implements IPersonRepository, PanacheRepositoryBase<PersonEntity, UUID> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Person save(Person entity) {
        PersonEntity entityToSave = PersonMapper.toEntity(entity);
        persist(entityToSave);
        return PersonMapper.toDomain(entityToSave);
    }

    @Override
    public Optional<Person> findDomainById(UUID id) {
        return find("id", id).firstResultOptional().map(PersonMapper::toDomain);
    }

    @Override
    public void deleteEntity(Person entity) {
        PersonEntity entityToDelete = PersonMapper.toEntity(entity);
        delete(entityToDelete);
    }

    @Override
    public boolean existsById(UUID id) {
        return count("id", id) > 0;
    }

    @Override
    public Optional<Person> findByTaxIdAndCountryCode(String taxId, String countryCode) {
        return find("taxId = ?1 and countryCode = ?2", taxId, countryCode)
                .firstResultOptional()
                .map(PersonMapper::toDomain);
    }

    @Override
    public boolean existsByTaxIdAndCountryCode(String taxId, String countryCode) {
        return count("taxId = ?1 and countryCode = ?2", taxId, countryCode) > 0;
    }
}