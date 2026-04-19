package com.aiperson.service.infrastructure.repository;

import com.aiperson.service.domain.entity.IndividualPerson;
import com.aiperson.service.domain.repository.IIndividualPersonRepository;
import com.aiperson.service.infrastructure.entity.IndividualPersonEntity;
import com.aiperson.service.infrastructure.persistence.IndividualPersonMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class IndividualPersonRepository implements IIndividualPersonRepository, PanacheRepositoryBase<IndividualPersonEntity, UUID> {

    @Override
    public IndividualPerson save(IndividualPerson entity) {
        IndividualPersonEntity entityToSave = IndividualPersonMapper.toEntity(entity);
        persist(entityToSave);
        return IndividualPersonMapper.toDomain(entityToSave);
    }

    @Override
    public Optional<IndividualPerson> findDomainById(UUID id) {
        return find("personId", id).firstResultOptional().map(IndividualPersonMapper::toDomain);
    }

    @Override
    public void deleteEntity(IndividualPerson entity) {
        IndividualPersonEntity entityToDelete = IndividualPersonMapper.toEntity(entity);
        delete(entityToDelete);
    }

    @Override
    public boolean existsById(UUID id) {
        return count("personId", id) > 0;
    }

    @Override
    public Optional<IndividualPerson> findByPersonId(UUID personId) {
        return find("personId", personId).firstResultOptional().map(IndividualPersonMapper::toDomain);
    }

    @Override
    public boolean existsByPersonId(UUID personId) {
        return count("personId", personId) > 0;
    }
}