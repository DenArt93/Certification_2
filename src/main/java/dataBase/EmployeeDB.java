package dataBase;

import model.DB;

import java.sql.*;

public class EmployeeDB implements EmployeeInterface {

    private static final String INSERT_EMPLOYEE = "INSERT INTO employee(\"first_name\", \"last_name\", \"middle_name\", \"is_active\",  \"company_id\", \"avatar_url\", \"birthdate\", \"phone\")" +
            "VALUES (?,?,?,?,?,?,?,?)";

    public static final String SELECT_BY_ID = "SELECT * FROM employee where id = ?";

    private final Connection connection;

    public EmployeeDB (String connectionString, String user, String pass) throws SQLException {
        this.connection = DriverManager.getConnection(connectionString, user, pass);
    }


    @Override
    public int create(String first_name, String last_name, String middle_name, boolean is_active, int company_id, String avatar_url, String birthdate, String phone) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, first_name);
        statement.setString(2, last_name);
        statement.setString(3, middle_name);
        statement.setBoolean(4, is_active);
        statement.setInt(5, company_id);
        statement.setString(6, avatar_url);
        statement.setDate(7, Date.valueOf(birthdate));
        statement.setString(8, phone);
        statement.executeUpdate();
        ResultSet keys = statement.getGeneratedKeys();
        keys.next();
        return keys.getInt(1);
    }

    @Override
    public DB getById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new DB(
                    resultSet.getInt("id"),
                    resultSet.getBoolean("is_active"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("middle_name"),
                    resultSet.getString("create_timestamp"),
                    resultSet.getString("change_timestamp"),
                    resultSet.getString("phone"),
                    resultSet.getString("birthdate"),
                    resultSet.getString("avatar_url"),
                    resultSet.getInt("company_id"));
        } else return null;
    }
}
