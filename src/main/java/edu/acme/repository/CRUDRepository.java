package edu.acme.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T, ID> {
    void create(T t) throws SQLException;

    List<T> findAll();

    Optional<T> findByID(ID id);

    boolean update(T t);

    boolean delete(T t);
}
