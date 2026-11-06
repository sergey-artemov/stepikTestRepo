import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class RestAssuredTest extends BaseTest {

    private static final String username = "abramsson";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://qa-stand-login.inzhenerka.tech/api";
    }

    @DisplayName("ResT ASSUREDD")
    @Test
    void runRestAssuredTest() {
        given()
                .queryParam("API_KEY", "API_KEY123")
                .when()
                .get("/users/" + username)
                .then()
                .statusCode(200)
                .body("username", equalTo(username));
    }

    @Test
    void getAdminUser() {
        given()
                .queryParam("API_KEY", "API_KEY123")
                .when()
                .get("/users/admin")
                .then()
                .statusCode(200)
                .body("username", equalTo("admin"));
    }

    @Test
    void updateUserAbramson() {
        String request = """
                {
                    "admin": false,
                    "age": 45,
                    "description": "Самый Главный Абрамссон по состоянию на %s",
                    "jobtitle": "Старший Тестировщик",
                    "name": "Абрамссон Тестер Петрович",
                    "password": "password123",
                    "username": "%s"
                }
                """;
        String requestBody = String.format(request, LocalDate.now(), username);

        given()
                .contentType("application/json")
                .queryParam("API_KEY", "API_KEY123")
                .body(requestBody)
                .when()
                .put("/users/" + username)
                .then()
                .statusCode(200);
    }

    @Test
    void createNewUser() {
        String request = """
                {
                    "admin": false,
                    "age": 45,
                    "description": "Привет! Я - студент ИнженеркаТех %s",
                    "jobtitle": "Старший Тестировщик %s на %s",
                    "name": "Владимир Тестировович %s",
                    "password": "password123",
                    "username": "%s"
                }
                """;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        String user = "atranson" + formattedDateTime;

        String requestBody = String.format(request, user, user, LocalDate.now(), user, user);

        RestAssured.baseURI = "https://qa-stand-login.inzhenerka.tech/api";
        given()
                .contentType("application/json")
                .queryParam("API_KEY", "API_KEY123")
                .body(requestBody)
       .when()
                .post("/users")
       .then()
                .statusCode(201);
    }

}

