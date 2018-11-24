package servlets;

import entities.Video;
import services.VideoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class AddVideoServlet extends HttpServlet {
    private VideoService videoService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        videoService = new VideoService();
        String youtubeId = videoService.getYoutubeId(req.getParameter("link"));
        String header = req.getParameter("header");
        String description = req.getParameter("description");
        String username = (String)req.getSession(false).getAttribute("username");
        Video video = new Video(youtubeId,username,0,header, Calendar.getInstance(),description);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }
}
