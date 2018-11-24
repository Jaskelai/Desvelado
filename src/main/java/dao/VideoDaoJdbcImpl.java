package dao;

import entities.Video;
import utils.DatabaseConnection;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class VideoDaoJdbcImpl implements VideoDao {
    // language=sql
    private final String SQL_SELECT_ALL = "SELECT video.video_link, video.header, video.description, video.date, userreg.username " +
            "FROM desvelado.video, desvelado.userreg WHERE video.user_id = userreg.id";
    //language=sql
    private final String SQL_SELECT_BY_ID = "SELECT video.video_link, video.header, video.description, video.date, userreg.username" +
            " FROM desvelado.video, desvelado.userreg WHERE video.id = ? AND video.user_id = userreg.id";
    //language=sql
    private final String SQL_INSERT = "INSERT INTO desvelado.video(video_link,user_id, header, description, date) VALUES (?,?,?,?,?)";
    //language=sql
    private final String SQL_SELECT_NUM_LIKES = "SELECT count(*) WHERE desvelado.likevideo.video_id = ?";

    private Connection connection;

    public VideoDaoJdbcImpl() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstanceToGetConnection();
        connection = databaseConnection.getConnection();
    }

    @Override
    public Video find(Integer id) {
        Video video = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID);
            if (resultSet.next()) {
                String link = resultSet.getString("link");
                String username = resultSet.getString("username");
                String description = resultSet.getString("description");
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(resultSet.getString("date"));
                String header = resultSet.getString("header");
                video = new Video(link, username, findLikes(id), header, date, description);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return video;
    }

    @Override
    public void save(Video model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, model.getLink());
            statement.setString(2, model.getUsernameOwner());
            statement.setString(3, model.getHeader());
            statement.setString(4, model.getDescription());
            statement.setString(5, model.getDescription());
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
                String link = resultSet.getString("link");
                int likes = resultSet.getInt("likes");
                String username = resultSet.getString("username");
                String description = resultSet.getString("description");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("date"));
                String header = resultSet.getString("header");
                Video video = new Video(link, username, likes, header, date, description);
                videos.add(video);
            }
        } catch (SQLException | ParseException e) {
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
            if (resultSet.next()) {
                num = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
