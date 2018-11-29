package servlets;

import entities.User;
import entities.Video;
import org.json.JSONObject;
import services.LikeService;
import services.UserService;
import services.VideoService;
import utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LikeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper.checkSession(req);
        LikeService likeService = LikeService.getLikeServiceInstance();
        UserService userService = UserService.getUserServiceInstance();
        VideoService videoService = VideoService.getVideoServiceInstance();
        boolean isLiked = Boolean.parseBoolean(req.getParameter("isLiked"));
        String idLink = req.getParameter("idLink");
        String username = (String) req.getSession(false).getAttribute("username");
        User user = userService.findByUsername(username);
        Video video = videoService.findByIdVid(idLink);
        if (isLiked) {
            if (!likeService.contains(user,video)) {
                likeService.save(user,video);
                video.setLikes(video.getLikes() + 1);
            }
        } else {
            if (likeService.contains(user,video)) {
                likeService.remove(user,video);
                video.setLikes(video.getLikes() - 1);
            }
        }
    }
}
