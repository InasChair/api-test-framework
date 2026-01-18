package helpers;

import config.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class CommentHelper {

    public static Response getAllComments(){
       return given()
            .when()
            .get(Endpoints.COMMENTS)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    public static Response getCommentById(int commentId){
        return given()
            .pathParam("commentId", commentId)
            .when()
            .get(Endpoints.COMMENTS_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }



    public static Response getCommentByPostId(int postId){
        return given()
            .queryParam("postId", postId)
            .when()
            .get(Endpoints.COMMENTS)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    public static Response createComment(String commentBody){
        return given()
            .header("Content-type", "application/json; charset=UTF-8")
            .body(commentBody)
            .when()
            .post(Endpoints.COMMENTS)
            .then()
            .statusCode(201)
            .extract()
            .response();
    }

    public static Response updateComment(int commentId, String commentBody){
        return given()
            .header("Content-type", "application/json; charset=UTF-8")
            .body(commentBody)
            .pathParam("commentId", commentId)
            .when()
            .put(Endpoints.COMMENTS_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    public static Response deleteComment(int commentId){
        return given()
            .pathParam("commentId", commentId)
            .when()
            .delete(Endpoints.COMMENTS_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

}
