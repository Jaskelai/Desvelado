package dao;

import entities.User;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDaoJdbcImpl implements UsersDao {
    //language=sql
    private final String SQL_SELECT_ALL = "SELECT * FROM desvelado_user";
    //language=sql
    private final String SQL_SELECT_BY_EMAIL = "SELECT * FROM desvelado_user WHERE email = ?";
    //language=sql
    private final String SQL_SELECT_BY_USERNAME = "SELECT * FROM desvelado_user WHERE username = ?";
    //language=sql
    private final String SQL_SELECT_BY_ID = "SELECT * FROM desvelado_user WHERE id = ?";
    //language=sql
    private final String SQL_INSERT = "INSERT INTO desvelado_user(email, username, password, country, gender, birthdate) VALUES (?,?,?,?,?,?)";
    //language=sql
    private final String SQL_UPDATE_TOKEN = "UPDATE desvelado_user SET token = ? WHERE email = ?";
    //language=sql
    private final String SQL_SELECT_BY_TOKEN = "SELECT * FROM desvelado_user WHERE token = ?";
    //private final String SQL_UPDATE = "UPDATE desvelado_user SET (email, password, country, gender, birthdate) VALUES (?,?,?,?,?)";

    private Connection connection;

    public UsersDaoJdbcImpl() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstanceToGetConnection();
        connection = databaseConnection.getConnection();
    }

    @Override
    public User findByEmail(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer countryId = resultSet.getInt("country");
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");
                Boolean gender = resultSet.getBoolean("gender");
                Date birthdate = resultSet.getDate("birthdate");
                String country = new CountriesDaoJdbcImpl().find(countryId);
                return new User(username, email, password, country, gender, birthdate);
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_USERNAME);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer countryId = resultSet.getInt("country");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                Boolean gender = resultSet.getBoolean("gender");
                Date birthdate = resultSet.getDate("birthdate");
                String country = new CountriesDaoJdbcImpl().find(countryId);
                return new User(username, email, password, country, gender, birthdate);
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    @Override
    public User find(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Integer countryId = resultSet.getInt("country");
                Boolean gender = resultSet.getBoolean("gender");
                Date birthdate = resultSet.getDate("birthdate");
                String country = new CountriesDaoJdbcImpl().find(countryId);
                return new User(username, email, password, country, gender, birthdate);
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    @Override
    public void save(User model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            int country = new CountriesDaoJdbcImpl().findByCountry(model.getCountry());
            statement.setString(1, model.getEmail());
            statement.setString(2, model.getUsername());
            statement.setString(3, model.getPassword());
            statement.setInt(4, country);
            statement.setBoolean(5, model.isGender());
            statement.setDate(6, new java.sql.Date(model.getBirthday().getTime()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User model) {
        // TODO ADD UPDATE method
    }

    @Override
    public void delete(Integer id) {
        // TODO ADD DELETE method
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String country = resultSet.getString("country");
                Boolean gender = resultSet.getBoolean("gender");
                Date birthdate = resultSet.getDate("birthdate");
                User user = new User(username, email, password, country, gender, birthdate);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public void saveToken(String token, User model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TOKEN);
            statement.setString(1, token);
            statement.setString(2,model.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findByToken(String token) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_TOKEN);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                Integer countryId = resultSet.getInt("country");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                Boolean gender = resultSet.getBoolean("gender");
                Date birthdate = resultSet.getDate("birthdate");
                String country = new CountriesDaoJdbcImpl().find(countryId);
                return new User(username, email, password, country, gender, birthdate);
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
