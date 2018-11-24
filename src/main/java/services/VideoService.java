package services;

public class VideoService {
    public String getYoutubeId(String link) {
        String[] parts = link.split("=");
        return parts[1];
    }

}
