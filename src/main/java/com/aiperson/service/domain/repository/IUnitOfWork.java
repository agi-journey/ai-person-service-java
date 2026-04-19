package com.aiperson.service.domain.repository;

public interface IUnitOfWork {

    void beginTransaction();

    void commitTransaction();

    void rollbackTransaction();

    void closeTransaction();
}