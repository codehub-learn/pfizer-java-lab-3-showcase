package edu.acme.service;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T, ID> {
    void create(T t) throws Exception;

    List<T> findAll() throws Exception;

    Optional<T> findByID(ID id) throws Exception;

    boolean update(T t) throws Exception;

    boolean delete(T t) throws Exception;
}
