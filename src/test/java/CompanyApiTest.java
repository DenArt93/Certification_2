import dataBase.EmployeeDB;
import dataBase.EmployeeInterface;
import model.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class CompanyApiTest {


    private EmployeeInterface employeeInterface;


    @BeforeEach
    public void setUp() throws SQLException {
        String connectionString = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
        String user = "x_clients_user";
        String pass = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";

        employeeInterface = new EmployeeDB(connectionString, user, pass);

    }

    @Test
    void getEmployeeByID() throws SQLException {
        String employeeId = "1543";
        DB db = employeeInterface.getById(5607);
        System.out.println(db);
    }


    @Test
    void addEmployee() throws SQLException {
        int DB = employeeInterface.create("ivanov", "ivan", "ivanovich", true,1543, "ivanovka", "2004-01-01", "89222222222");
        System.out.println(DB);

    }


}