package com.aiperson.service.infrastructure.persistence;

import com.aiperson.service.domain.repository.IUnitOfWork;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class UnitOfWork implements IUnitOfWork {

    private static final Logger LOG = LoggerFactory.getLogger(UnitOfWork.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void beginTransaction() {
        LOG.debug("Beginning transaction");
        entityManager.getTransaction().begin();
    }

    @Override
    public void commitTransaction() {
        LOG.debug("Committing transaction");
        entityManager.getTransaction().commit();
    }

    @Override
    public void rollbackTransaction() {
        LOG.debug("Rolling back transaction");
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void closeTransaction() {
        LOG.debug("Closing transaction");
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
        entityManager.clear();
    }
}