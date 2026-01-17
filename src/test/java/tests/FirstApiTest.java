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

    @Test
    public void testExistenceOfMoreThanFiveUsers() {
       given()
           .when()
           .get("/users")
           .then()
           .statusCode(200)
           .body("size()",greaterThan(5))
           .log().all();
    }

    @Test
    public void test10ThUserHasName(){
        given()
            .pathParam("userId",10)
            .when()
            .get("/users/{userId}")
            .then()
            .statusCode(200)
            .body("name", notNullValue())
            .log().body();
    }

    @Test
    public void createMyOwnPost(){
        String newUserRequestBody = """
    {
      "name": "Inas",
      "username": "Kuriamasun",
      "userId": 21
    }
    """;
        given()
            .header("Content-type","application/json")
            .body(newUserRequestBody)
            .when()
            .post("/users")
            .then()
            .statusCode(201)
            .body("name", equalTo("Inas"))
            .log().body();
    }

    @Test
    public void createNewPostAndVerifyId(){
        String newPostRequestBody = """
    {
       "userId": 1,
       "title": "post Title",
       "body": " rerum est autem sunt rem eveniet architecto"
    }
    """;
        int newPostId =
        given()
            .header("Content-type","application/json")
            .body(newPostRequestBody)
            .when()
            .post("/posts")
            .then()
            .statusCode(201)
            .body("title", equalTo("post Title"))
            .extract()
            .path("id");
        System.out.println("Newly created post ID: " + newPostId);
    }

    @Test
    public void testMultipleTestsInOne(){
        String  newPostRequestBody = """
    {
       "userId": 1,
       "title": "post Title",
       "body": " rerum est autem sunt rem eveniet architecto"
    }
    
    """;
        // Create new post and extract ID
        int newPostId =
            given()
                .header("Content-tupe", "application-json")
                .body(newPostRequestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .path("id");
        // Verify the newly created post
        given()
            .pathParam("postId", newPostId)
            .when()
            .get("/posts/{postId}")
            .then()
            .statusCode(200)
            .body("title", equalTo("post Title"));

        // Delete the newly created post
        given()
            .pathParam("postId", newPostId)
            .when()
            .delete("/posts/{postId}")
            .then()
            .statusCode(200)
            .log().body();
    }

    @Test
    public void testExtractAllPostsOfUser(){
        int userId =
            given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .path("[0].id"); // Get ID of 1st user

        System.out.println("User ID: " + userId);

        given()
            .queryParam("userId", userId)
            .when()
            .get("/posts")
            .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("userId", everyItem(equalTo(userId)))
            .log().body();
    }

    @Test
    public void testCommentBelongsToPost(){
        int postId = 1;
        //Get post with ID = 1
        given()
            .pathParams("postId",postId)
            .when()
            .get("/posts/{postId}")
            .then()
            .statusCode(200)
            .body("id", equalTo(postId))
            .log().body();
        //Get all comments for that post (GET /comments?postId=1)
        given()
            .queryParam("postId",postId)
            .when()
            .get("/comments")
            .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("postId", everyItem(equalTo(postId)))
            .log().body();
    }
}
