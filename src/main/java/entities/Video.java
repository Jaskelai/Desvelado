package entities;

import java.util.Objects;

public class Video {
    private String youtubeId;
    private String usernameOwner;
    private int likes;
    private String description;
    private String headerVideo;
    private Long dateVideo;
    private boolean like;


    public Video(String youtubeId, String usernameOwner, int likes, String headerVideo, Long dateVideo, String description) {
        this.youtubeId = youtubeId;
        this.usernameOwner = usernameOwner;
        this.likes = likes;
        this.headerVideo = headerVideo;
        this.dateVideo = dateVideo;
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

    public String getHeaderVideo() {
        return headerVideo;
    }

    public void setHeaderVideo(String headerVideo) {
        this.headerVideo = headerVideo;
    }

    public Long getDateVideo() {
        return dateVideo;
    }

    public void setDateVideo(Long dateVideo) {
        this.dateVideo = dateVideo;
    }

    public boolean getLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
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
                Objects.equals(headerVideo, video.headerVideo) &&
                Objects.equals(dateVideo, video.dateVideo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(youtubeId, usernameOwner, likes, description, headerVideo, dateVideo);
    }

    @Override
    public String toString() {
        return "Video{" +
                "youtubeId='" + youtubeId + '\'' +
                ", usernameOwner='" + usernameOwner + '\'' +
                ", likes=" + likes +
                ", description='" + description + '\'' +
                ", headerVideo='" + headerVideo + '\'' +
                ", date=" + dateVideo + ", like =" + like +
                '}';
    }
}
