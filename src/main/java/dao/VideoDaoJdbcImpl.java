package dao;

import entities.Video;
import utils.DatabaseConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VideoDaoJdbcImpl implements VideoDao {
    // language=sql
    private final String SQL_SELECT_ALL = "SELECT video.id, video.youtube_link_id, video.header, video.description, video.date, userreg.username " +
            "FROM desvelado.video, desvelado.userreg WHERE video.user_id = userreg.id";
    //language=sql
    private final String SQL_SELECT_BY_ID = "SELECT video.youtube_link_id, video.header, video.description, video.date, userreg.username" +
            " FROM desvelado.video, desvelado.userreg WHERE video.id = ? AND video.user_id = userreg.id";
    //language=sql
    private final String SQL_SELECT_BY_YOUTUBE_ID = "SELECT video.id, video.youtube_link_id, video.header, video.description, video.date, userreg.username" +
            " FROM desvelado.video, desvelado.userreg WHERE video.youtube_link_id = ? AND video.user_id = userreg.id";
    //language=sql
    private final String SQL_INSERT = "INSERT INTO desvelado.video(youtube_link_id,user_id, header, description, date) VALUES (?,?,?,?,?) ";
    //language=sql
    private final String SQL_SELECT_NUM_LIKES = "SELECT * FROM desvelado.likevideo WHERE likevideo.video_id = ?";
    //language=sql
    private final String SQL_SELECT_BY_USERNAME = "SELECT * FROM desvelado.video WHERE video.user_id = ?";
    //language=sql
    private final String SQL_SELECT_ID_BY_VIDEO_LINK = "SELECT id FROM desvelado.video WHERE video.youtube_link_id = ?";

    private Connection connection;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public VideoDaoJdbcImpl() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstanceToGetConnection();
        connection = databaseConnection.getConnection();
    }

    @Override
    public Video find(Integer id) {
        Video video = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String link = resultSet.getString("youtube_link_id");
                String username = resultSet.getString("username");
                String description = resultSet.getString("description");
                long dateMillis = resultSet.getLong("date");
                String header = resultSet.getString("header");
                video = new Video(link, username, findLikes(id), header, dateMillis, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return video;
    }

    @Override
    public void save(Video model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, model.getYoutubeId());
            statement.setInt(2, 5);
            statement.setString(3, model.getHeaderVideo());
            statement.setString(4, model.getDescription());
            statement.setLong(5, model.getDateVideo());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Video model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                String link = resultSet.getString("youtube_link_id");
                String username = resultSet.getString("username");
                String description = resultSet.getString("description");
                Long dateMillis = resultSet.getLong("date");
                String header = resultSet.getString("header");
                int id = resultSet.getInt("id");
                Video video = new Video(link, username, findLikes(id), header, dateMillis, description);
                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videos;
    }

    @Override
    public int findLikes(int id) {
        int num = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_NUM_LIKES);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                num++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Video findByYoutubeId(String youtubeId) {
        Video video = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_YOUTUBE_ID);
            statement.setString(1, youtubeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String link = resultSet.getString("youtube_link_id");
                String username = resultSet.getString("username");
                String description = resultSet.getString("description");
                Long dateMillis = resultSet.getLong("date");
                String header = resultSet.getString("header");
                int id = resultSet.getInt("id");
                video = new Video(link, username, findLikes(id), header, dateMillis, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return video;
    }

    @Override
    public int findIdByYoutubeId(String link) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ID_BY_VIDEO_LINK);
            statement.setString(1, link);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    @Override
    public void saveWUser(Video video, String username) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, video.getYoutubeId());
            statement.setInt(2, new UsersDaoJdbcImpl().findIdByUsername(username));
            statement.setString(3, video.getHeaderVideo());
            statement.setString(4, video.getDescription());
            statement.setLong(5, video.getDateVideo());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Video> findByUsername(String username) {
        List<Video> videos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_USERNAME);
            statement.setInt(1, new UsersDaoJdbcImpl().findIdByUsername(username));
            System.out.println(new UsersDaoJdbcImpl().findIdByUsername(username));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String link = resultSet.getString("youtube_link_id");
                String description = resultSet.getString("description");
                Long dateMillis = resultSet.getLong("date");
                String header = resultSet.getString("header");
                int id = resultSet.getInt("id");
                Video video = new Video(link, username, findLikes(id), header, dateMillis, description);
                System.out.println(video);
                videos.add(video);
            }
            return videos;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
