package com.example.bookshelf.utils;

import java.util.List;

public interface CustomOperations<T> {
    public List<T> findOrCreate(List<T> entityList);
}
