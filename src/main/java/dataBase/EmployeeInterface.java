package dataBase;

import model.DB;

import java.sql.SQLException;

public interface EmployeeInterface {

    int create(String first_name, String last_name, String middle_name, boolean is_active, int company_id,String avatar_url, String birthdate, String phone) throws SQLException;


    DB getById(int id) throws SQLException;
}
