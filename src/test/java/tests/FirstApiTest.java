package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FirstApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetAllUsers() {
        // Make GET request and validate
        given()
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .log().all();
    }

    @Test
    public void testGetSingleUser() {
        given()
            .pathParam("userId", 1)
            .when()
            .get("/users/{userId}")
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("name", notNullValue())
            .body("email", notNullValue())
            .log().body();
    }

    @Test
    public void testGetNonExistentUser() {
        given()
            .pathParam("userId", 999)
            .when()
            .get("/users/{userId}")
            .then()
            .statusCode(404)  // We EXPECT 404 for non-existent user
            .log().body();
    }

    @Test
    public void testCreateUser() {
        String requestBody = "{\n" +
            "  \"name\": \"Inas\",\n" +
            "  \"username\": \"kuriamasun\",\n" +
            "  \"email\": \"kuriamasun@example.com\"\n" +
            "}";

        given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .when()
            .post("/users")
            .then()
            .statusCode(201)  // 201 = Created
            .body("name", equalTo("Inas"))
            .log().body();
    }

    @Test
    public void testUpdateUser() {
        String requestBody = "{\n" +
            "  \"name\": \"nanuss\",\n" +
            "  \"username\": \"updateduser\",\n" +
            "  \"email\": \"updated@example.com\"\n" +
            "}";

        given()
            .header("Content-Type", "application/json")
            .pathParam("userId", 3)
            .body(requestBody)
            .when()
            .put("/users/{userId}")
            .then()
            .statusCode(200)
            .body("name", equalTo("nanuss"))
            .log().body();
    }

    @Test
    public void testDeleteUser() {
        given()
            .pathParam("userId", 1)
            .when()
            .delete("/users/{userId}")
            .then()
            .statusCode(200)
            .log().body();
    }
}