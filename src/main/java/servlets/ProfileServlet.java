package servlets;


import entities.Video;
import exceptions.DBException;
import services.LikeService;
import services.UserService;
import services.VideoService;
import utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Helper.checkSession(req);
            VideoService videoService = VideoService.getVideoServiceInstance();
            LikeService likeService = LikeService.getLikeServiceInstance();
            UserService userService = UserService.getUserServiceInstance();
            String username = (String) req.getSession(false).getAttribute("username");
            List<Video> ownedVideos = videoService.getUserVideos(username);
            List<Video> favVideos = videoService.getFavVideos(username);
            for (Video video : favVideos) {
                favVideos.get(favVideos.indexOf(video)).setLike(true);
            }
            req.setAttribute("favVideos", favVideos);
            for (Video video : ownedVideos) {
                if (likeService.contains(userService.findByUsername(username), video)) {
                    favVideos.get(ownedVideos.indexOf(video)).setLike(true);
                }
            }
            req.setAttribute("ownedVideos", ownedVideos);
            req.getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
        } catch (DBException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
