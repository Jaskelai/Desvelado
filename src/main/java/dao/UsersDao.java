package dao;

import entities.User;

public interface UsersDao extends CrudDao<User> {
    User findByEmail(String email);
    User findByUsername(String username);
}
