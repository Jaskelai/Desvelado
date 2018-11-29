package services;

import dao.VideoDao;
import dao.VideoDaoJdbcImpl;
import entities.Video;
import org.json.JSONObject;
import utils.YoutubeLinkValidator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoService {
    private static VideoService videoService;
    private String idYoutubePattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
    private VideoDao videoDao = new VideoDaoJdbcImpl();

    public static VideoService getVideoServiceInstance() {
        if (videoService == null) {
            videoService = new VideoService();
        }
        return videoService;
    }

    private VideoService() {

    }


    public String getYoutubeId(String link) {
        Pattern compiledPattern = Pattern.compile(idYoutubePattern);
        Matcher matcher = compiledPattern.matcher(link);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public JSONObject save(Video video, String username) {
        JSONObject resultValidation = new JSONObject();
        if (!YoutubeLinkValidator.validate(video.getYoutubeId())) {
            resultValidation.put("errorUpload", "Wrong link to youtube!");
            return resultValidation;
        }
        video.setYoutubeId(getYoutubeId(video.getYoutubeId()));
        if (videoDao.findByYoutubeId(video.getYoutubeId()) != null) {
            resultValidation.put("errorUpload", "Video already exist!");
            return resultValidation;
        }
        videoDao.saveWUser(video,username);
        return resultValidation;
    }

    public List<Video> getAllVideos() {
        return videoDao.findAll();
    }
    public Video findByIdVid(String youtubeId) {
        return videoDao.findByYoutubeId(youtubeId);
    }

    public List<Video> getUserVideos(String username) {
        return videoDao.findByUsername(username);
    }
    public List<Video> getFavVideos(String username) {
        return LikeService.getLikeServiceInstance().getLikedVideos(username);
    }

}
