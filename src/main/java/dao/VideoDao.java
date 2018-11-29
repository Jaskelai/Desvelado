package dao;

import entities.Video;

import java.util.List;

public interface VideoDao extends CrudDao<Video> {
    int findLikes(int id);
    Video findByYoutubeId(String youtubeId);
    int findIdByYoutubeId(String link);
    void saveWUser(Video video,String username);
    List<Video> findByUsername(String username);
}
