package com.aiperson.service.domain.repository;

import com.aiperson.service.application.query.PagedQuery;
import com.aiperson.service.application.query.PagedResult;
import com.aiperson.service.application.usecases.individual_person.list_people.response.ListIndividualPersonResponse;
import com.aiperson.service.domain.entity.IndividualPerson;

import java.util.Optional;
import java.util.UUID;

public interface IIndividualPersonRepository extends IGenericRepository<IndividualPerson, UUID> {

    Optional<IndividualPerson> findByPersonId(UUID personId);

    boolean existsByPersonId(UUID personId);

    PagedResult<ListIndividualPersonResponse> findPaginated(PagedQuery pagedQuery);
}