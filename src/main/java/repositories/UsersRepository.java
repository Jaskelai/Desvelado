package repositories;

import entities.User;

public interface UsersRepository {
    void save(User user);
    boolean isExist(User user);
}
