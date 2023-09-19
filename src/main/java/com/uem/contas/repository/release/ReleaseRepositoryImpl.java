package com.uem.contas.repository.release;

import com.uem.contas.dto.ReleaseStatisticsCategoryDto;
import com.uem.contas.dto.ReleaseStatisticsDayDto;
import com.uem.contas.dto.ReleaseStatisticsPersonDto;
import com.uem.contas.model.*;
import com.uem.contas.repository.filter.ReleaseFilter;
import com.uem.contas.repository.projection.ReleaseSummary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReleaseRepositoryImpl implements ReleaseRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<ReleaseStatisticsPersonDto> byPerson(LocalDate start, LocalDate end) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<ReleaseStatisticsPersonDto> criteriaQuery = criteriaBuilder.
                createQuery(ReleaseStatisticsPersonDto.class);

        Root<Release> root = criteriaQuery.from(Release.class);

        criteriaQuery.select(criteriaBuilder.construct(ReleaseStatisticsPersonDto.class,
                root.get(Release_.type),
                root.get(Release_.person),
                criteriaBuilder.sum(root.get(Release_.value))));

        criteriaQuery.where(
                criteriaBuilder.greaterThanOrEqualTo(root.get(Release_.dueDate),
                        start),
                criteriaBuilder.lessThanOrEqualTo(root.get(Release_.dueDate),
                        end));

        criteriaQuery.groupBy(root.get(Release_.type),
                root.get(Release_.person));

        TypedQuery<ReleaseStatisticsPersonDto> typedQuery = manager
                .createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    @Override
    public List<ReleaseStatisticsCategoryDto> byCategory(LocalDate referenceMonth) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<ReleaseStatisticsCategoryDto> criteriaQuery = criteriaBuilder.
                createQuery(ReleaseStatisticsCategoryDto.class);

        Root<Release> root = criteriaQuery.from(Release.class);

        criteriaQuery.select(criteriaBuilder.construct(ReleaseStatisticsCategoryDto.class,
                root.get(Release_.category),
                criteriaBuilder.sum(root.get(Release_.value))));

        LocalDate primeiroDia = referenceMonth.withDayOfMonth(1);
        LocalDate ultimoDia = referenceMonth.withDayOfMonth(referenceMonth.lengthOfMonth());

        criteriaQuery.where(
                criteriaBuilder.greaterThanOrEqualTo(root.get(Release_.dueDate),
                        primeiroDia),
                criteriaBuilder.lessThanOrEqualTo(root.get(Release_.dueDate),
                        ultimoDia));

        criteriaQuery.groupBy(root.get(Release_.category));

        TypedQuery<ReleaseStatisticsCategoryDto> typedQuery = manager
                .createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    @Override
    public List<ReleaseStatisticsDayDto> byDay(LocalDate referenceMonth) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<ReleaseStatisticsDayDto> criteriaQuery = criteriaBuilder.
                createQuery(ReleaseStatisticsDayDto.class);

        Root<Release> root = criteriaQuery.from(Release.class);

        criteriaQuery.select(criteriaBuilder.construct(ReleaseStatisticsDayDto.class,
                root.get(Release_.type),
                root.get(Release_.dueDate),
                criteriaBuilder.sum(root.get(Release_.value))));

        LocalDate primeiroDia = referenceMonth.withDayOfMonth(1);
        LocalDate ultimoDia = referenceMonth.withDayOfMonth(referenceMonth.lengthOfMonth());

        criteriaQuery.where(
                criteriaBuilder.greaterThanOrEqualTo(root.get(Release_.dueDate),
                        primeiroDia),
                criteriaBuilder.lessThanOrEqualTo(root.get(Release_.dueDate),
                        ultimoDia));

        criteriaQuery.groupBy(root.get(Release_.type),
                root.get(Release_.dueDate));

        TypedQuery<ReleaseStatisticsDayDto> typedQuery = manager
                .createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    @Override
    public Page<Release> filter(ReleaseFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Release> criteria = builder.createQuery(Release.class);
        Root<Release> root = criteria.from(Release.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<Release> query = manager.createQuery(criteria);
        addRestrictionsOfPagination(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    @Override
    public Page<ReleaseSummary> summarize(ReleaseFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ReleaseSummary> criteria = builder.createQuery(ReleaseSummary.class);
        Root<Release> root = criteria.from(Release.class);

        criteria.select(builder.construct(ReleaseSummary.class,
                root.get(Release_.id), root.get(Release_.description),
                root.get(Release_.dueDate), root.get(Release_.dateOfPayment),
                root.get(Release_.value), root.get(Release_.type),
                root.get(Release_.category).get(Category_.name),
                root.get(Release_.person).get(Person_.name)));

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<ReleaseSummary> query = manager.createQuery(criteria);
        addRestrictionsOfPagination(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    private Predicate[] createRestrictions(ReleaseFilter filter, CriteriaBuilder builder, Root<Release> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(filter.getDescription())) {
            predicates.add(builder.like(
                    builder.lower(root.get(Release_.description)), "%" + filter.getDescription().toLowerCase() + "%"));
        }

        if (filter.getDueDateFrom() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(Release_.dueDate), filter.getDueDateFrom()));
        }

        if (filter.getDueDateTo() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(Release_.dueDate), filter.getDueDateTo()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addRestrictionsOfPagination(TypedQuery<?> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRegistration = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPageRegistration);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Long total(ReleaseFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Release> root = criteria.from(Release.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
