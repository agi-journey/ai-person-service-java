package com.aiperson.service.application.usecases.individual_person.list_people.handler;

import com.aiperson.service.application.query.PagedResult;
import com.aiperson.service.application.result.Result;
import com.aiperson.service.application.usecases.individual_person.list_people.query.ListIndividualPersonQuery;
import com.aiperson.service.application.usecases.individual_person.list_people.response.ListIndividualPersonResponse;
import com.aiperson.service.domain.repository.IIndividualPersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ListIndividualPersonHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ListIndividualPersonHandler.class);

    private final IIndividualPersonRepository repository;

    @Inject
    public ListIndividualPersonHandler(IIndividualPersonRepository repository) {
        this.repository = repository;
    }

    public Result<PagedResult<ListIndividualPersonResponse>> handle(ListIndividualPersonQuery query) {
        LOG.info("Handling ListIndividualPersonQuery");
        try {
            var pagedResult = repository.findPaginated(query.pagedQuery());
            return new Result.Success<>(pagedResult);
        } catch (Exception e) {
            LOG.error("Error listing individual persons", e);
            return new Result.Failure<>("LIST_ERROR", "Failed to list individual persons");
        }
    }
}
