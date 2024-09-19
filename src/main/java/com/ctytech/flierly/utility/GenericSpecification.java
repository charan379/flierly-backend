package com.ctytech.flierly.utility;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked", "rawtypes"})
public class GenericSpecification<T> {

    public static <T> Specification<T> getEntityByFilter(Map<String, Object> filter) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Handle $or condition if present
            if (filter.containsKey("$or")) {
                List<Map<String, Object>> orConditions = (List<Map<String, Object>>) filter.get("$or");
                List<Predicate> orPredicates = new ArrayList<>();

                for (Map<String, Object> orCondition : orConditions) {
                    Predicate orPredicate = handleCondition(root, cb, orCondition);
                    orPredicates.add(orPredicate);
                }
                predicates.add(cb.or(orPredicates.toArray(new Predicate[0])));
            }

            // Handle $not condition if present
            if (filter.containsKey("$not")) {
                Map<String, Object> notConditions = (Map<String, Object>) filter.get("$not");
                Predicate notPredicate = handleNotCondition(root, cb, notConditions);
                predicates.add(cb.not(notPredicate));
            }

            // Handle other filters
            for (Map.Entry<String, Object> entry : filter.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();

                if (!field.equals("$or") && !field.equals("$not")) {
                    Predicate conditionPredicate = handleCondition(root, cb, Map.of(field, value));
                    predicates.add(conditionPredicate);
                }
            }

            // Apply the global AND condition (all conditions must match)
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static <T> Predicate handleCondition(Root<T> root, CriteriaBuilder cb, Map<String, Object> conditionMap) {
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> conditionEntry : conditionMap.entrySet()) {
            String field = conditionEntry.getKey();
            Object value = conditionEntry.getValue();

            // If value is a Map, treat it as an operator condition
            if (value instanceof Map) {
                Map<String, Object> conditions = (Map<String, Object>) value;

                for (Map.Entry<String, Object> condition : conditions.entrySet()) {
                    String operator = condition.getKey();
                    Object conditionValue = condition.getValue();

                    switch (operator) {
                        case "$in" -> { // IN condition
                            if (conditionValue instanceof List) {
                                predicates.add(root.get(field).in((List<?>) conditionValue));
                            }
                        }
                        case "$notIn" -> { // NOT IN condition
                            if (conditionValue instanceof List) {
                                predicates.add(cb.not(root.get(field).in((List<?>) conditionValue)));
                            }
                        }
                        case "$gte" -> // Greater than or equal to (>=)
                                predicates.add(cb.greaterThanOrEqualTo(root.get(field), (Comparable) conditionValue));
                        case "$lte" -> // Less than or equal to (<=)
                                predicates.add(cb.lessThanOrEqualTo(root.get(field), (Comparable) conditionValue));
                        case "$gt" -> // Greater than (>)
                                predicates.add(cb.greaterThan(root.get(field), (Comparable) conditionValue));
                        case "$lt" -> // Less than (<)
                                predicates.add(cb.lessThan(root.get(field), (Comparable) conditionValue));
                        case "$isNull" -> { // IS NULL condition
                            if ((Boolean) conditionValue) {
                                predicates.add(cb.isNull(root.get(field)));
                            }
                        }
                        case "$isNotNull" -> { // IS NOT NULL condition
                            if ((Boolean) conditionValue) {
                                predicates.add(cb.isNotNull(root.get(field)));
                            }
                        }
                        case "$ne" -> // Not equal (<>)
                                predicates.add(cb.notEqual(root.get(field), conditionValue));
                        case "$between" -> { // BETWEEN query
                            if (conditionValue instanceof List) {
                                List<Comparable> betweenValues = (List<Comparable>) conditionValue;
                                if (betweenValues.size() == 2) {
                                    predicates.add(cb.between(root.get(field), betweenValues.get(0), betweenValues.get(1)));
                                }
                            }
                        }
                        case "$startsWith" -> // String starts with
                                predicates.add(cb.like(root.get(field), conditionValue + "%"));
                        case "$endsWith" -> // String ends with
                                predicates.add(cb.like(root.get(field), "%" + conditionValue));
                        case "$contains" -> // String contains
                                predicates.add(cb.like(root.get(field), "%" + conditionValue + "%"));
                    }
                }
            }
            // Handle regex-like matching (e.g., "/john/" → LIKE '%john%')
            else if (value instanceof String && ((String) value).startsWith("/") && ((String) value).endsWith("/")) {
                String regexValue = ((String) value).substring(1, ((String) value).length() - 1); // Remove slashes
                predicates.add(cb.like(root.get(field), "%" + regexValue + "%"));
            }
            // Handle simple text equality (if no operator is present, use equality by default)
            else if (value instanceof String) {
                predicates.add(cb.equal(root.get(field), value));
            }
        }

        // Combine all condition predicates into a single predicate
        return cb.and(predicates.toArray(new Predicate[0]));
    }

    private static <T> Predicate handleNotCondition(Root<T> root, CriteriaBuilder cb, Map<String, Object> notConditions) {
        List<Predicate> notPredicates = new ArrayList<>();

        for (Map.Entry<String, Object> notCondition : notConditions.entrySet()) {
            String field = notCondition.getKey();
            Object value = notCondition.getValue();

            // Handle the NOT condition
            Predicate notPredicate = handleCondition(root, cb, Map.of(field, value));
            notPredicates.add(notPredicate);
        }

        // Combine all NOT conditions into a single predicate
        return cb.and(notPredicates.toArray(new Predicate[0]));
    }
}