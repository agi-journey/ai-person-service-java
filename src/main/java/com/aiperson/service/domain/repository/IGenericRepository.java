package com.aiperson.service.domain.repository;

import java.util.Optional;

public interface IGenericRepository<E, ID> {

    E save(E entity);

    Optional<E> findDomainById(ID id);

    void deleteEntity(E entity);

    boolean existsById(ID id);
}