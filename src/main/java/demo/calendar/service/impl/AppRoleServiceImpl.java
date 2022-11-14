package demo.calendar.service.impl;

import demo.calendar.model.AppRole;
import demo.calendar.repository.AppRoleRepository;
import demo.calendar.service.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class AppRoleServiceImpl implements AppRoleService {

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Override
    public List<AppRole> findAll() {
        return appRoleRepository.findAll();
    }

    @Override
    public List<AppRole> findAll(Sort sort) {
        return appRoleRepository.findAll(sort);
    }

    @Override
    public List<AppRole> findAllById(Iterable<Long> longs) {
        return appRoleRepository.findAllById(longs);
    }

    @Override
    public void flush() {
        appRoleRepository.flush();
    }

    @Override
    public <S extends AppRole> S saveAndFlush(S entity) {
        return appRoleRepository.saveAndFlush(entity);
    }

    @Override
    @Deprecated
    public AppRole getOne(Long aLong) {
        return appRoleRepository.getOne(aLong);
    }

    @Override
    @Deprecated
    public AppRole getById(Long aLong) {
        return appRoleRepository.getById(aLong);
    }

    @Override
    public <S extends AppRole> List<S> findAll(Example<S> example) {
        return appRoleRepository.findAll(example);
    }

    @Override
    public <S extends AppRole> List<S> findAll(Example<S> example, Sort sort) {
        return appRoleRepository.findAll(example, sort);
    }

    @Override
    public <S extends AppRole> S save(S entity) {
        return appRoleRepository.save(entity);
    }

    @Override
    public Optional<AppRole> findById(Long aLong) {
        return appRoleRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return appRoleRepository.existsById(aLong);
    }

    @Override
    public long count() {
        return appRoleRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        appRoleRepository.deleteById(aLong);
    }

    @Override
    public void delete(AppRole entity) {
        appRoleRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        appRoleRepository.deleteAll();
    }

    @Override
    public <S extends AppRole> long count(Example<S> example) {
        return appRoleRepository.count(example);
    }

    @Override
    public <S extends AppRole> boolean exists(Example<S> example) {
        return appRoleRepository.exists(example);
    }

    @Override
    public <S extends AppRole, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return appRoleRepository.findBy(example, queryFunction);
    }
}
