package helpers;

import config.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class AlbumHelper {

    public static Response getAllAlbums(){
        return given()
            .when()
            .get(Endpoints.ALBUMS)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    public static Response getAlbumById(int albumId){
        return given()
            .pathParam("albumId", albumId)
            .when()
            .get(Endpoints.ALBUM_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }



    public static Response getAlbumByUserId(int userId){
        return given()
            .queryParam("userId", userId)
            .when()
            .get(Endpoints.ALBUMS)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    public static Response createAlbum(String albumBody){
        return given()
            .header("Content-type", "application/json; charset=UTF-8")
            .body(albumBody)
            .when()
            .post(Endpoints.ALBUMS)
            .then()
            .statusCode(201)
            .extract()
            .response();
    }

    public static Response updateAlbum(int albumId, String albumBody){
        return given()
            .header("Content-type", "application/json; charset=UTF-8")
            .body(albumBody)
            .pathParam("albumId", albumId)
            .when()
            .put(Endpoints.ALBUM_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    public static Response deleteAlbum(int albumId){
        return given()
            .pathParam("albumId", albumId)
            .when()
            .delete(Endpoints.ALBUM_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

}
