package servlets;

import entities.Video;
import org.json.JSONObject;
import services.VideoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;

public class AddVideoServlet extends HttpServlet {
    private VideoService videoService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        videoService = VideoService.getVideoServiceInstance();
        String youtubeLink = req.getParameter("link");
        String header = req.getParameter("header");
        String description = req.getParameter("description");
        String username = req.getSession(false).getAttribute("username").toString();
        Video video = new Video(youtubeLink, username, 0, header, System.currentTimeMillis(), description);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        JSONObject result = videoService.save(video,username);
        Writer pw = resp.getWriter();
        pw.write(result.toString());
        pw.close();
    }
}
