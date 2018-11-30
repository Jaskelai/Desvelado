package servlets;

import entities.Video;
import exceptions.DBException;
import services.LikeService;
import services.VideoService;
import utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VideoCatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Helper.checkSession(req);
            VideoService videoService = VideoService.getVideoServiceInstance();
            List<Video> videos = videoService.getAllVideos();
            if (req.getSession(false) != null) {
                LikeService likeService = LikeService.getLikeServiceInstance();
                List<Video> likedVideos = likeService.getLikedVideos((String) req.getSession(false).getAttribute("username"));
                for (Video video : likedVideos) {
                    videos.get(videos.indexOf(video)).setLike(true);
                }
                req.setAttribute("videos", videos);
                for (Video video : likedVideos) {
                    video.setLike(false);
                }
            } else {
                req.setAttribute("videos", videos);
            }
        } catch (DBException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/videoCatalog.jsp").forward(req, resp);
    }
}
