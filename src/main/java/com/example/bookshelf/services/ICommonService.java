package com.example.bookshelf.services;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface ICommonService<T> {
    @Transactional
    public T save(T entity);
    @Transactional
    public void deleteById(Long id);
    @Transactional
    public List<T> findOrCreate(List<T> entityList);
    @Transactional
    public List<T> getAll();
    @Transactional
    public T getById(Long id);
}
