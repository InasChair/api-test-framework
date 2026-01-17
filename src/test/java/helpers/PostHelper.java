package helpers;

import config.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostHelper {

    /**
     * Get all posts
     */
public static Response getAllPosts(){
    return given()
        .when()
        .get(Endpoints.POSTS)
        .then()
        .statusCode(200)
        .extract()
        .response();
}
    /**
     * Get post by ID
     */
    public static Response getPostById(int postID){
     return given()
         .pathParam("postId", postID)
        .when()
        .get(Endpoints.POST_BY_ID)
        .then()
        .statusCode(200)
        .extract()
        .response();
    }

    /**
     * Create a new post
     */
    public  static Response createPost(String requestBody){
        return given()
            .header("Content-Type","application/json")
            .body(requestBody)
            .when()
            .post(Endpoints.POSTS)
            .then()
            .statusCode(201)
            .extract()
            .response();

    }

    /**
     * update post
     */
    public static Response updatePost(int postID, String requestBody){
        return given()
            .header("Content-Type","application/json")
            .body(requestBody)
            .pathParam("postId", postID)
            .when()
            .put(Endpoints.POST_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    /**
     * Delete post
     */

    public static Response deletePost(int postID){
        return given()
            .pathParam("postId", postID)
            .when()
            .delete(Endpoints.POST_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    /**
     * Get post by ID - non-existent post (404 case)
     */
    public static Response getNonExistentPost(int postID){
        return given()
            .pathParam("postId", postID)
            .when()
            .get(Endpoints.POST_BY_ID)
            .then()
            .statusCode(404)
            .extract()
            .response();
    }

    /**
     * Get posts by user ID
     **/

   public static Response getPostsByUserId (int userID){
       return given()
            .queryParam("userId",userID)
            .when()
            .get(Endpoints.POSTS)
            .then()
            .statusCode(200)
            .extract()
            .response();
   }
}
