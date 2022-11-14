package demo.calendar.service.impl;

import demo.calendar.model.Calendar;
import demo.calendar.repository.CalendarRepository;
import demo.calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;


    @Override
    public List<Calendar> findAll() {
        return calendarRepository.findAll();
    }

    @Override
    public List<Calendar> findAllById(Iterable<Long> longs) {
        return calendarRepository.findAllById(longs);
    }

    @Override
    public <S extends Calendar> S saveAndFlush(S entity) {
        return calendarRepository.saveAndFlush(entity);
    }

    @Override
    @Deprecated
    public Calendar getOne(Long aLong) {
        return calendarRepository.getOne(aLong);
    }

    @Override
    @Deprecated
    public Calendar getById(Long aLong) {
        return calendarRepository.getById(aLong);
    }

    @Override
    public Page<Calendar> findAll(Specification<Calendar> spec, Pageable pageable) {
        return calendarRepository.findAll(spec, pageable);
    }

    @Override
    public <S extends Calendar> List<S> findAll(Example<S> example, Sort sort) {
        return calendarRepository.findAll(example, sort);
    }

    @Override
    public Page<Calendar> findAll(Pageable pageable) {
        return calendarRepository.findAll(pageable);
    }

    @Override
    public <S extends Calendar> S save(S entity) {
        return calendarRepository.save(entity);
    }

    @Override
    public boolean existsById(Long aLong) {
        return calendarRepository.existsById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        calendarRepository.deleteById(aLong);
    }



//    public static Specification<Calendar> hasType(String type) {
//        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("type"), "%" + type + "%"));
//    }
//
//    public static Specification<Calendar> hasLocation(String location) {
//        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("location"), "%" + location + "%"));
//    }
//
//    public static Specification<Calendar> hasOwner(String owner) {
//        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("owner"), "%" + owner + "%"));
//    }
//
//    public static Specification<Calendar> hasTitle(String title) {
//        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%"));
//    }

//    public static Specification<Calendar> hasId(long calendarId) {
//        return (root, query, cb) -> cb.equal(root.get("id"), calendarId);
//    }
}
