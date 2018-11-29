package dao;

import entities.User;
import entities.Video;
import javafx.util.Pair;

import java.util.List;

public interface LikeDao extends CrudDao<Pair<User,Video>> {
    List<Video> findByUser(int id);
    void delete(Pair<User,Video> pair);
}
