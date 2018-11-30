package dao;

import exceptions.DBException;

import java.util.List;

public interface CrudDao<T> {
    T find(Integer id) throws DBException;

    void save(T model) throws DBException;

    void update(T model) throws DBException;

    void delete(Integer id) throws DBException;

    List<T> findAll() throws DBException;
}
