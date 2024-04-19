import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import model.Company;
import model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeApiTest {

    static int idCompany;

//    @BeforeAll
//    public static void setup() throws IOException {
//        ApiUrl.getToken("bloom", "fire-fairy");
//
//    }
//
//    @BeforeEach
//    public void addCompany() {
//        Company company = new Company("Roga and Kopyta", "Roga and Kopyta");
//        idCompany = ApiUrl.createNewCompany(company);
//
//    }
@BeforeAll
public static void setup() throws IOException {
    ApiUrl.getToken("bloom", "fire-fairy");
    Company company = new Company("Roga and Kopyta", "Roga and Kopyta");
    idCompany = ApiUrl.createNewCompany(company);
}

    @Test
    public void testCreateNewEmployee() {
        Employee employee = new Employee(true, "1999-08-11","url", idCompany);
        int idEmployee = ApiUrl.createNewEmployee(employee);
        assertNotNull(idEmployee);
    }

    //создать негативную проверку
    @Test
    public void testGetEmployeeById() {
        int idEmployee = 5600;
        Employee employee = ApiUrl.getEmployeeById(idEmployee);
        assertNotNull(employee);
    }


    //сделать тест getEmployeeInfo
    @Test
    public void testGetEmployeeInfo() {
        Employee employee = new Employee(true, "1999-08-10","url", idCompany);
        int employeeId = ApiUrl.createNewEmployee(employee);
        Employee employeeInfo = ApiUrl.getEmployeeInfo(employeeId);
        assertNotNull(employeeInfo);
    }


    @Test
    public void patchEmployees() {
        Employee employee = new Employee(true, "1999-08-10", "url", idCompany);
        int employeeId = ApiUrl.createNewEmployee(employee);
        String patchEmployee = """
                {
                "lastName": "Borov",
                  "email": "Borovoy@inbox.ru",
                  "url": "Bor",
                  "phone": "83432544343",
                  "isActive": true
                }
                """;
        Employee employee1 = ApiUrl.patchEmployeeInfo(employeeId, patchEmployee);
        Gson gson =new Gson();
        String employeeJson = gson.toJson(employee1);
        JsonPath jsonPath = new JsonPath(employeeJson);
        String lastName = jsonPath.getString("lastName");
        String email = jsonPath.getString("email");
        String url = jsonPath.getString("url");
        String phone = jsonPath.getString("phone");
        boolean isActive = jsonPath.getBoolean("isActive");

    }
    @Test
    public void testListEmployeesByIdCompany() {
        List<Employee> employees = ApiUrl.getListOfEmployees(2002);
        assertNotNull(employees);
    }

    //создать компанию с двумя сотрудниками
    @Test
    public void testCreateCompanyWithTwoEmployees() {
        Company company = new Company("Test Company", "Test Company");
        int companyId = ApiUrl.createNewCompany(company);

        Employee employee1 = new Employee(true, "1990-01-01", "url1", companyId);
        int employeeId1 = ApiUrl.createNewEmployee(employee1);

        Employee employee2 = new Employee(true, "1990-01-02", "url2", companyId);
        int employeeId2 = ApiUrl.createNewEmployee(employee2);

        assertNotNull(employeeId1);
        assertNotNull(employeeId2);
    }

    //сделай тест на компанию Roga and Kopyta с опечаткой в названии
    @Test
    public void testCompanyWithTypoInName() {
        Company company = new Company("Roga and Kopyt", "Roga and Kopyt");
        int companyId = ApiUrl.createNewCompany(company);
        assertNotNull(companyId);
    }


    @Test
    public void testSearchEmployeeByIdAndCheckPhone() {
        int idEmployee = 5600;
        Employee employee = ApiUrl.getEmployeeById(idEmployee);
        assertNotNull(employee);
        assertEquals("6513", employee.getPhone());
    }


    @Test
    public void testGetEmployeeByIdAndCheckJson() {
        int idEmployee = 5600;
        Employee employee = ApiUrl.getEmployeeById(idEmployee);
        assertNotNull(employee);
        assertEquals(5600, employee.getId());
        assertEquals("Mindi", employee.getFirstName());
        assertEquals("McGlynn", employee.getLastName());
        assertEquals("prince.grady", employee.getMiddleName());
        assertEquals(1980, employee.getCompanyId());
        assertNull(employee.getEmail());
        assertEquals("url", employee.getAvatar_url());
        assertEquals("6513", employee.getPhone());
        assertEquals("1999-08-10", employee.getBirthdate());
    }


}
