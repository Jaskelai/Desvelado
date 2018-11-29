package services;

import dao.LikeDao;
import dao.LikeDaoJdbcImpl;
import entities.User;
import entities.Video;
import javafx.util.Pair;

import java.util.List;

public class LikeService {
    private static LikeService likeService;
    private LikeDao likeDao = new LikeDaoJdbcImpl();

    public static LikeService getLikeServiceInstance() {
        if (likeService == null) {
            likeService = new LikeService();
        }
        return likeService;
    }

    private LikeService() {
    }

    public List<Video> getLikedVideos(String username) {
        return likeDao.findByUser(UserService.getUserServiceInstance().findIdByUsername(username));
    }

    public void save(User user, Video video) {
        likeDao.save(new Pair<>(user, video));
    }

    public void remove(User user, Video video) {
        likeDao.delete(new Pair<>(user,video));
    }
    public boolean contains(User user, Video video) {
        List<Video> videos = likeDao.findByUser(UserService.getUserServiceInstance().findIdByUsername(user.getUsername()));
        for (Video x : videos) {
            if (x.equals(video)) {
                return true;
            }
        }
        return false;
    }
}
