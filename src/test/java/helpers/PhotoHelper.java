package helpers;

import config.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class PhotoHelper {

    public static Response getAllPhotos(){
        return given()
            .when()
            .get(Endpoints.PHOTOS)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    public static Response getPhotoById(int photoId){
        return given()
            .pathParam("photoId", photoId)
            .when()
            .get(Endpoints.PHOTO_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }



    public static Response getPhotoByAlbumId(int albumId){
        return given()
            .queryParam("albumId", albumId)
            .when()
            .get(Endpoints.PHOTOS)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    public static Response createPhoto(String photoBody){
        return given()
            .header("Content-type", "application/json; charset=UTF-8")
            .body(photoBody)
            .when()
            .post(Endpoints.PHOTOS)
            .then()
            .statusCode(201)
            .extract()
            .response();
    }

    public static Response updatePhoto(int photoId, String photoBody){
        return given()
            .header("Content-type", "application/json; charset=UTF-8")
            .body(photoBody)
            .pathParam("photoId", photoId)
            .when()
            .put(Endpoints.PHOTO_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

    public static Response deletePhoto(int photoId){
        return given()
            .pathParam("photoId", photoId)
            .when()
            .delete(Endpoints.PHOTO_BY_ID)
            .then()
            .statusCode(200)
            .extract()
            .response();
    }

}
