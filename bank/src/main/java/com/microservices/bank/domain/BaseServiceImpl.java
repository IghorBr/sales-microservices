package com.microservices.bank.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseServiceImpl<T extends BaseDomain> implements BaseService<T> {

    private final BaseRepository<T> baseRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T save(T entity) {
        return (T) baseRepository.save(entity);
    }

    @Override
    public List<T> saveAll(List<T> entities) {
        return (List<T>) baseRepository.saveAll(entities);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Optional<T> findById(Long entityId) {
        return baseRepository.findById(entityId);
    }


    @Override
    public T update(T entity) {
        return (T) baseRepository.save(entity);
    }

    @Override
    public T updateById(T entity, Long entityId) {
        Optional<T> optional = baseRepository.findById(entityId);
        if(optional.isPresent()){
            return (T) baseRepository.save(entity);
        }else{
            return null;
        }
    }

    @Override
    public void delete(T entity) {
        baseRepository.delete(entity);

    }

    @Override
    public void deleteById(Long entityId) {
        baseRepository.deleteById(entityId);
    }
}
