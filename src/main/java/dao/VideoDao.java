package dao;

import entities.Video;

public interface VideoDao extends CrudDao<Video> {
    int findLikes(int id);
}
