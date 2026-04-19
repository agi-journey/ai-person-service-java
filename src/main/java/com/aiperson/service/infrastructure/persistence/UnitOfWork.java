package com.aiperson.service.infrastructure.persistence;

import com.aiperson.service.domain.repository.IUnitOfWork;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class UnitOfWork implements IUnitOfWork {

    private static final Logger LOG = LoggerFactory.getLogger(UnitOfWork.class);

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    UserTransaction userTransaction;

    @Override
    public void beginTransaction() {
        LOG.debug("Beginning transaction");
        try {
            userTransaction.begin();
        } catch (Exception e) {
            throw new RuntimeException("Could not begin transaction", e);
        }
    }

    @Override
    public void commitTransaction() {
        LOG.debug("Committing transaction");
        try {
            userTransaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Could not commit transaction", e);
        }
    }

    @Override
    public void rollbackTransaction() {
        LOG.debug("Rolling back transaction");
        try {
            if (userTransaction.getStatus() != jakarta.transaction.Status.STATUS_NO_TRANSACTION) {
                userTransaction.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not rollback transaction", e);
        }
    }

    @Override
    public void closeTransaction() {
        LOG.debug("Closing transaction");
        try {
            if (userTransaction.getStatus() != jakarta.transaction.Status.STATUS_NO_TRANSACTION) {
                userTransaction.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not rollback transaction on close", e);
        }
        entityManager.clear();
    }
}