package helpers;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import config.Endpoints;

public class UserHelper {

    /**
     * Get all users
     */
    public static Response getAllUsers() {
        return given()
            .when()
            .get(Endpoints.USERS)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    /**
     * Get user by ID
     */
    public static Response getUserById(int userId) {
        return given()
            .pathParam("userId", userId)
            .when()
            .get(Endpoints.USER_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    /**
     * Create a new user
     */
    public static Response createUser(String requestBody) {
        return given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .when()
            .post(Endpoints.USERS)
            .then()
            .statusCode(201)
            .extract()
            .response();
    }

    /**
     * Update user
     */
    public static Response updateUser(int userId, String requestBody) {
        return given()
            .header("Content-Type", "application/json")
            .pathParam("userId", userId)
            .body(requestBody)
            .when()
            .put(Endpoints.USER_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    /**
     * Delete user
     */
    public static Response deleteUser(int userId) {
        return given()
            .pathParam("userId", userId)
            .when()
            .delete(Endpoints.USER_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    /**
     * Get user by ID - expecting failure (404)
     */
    public static Response getNonExistentUser(int userId) {
        return given()
            .pathParam("userId", userId)
            .when()
            .get(Endpoints.USER_BY_ID)
            .then()
            .statusCode(404)
            .extract()
            .response();
    }
}