package dao;

import entities.Video;
import exceptions.DBException;

import java.util.List;

public interface VideoDao extends CrudDao<Video> {
    int findLikes(int id) throws DBException;

    Video findByYoutubeId(String youtubeId) throws DBException;

    int findIdByYoutubeId(String link) throws DBException;

    void saveWUser(Video video, String username) throws DBException;

    List<Video> findByUsername(String username) throws DBException;
}
