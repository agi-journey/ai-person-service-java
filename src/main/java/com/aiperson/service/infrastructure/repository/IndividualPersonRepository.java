package com.aiperson.service.infrastructure.repository;

import com.aiperson.service.application.query.FilterCriteria;
import com.aiperson.service.application.query.PagedQuery;
import com.aiperson.service.application.query.PagedResult;
import com.aiperson.service.application.usecases.individual_person.list_people.response.ListIndividualPersonResponse;
import com.aiperson.service.domain.entity.IndividualPerson;
import com.aiperson.service.domain.repository.IIndividualPersonRepository;
import com.aiperson.service.infrastructure.entity.IndividualPersonEntity;
import com.aiperson.service.infrastructure.persistence.IndividualPersonMapper;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public PagedResult<ListIndividualPersonResponse> findPaginated(PagedQuery pagedQuery) {
        StringBuilder queryHql = new StringBuilder("SELECT e FROM IndividualPersonEntity e JOIN FETCH e.person p WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (pagedQuery.filters() != null) {
            for (int i = 0; i < pagedQuery.filters().size(); i++) {
                FilterCriteria filter = pagedQuery.filters().get(i);
                String field = filter.field();

                String hqlField;
                if (field.equals("taxId") || field.equals("countryCode")) {
                    hqlField = "p." + field;
                } else {
                    hqlField = "e." + field;
                }

                String paramName1 = "param" + i + "_1";
                String paramName2 = "param" + i + "_2";

                switch (filter.operator()) {
                    case EQUALS -> {
                        queryHql.append(" AND ").append(hqlField).append(" = :").append(paramName1);
                        params.put(paramName1, parseValue(field, filter.values().get(0)));
                    }
                    case NOT_EQUALS -> {
                        queryHql.append(" AND ").append(hqlField).append(" != :").append(paramName1);
                        params.put(paramName1, parseValue(field, filter.values().get(0)));
                    }
                    case CONTAINS -> {
                        queryHql.append(" AND ").append(hqlField).append(" LIKE :").append(paramName1);
                        params.put(paramName1, "%" + filter.values().get(0) + "%");
                    }
                    case NOT_CONTAINS -> {
                        queryHql.append(" AND ").append(hqlField).append(" NOT LIKE :").append(paramName1);
                        params.put(paramName1, "%" + filter.values().get(0) + "%");
                    }
                    case GREATER_THAN -> {
                        queryHql.append(" AND ").append(hqlField).append(" > :").append(paramName1);
                        params.put(paramName1, parseValue(field, filter.values().get(0)));
                    }
                    case LESS_THAN -> {
                        queryHql.append(" AND ").append(hqlField).append(" < :").append(paramName1);
                        params.put(paramName1, parseValue(field, filter.values().get(0)));
                    }
                    case BETWEEN -> {
                        queryHql.append(" AND ").append(hqlField).append(" BETWEEN :").append(paramName1).append(" AND :").append(paramName2);
                        params.put(paramName1, parseValue(field, filter.values().get(0)));
                        params.put(paramName2, parseValue(field, filter.values().get(1)));
                    }
                    case NOT_BETWEEN -> {
                        queryHql.append(" AND (").append(hqlField).append(" < :").append(paramName1).append(" OR ").append(hqlField).append(" > :").append(paramName2).append(")");
                        params.put(paramName1, parseValue(field, filter.values().get(0)));
                        params.put(paramName2, parseValue(field, filter.values().get(1)));
                    }
                }
            }
        }

        if (pagedQuery.sortBy() != null && !pagedQuery.sortBy().isEmpty()) {
            String alias = pagedQuery.sortBy().equals("taxId") || pagedQuery.sortBy().equals("countryCode") ? "p" : "e";
            String hqlField = alias + "." + pagedQuery.sortBy();
            String direction = pagedQuery.sortDirection() != null && pagedQuery.sortDirection().equalsIgnoreCase("desc") ? "DESC" : "ASC";
            queryHql.append(" ORDER BY ").append(hqlField).append(" ").append(direction);
        } else {
            queryHql.append(" ORDER BY e.createdAt DESC");
        }

        PanacheQuery<IndividualPersonEntity> query = find(queryHql.toString(), params);

        int pageIndex = Math.max(0, pagedQuery.page() - 1);
        int pageSize = Math.max(1, pagedQuery.limit());

        query.page(pageIndex, pageSize);

        long totalElements = query.count();
        List<IndividualPersonEntity> entities = query.list();

        List<ListIndividualPersonResponse> items = entities.stream().map(e ->
                new ListIndividualPersonResponse(
                        e.getPersonId(),
                        e.getFirstName(),
                        e.getLastName(),
                        e.getPerson().getTaxId(),
                        e.getPerson().getCountryCode(),
                        e.getBirthDate(),
                        e.getCreatedAt()
                )
        ).toList();

        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        return new PagedResult<>(items, pagedQuery.page(), pageSize, totalElements, totalPages);
    }

    private Object parseValue(String field, String value) {
        if (field.equals("birthDate")) {
            return java.time.LocalDate.parse(value);
        }
        if (field.equals("createdAt") || field.equals("updatedAt")) {
            return java.time.LocalDateTime.parse(value);
        }
        // Handle number types if they exist (not evident strictly, but safety)
        return value;
    }
}