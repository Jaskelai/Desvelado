package dao;

import entities.User;
import entities.Video;
import exceptions.DBException;
import javafx.util.Pair;

import java.util.List;

public interface LikeDao extends CrudDao<Pair<User, Video>> {
    List<Video> findByUser(int id) throws DBException;

    void delete(Pair<User, Video> pair) throws DBException;
}
