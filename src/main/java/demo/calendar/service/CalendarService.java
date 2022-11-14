package demo.calendar.service;

import demo.calendar.model.Calendar;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface CalendarService  {



    List<Calendar> findAll();

    List<Calendar> findAllById(Iterable<Long> longs);

    <S extends Calendar> S saveAndFlush(S entity);

    @Deprecated
    Calendar getOne(Long aLong);

    @Deprecated
    Calendar getById(Long aLong);

    Page<Calendar> findAll(Specification<Calendar> spec, Pageable pageable);

    <S extends Calendar> List<S> findAll(Example<S> example, Sort sort);

    Page<Calendar> findAll(Pageable pageable);

    <S extends Calendar> S save(S entity);

    boolean existsById(Long aLong);

    void deleteById(Long aLong);
}