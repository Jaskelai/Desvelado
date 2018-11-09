package dao;

import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountriesDaoJdbcImpl implements CrudDao<String> {

    private Connection connection;
    // language=sql
    private final String SQL_SELECT_ALL = "SELECT * FROM desvelado_country";
    //language=sql
    private final String SQL_SELECT_BY_ID = "SELECT * FROM desvelado_country WHERE id = ?";
    //language=sql
    private final String SQL_SELECT_BY_COUNTRY = "SELECT * FROM desvelado_country WHERE country = ?";

    public CountriesDaoJdbcImpl() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstanceToGetConnection();
        connection = databaseConnection.getConnection();
    }

    @Override
    public String find(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("country");
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    @Override
    public void save(String model) {
        //TODO add save for country
    }

    @Override
    public void update(String model) {
        //TODO add update for country
    }

    @Override
    public void delete(Integer id) {
        //TODO add delete for country
    }

    @Override
    public List<String> findAll() {
        List<String> countries = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                String country = resultSet.getString("country");
                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    public int findByCountry(String country) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_COUNTRY);
            statement.setString(1, country.substring(1));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
        return 0;
    }
}
