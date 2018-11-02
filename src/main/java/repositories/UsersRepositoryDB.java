package repositories;

import entities.User;

import java.sql.*;

public class UsersRepositoryDB implements UsersRepository {
    private Connection connection;
    private PreparedStatement statement;

    public UsersRepositoryDB() {
        String driverClassName = "org.postgresql.Driver";
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/desvelado_users",
                    "postgres", "0510");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(User user) {
        try {
            String sqlInsert = "INSERT INTO desvelado_user(email, password, country, gender, birthdate) VALUES (?,?,?,?,?)";
            statement = connection.prepareStatement(sqlInsert);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getCountry());
            statement.setBoolean(4, user.isGender());
            statement.setString(5, user.getBirthday());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExist(User user) {
        if (user.getPassword() != null) {
            String sqlSelect = "SELECT * FROM desvelado_user WHERE password = '" + user.getPassword() + "'";
            ResultSet rs = null;
            try {
                statement = connection.prepareStatement(sqlSelect);
                rs = statement.executeQuery();
                if (!rs.next()) {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String sqlSelect = "SELECT * FROM desvelado_user WHERE email = '" + user.getEmail() + "'";
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(sqlSelect);
            rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
