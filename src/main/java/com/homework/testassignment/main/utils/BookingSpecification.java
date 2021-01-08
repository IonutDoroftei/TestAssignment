package com.homework.testassignment.main.utils;

import com.homework.testassignment.main.models.Booking;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class BookingSpecification implements Specification<Booking> {
    private SearchCriteria criteria;

    public BookingSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Booking> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        if (criteria.isApplyFilter())
            if (criteria.isDateCriteria()) {
                return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), Utils.computeRequestDate(criteria.getFirstValue().toString()));
            } else
                return builder.equal(root.<String>get(criteria.getKey()), criteria.getFirstValue().toString());
        return null;
    }
}
