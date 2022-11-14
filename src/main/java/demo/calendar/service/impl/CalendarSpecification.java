package demo.calendar.service.impl;

import demo.calendar.model.Calendar;
import org.springframework.data.jpa.domain.Specification;

public class CalendarSpecification {
    public static Specification<Calendar> hasType(String type) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("type"), "%" + type + "%"));
    }

    public static Specification<Calendar> hasLocation(String location) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("location"), "%" + location + "%"));
    }

    public static Specification<Calendar> hasOwner(String owner) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("owner"), "%" + owner + "%"));
    }

    public static Specification<Calendar> hasTitle(String title) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%"));
    }
}
