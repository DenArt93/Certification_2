import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Company;
import model.Employee;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class ApiUrl {
    public static final String BASE_URL = "https://x-clients-be.onrender.com/company";

    public static final String AUTH = "https://x-clients-be.onrender.com/auth/login";

    public static final String EMPLOYEE_URL = "https://x-clients-be.onrender.com/employee";

    public static String TOKEN;


    public static String getToken(String login, String pass) {
        String creds = "{\"username\": \"" + login + "\",\"password\": \"" + pass + "\"}";
        TOKEN = given()
                .log().all()
                .body(creds)
                .contentType(ContentType.JSON)
                .when().post(AUTH)
                .then().log().all()
                .statusCode(201)
                .extract().path("userToken");

        return TOKEN;
    }

    public static int createNewCompany(Company companyName) {
        return given()
                .log().all()
                .body(companyName)
                .header("x-client-token", TOKEN)
                .contentType(ContentType.JSON)
                .when().post(BASE_URL)
                .then()
                .log().all()
                .statusCode(201)
                .body("id", greaterThan(0))
                .extract().path("id");
    }

    public static int createNewEmployee(Employee employee) {
        return given()
                .log().all()
                .body(employee)
                .header("x-client-token", TOKEN)
                .contentType(ContentType.JSON)
                .when().post(EMPLOYEE_URL)
                .then()
                .log().all()
                .statusCode(201)
                .contentType("application/json; charset=utf-8")
                .body("id", greaterThan(1))
                .extract().path("id");
    }

    public static Employee patchEmployeeInfo(int employeeId, String patchEmployeeParams) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("x-client-token", TOKEN)
                .body(patchEmployeeParams)
                .when()
                .patch(EMPLOYEE_URL + "/" + employeeId)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(Employee.class);
    }

    public static Employee getEmployeeInfo(int employeeId) {
        Response response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(EMPLOYEE_URL + "/" + employeeId)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        return response.body().as(Employee.class);
    }

    public static List<Employee> getListOfEmployees(int companyId) {
        Response response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(EMPLOYEE_URL + "?company=" + companyId)
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        return response.body().jsonPath()
                .getList(".", Employee.class);

    }


    public static Employee getEmployeeById(int idEmployee) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(EMPLOYEE_URL + "/" + idEmployee)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(Employee.class);
    }


    public static List<Employee> getAllEmployees() {

        Response response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(EMPLOYEE_URL)
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        return response.body().jsonPath()
                .getList(".", Employee.class);
    }



}
