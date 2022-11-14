package demo.calendar.service;

import demo.calendar.model.AppRole;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface AppRoleService {
    List<AppRole> findAll();

    List<AppRole> findAll(Sort sort);

    List<AppRole> findAllById(Iterable<Long> longs);

    void flush();

    <S extends AppRole> S saveAndFlush(S entity);

    @Deprecated
    AppRole getOne(Long aLong);

    @Deprecated
    AppRole getById(Long aLong);

    <S extends AppRole> List<S> findAll(Example<S> example);

    <S extends AppRole> List<S> findAll(Example<S> example, Sort sort);

    <S extends AppRole> S save(S entity);

    Optional<AppRole> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(AppRole entity);

    void deleteAll();

    <S extends AppRole> long count(Example<S> example);

    <S extends AppRole> boolean exists(Example<S> example);

    <S extends AppRole, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
