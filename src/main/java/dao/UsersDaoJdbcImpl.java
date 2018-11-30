package dao;

import entities.User;
import exceptions.DBException;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDaoJdbcImpl implements UsersDao {
    //language=sql
    private final String SQL_SELECT_ALL = "SELECT * FROM desvelado.userreg";
    //language=sql
    private final String SQL_SELECT_BY_EMAIL = "SELECT * FROM desvelado.userreg WHERE email = ?";
    //language=sql
    private final String SQL_SELECT_BY_USERNAME = "SELECT * FROM desvelado.userreg WHERE username = ?";
    //language=sql
    private final String SQL_SELECT_ID_BY_USERNAME = "SELECT id FROM desvelado.userreg WHERE username = ?";
    //language=sql
    private final String SQL_SELECT_BY_TOKEN = "SELECT * FROM desvelado.userreg WHERE token = ?";
    //language=sql
    private final String SQL_SELECT_BY_ID = "SELECT * FROM desvelado.userreg WHERE id = ?";
    //language=sql
    private final String SQL_INSERT = "INSERT INTO desvelado.userreg(email, username, password, country_id, gender, birthdate) VALUES (?,?,?,?,?,?)";
    //language=sql
    private final String SQL_UPDATE = "UPDATE desvelado.userreg SET password = ?, country_id = ?, gender = ?, birthdate = ?, email = ?, token = ? WHERE username = ?";
    //language=sql
    private final String SQL_DELETE = "DELETE FROM desvelado.userreg WHERE id = ?";

    private Connection connection;

    public UsersDaoJdbcImpl() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstanceToGetConnection();
        connection = databaseConnection.getConnection();
    }

    @Override
    public User findByEmail(String email) throws DBException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer countryId = resultSet.getInt("country_id");
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");
                boolean gender = resultSet.getBoolean("gender");
                Date birthdate = resultSet.getDate("birthdate");
                String country = new CountriesJdbcImpl().find(countryId);
                return new User(username, email, password, country, gender, birthdate);
            } else return null;
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public int findIdByUsername(String username) throws DBException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ID_BY_USERNAME);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else return 0;
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public User findByUsername(String username) throws DBException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_USERNAME);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer countryId = resultSet.getInt("country_id");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                boolean gender = resultSet.getBoolean("gender");
                Date birthdate = resultSet.getDate("birthdate");
                String country = new CountriesJdbcImpl().find(countryId);
                return new User(username, email, password, country, gender, birthdate);
            } else return null;
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public User findByToken(String token) throws DBException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_TOKEN);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                Integer countryId = resultSet.getInt("country_id");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                boolean gender = resultSet.getBoolean("gender");
                Date birthdate = resultSet.getDate("birthdate");
                String country = new CountriesJdbcImpl().find(countryId);
                return new User(username, email, password, country, gender, birthdate);
            } else return null;
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public User find(Integer id) throws DBException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Integer countryId = resultSet.getInt("country_id");
                boolean gender = resultSet.getBoolean("gender");
                Date birthdate = resultSet.getDate("birthdate");
                String country = new CountriesJdbcImpl().find(countryId);
                return new User(username, email, password, country, gender, birthdate);
            } else return null;
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public void save(User model) throws DBException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            int country = new CountriesJdbcImpl().findByCountry(model.getCountry());
            statement.setString(1, model.getEmail());
            statement.setString(2, model.getUsername());
            statement.setString(3, model.getPassword());
            statement.setInt(4, country);
            statement.setBoolean(5, model.isGender());
            statement.setDate(6, new java.sql.Date(model.getBirthday().getTime()));
            statement.execute();
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public void update(User model) throws DBException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            int country = new CountriesJdbcImpl().findByCountry(model.getCountry());
            statement.setString(1, model.getPassword());
            statement.setInt(2, country);
            statement.setBoolean(3, model.isGender());
            statement.setDate(4, new java.sql.Date(model.getBirthday().getTime()));
            statement.setString(5, model.getEmail());
            statement.setString(6, model.getToken());
            statement.setString(7,model.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() throws DBException {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String country = resultSet.getString("country_id");
                Boolean gender = resultSet.getBoolean("gender");
                Date birthdate = resultSet.getDate("birthdate");
                User user = new User(username, email, password, country, gender, birthdate);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DBException();
        }
        return users;
    }
}
