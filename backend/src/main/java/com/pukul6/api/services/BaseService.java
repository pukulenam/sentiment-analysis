package com.pukul6.api.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, S> {
    public Optional<T> save(T entity);

    public List<T> findAll();

    public Optional<T> update(T entity);

    public Optional<T> findById(String id);

    public T DtoToEntity(S dto);

    public List<T> DtosToEntities(List<S> dtos);
}
