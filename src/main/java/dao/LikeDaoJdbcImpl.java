package dao;

import entities.User;
import entities.Video;
import exceptions.DBException;
import javafx.util.Pair;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikeDaoJdbcImpl implements LikeDao {
    //language=sql
    private final String SQL_SELECT_BY_ID = "SELECT likevideo.video_id,likevideo.user_id FROM " +
            "desvelado.likevideo WHERE likevideo.id = ?";
    // language=sql
    private final String SQL_SELECT_ALL = "SELECT * FROM desvelado.likevideo";
    //language=sql
    private final String SQL_SELECT_BY_USERID = "SELECT likevideo.video_id FROM " +
            "desvelado.likevideo WHERE likevideo.user_id = ?";
    //language=sql
    private final String SQL_INSERT = "INSERT INTO desvelado.likevideo(video_id, user_id) VALUES (?,?)";
    //language=sql
    private final String SQL_DELETE = "DELETE FROM desvelado.likevideo WHERE id = ?";
    //language=sql
    private final String SQL_DELETE_BY_PAIR = "DELETE FROM desvelado.likevideo WHERE likevideo.user_id = ? AND likevideo.video_id = ?";

    private Connection connection;

    public LikeDaoJdbcImpl() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstanceToGetConnection();
        connection = databaseConnection.getConnection();
    }

    @Override
    public Pair<User, Video> find(Integer id) throws DBException {
        Pair<User, Video> pair = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int videoId = resultSet.getInt("video_id");
                int userId = resultSet.getInt("user_id");
                Video video = new VideoDaoJdbcImpl().find(videoId);
                User user = new UsersDaoJdbcImpl().find(userId);
                pair = new Pair<>(user, video);
            }
        } catch (SQLException e) {
            throw new DBException();
        }
        return pair;
    }

    @Override
    public void update(Pair<User, Video> model) {

    }

    @Override
    public void save(Pair<User, Video> model) throws DBException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setInt(1, new VideoDaoJdbcImpl().findIdByYoutubeId(model.getValue().getYoutubeId()));
            statement.setInt(2, new UsersDaoJdbcImpl().findIdByUsername(model.getKey().getUsername()));
            statement.execute();
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public void delete(Integer id) throws DBException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public List<Pair<User, Video>> findAll() throws DBException {
        List<Pair<User, Video>> videos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                int idUser = resultSet.getInt("user_id");
                int idVideo = resultSet.getInt("video_id");
                videos.add(new Pair<>(new UsersDaoJdbcImpl().find(idUser), new VideoDaoJdbcImpl().find(idVideo)));
            }
        } catch (SQLException e) {
            throw new DBException();
        }
        return videos;
    }

    @Override
    public List<Video> findByUser(int userid) throws DBException {
        List<Video> videos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_USERID);
            statement.setInt(1, userid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int videoId = resultSet.getInt("video_id");
                videos.add(new VideoDaoJdbcImpl().find(videoId));
            }
        } catch (SQLException e) {
            throw new DBException();
        }
        return videos;
    }

    @Override
    public void delete(Pair<User, Video> pair) throws DBException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_PAIR);
            statement.setInt(1, new UsersDaoJdbcImpl().findIdByUsername(pair.getKey().getUsername()));
            statement.setInt(2, new VideoDaoJdbcImpl().findIdByYoutubeId(pair.getValue().getYoutubeId()));
            statement.execute();
        } catch (SQLException e) {
            throw new DBException();
        }
    }

}
