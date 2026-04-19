package com.aiperson.service.web.api.individual_person;

import com.aiperson.service.application.result.Result;
import com.aiperson.service.application.usecases.individual_person.create_person.command.CreateIndividualPersonCommand;
import com.aiperson.service.application.usecases.individual_person.create_person.handler.CreateIndividualPersonHandler;
import com.aiperson.service.web.dto.CreateIndividualPersonRequest;
import com.aiperson.service.web.dto.CreateIndividualPersonResponse;
import com.aiperson.service.web.dto.ErrorResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/v1/individuals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IndividualPersonResource {

    private static final Logger LOG = LoggerFactory.getLogger(IndividualPersonResource.class);

    private final CreateIndividualPersonHandler handler;

    @Inject
    public IndividualPersonResource(CreateIndividualPersonHandler handler) {
        this.handler = handler;
    }

    @POST
    public Response create(CreateIndividualPersonRequest request) {
        LOG.info("Received request to create individual person");

        if (request == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("BAD_REQUEST", "Request body is required", null))
                    .build();
        }

        CreateIndividualPersonCommand command = new CreateIndividualPersonCommand(
                request.tenantId(),
                request.createdBy(),
                request.countryCode(),
                request.taxId(),
                request.firstName(),
                request.lastName(),
                request.birthDate(),
                request.gender(),
                request.maritalStatus(),
                request.stateId()
        );

        var result = handler.handle(command);

        return switch (result) {
            case Result.Success<com.aiperson.service.application.usecases.individual_person.create_person.response.CreateIndividualPersonResponse> success -> {
                var data = success.data();
                LOG.info("Successfully created individual person: {}", data.personId());
                yield Response.status(Response.Status.CREATED)
                        .entity(new CreateIndividualPersonResponse(
                                data.personId(),
                                data.firstName(),
                                data.lastName(),
                                data.taxId(),
                                data.countryCode(),
                                data.createdAt()
                        ))
                        .build();
            }
            case Result.Failure<com.aiperson.service.application.usecases.individual_person.create_person.response.CreateIndividualPersonResponse> failure -> {
                LOG.warn("Failed to create individual person: {}", failure.error());
                Response.Status status = switch (failure.error()) {
                    case "VALIDATION_ERROR" -> Response.Status.BAD_REQUEST;
                    case "BUSINESS_ERROR" -> Response.Status.CONFLICT;
                    default -> Response.Status.INTERNAL_SERVER_ERROR;
                };
                yield Response.status(status)
                        .entity(new ErrorResponse(failure.error(), failure.details(), null))
                        .build();
            }
        };
    }
}