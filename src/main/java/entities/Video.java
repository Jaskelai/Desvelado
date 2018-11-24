package entities;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Video {
    private String youtubeId;
    private String usernameOwner;
    private int likes;
    private String description;
    private String header;
    private Calendar date;


    public Video(String youtubeId, String usernameOwner, int likes, String header, Calendar date, String description) {
        this.youtubeId = youtubeId;
        this.usernameOwner = usernameOwner;
        this.likes = likes;
        this.header = header;
        this.date = date;
        this.description = description;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getUsernameOwner() {
        return usernameOwner;
    }

    public void setUsernameOwner(String usernameOwner) {
        this.usernameOwner = usernameOwner;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return likes == video.likes &&
                Objects.equals(youtubeId, video.youtubeId) &&
                Objects.equals(usernameOwner, video.usernameOwner) &&
                Objects.equals(description, video.description) &&
                Objects.equals(header, video.header) &&
                Objects.equals(date, video.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(youtubeId, usernameOwner, likes, description, header, date);
    }
}
