package dao;

import entities.User;
import exceptions.DBException;

public interface UsersDao extends CrudDao<User> {
    User findByUsername(String username) throws DBException;

    User findByToken(String token) throws DBException;

    User findByEmail(String email) throws DBException;

    int findIdByUsername(String username) throws DBException;
}
