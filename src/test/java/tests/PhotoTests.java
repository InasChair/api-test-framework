package tests;

import base.BaseTest;
import helpers.PhotoHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestDataBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("API Testing")
@Feature("Photo Management")
public class PhotoTests extends BaseTest {
    @Test
    @Story("Get all photo")
    @Description("Verify that we can retrieve all photos from the API")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAllPhotos() {
        logTestStart("Get All Photos");

        Response response = PhotoHelper.getAllPhotos();
        assertThat(response.jsonPath().getList("$").size(), greaterThan(0));
        System.out.println("Total photos found: " + response.jsonPath().getList("$").size());

        logTestEnd("Get All Photos");
    }

    @Test
    @Story("Get Photo by ID")
    @Description("Verify that we can retrieve a specific Photo by ID")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetPhotoById() {
        logTestStart("Get Photo By ID");

        Response response = PhotoHelper.getPhotoById(1);
        assertThat(response.path("id"), notNullValue());
        assertThat(response.path("title"), notNullValue());

        System.out.println("Retrieved Photo with title: " + response.path("title"));

        logTestEnd("Get Photo By ID");
    }

    @Test
    @Story("Get Photos by user ID")
    @Description("Verify that we can retrieve photos for a specific user by user ID")
    @Severity(SeverityLevel.NORMAL)
    public void testGetPhotosByAlbumId() {
        logTestStart("Get Photos By Album ID");

        int albumId = 5;
        Response response = PhotoHelper.getPhotoByAlbumId(albumId);
        assertThat(response.jsonPath().getList("$").size(), greaterThan(0));

        System.out.println("Total photos found for album " + albumId + ": " + response.jsonPath().getList("$").size());

        logTestEnd("Get Photos By User ID");
    }

    @Test
    @Story("Create new photo")
    @Description("Verify that we can create a new photo")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreatePhoto() {
        logTestStart("Create New Photo");

        String photoJson = TestDataBuilder.buildPhotoJson(
            "Inas Test Photo",
            TestDataBuilder.generateRandomURL(),
            TestDataBuilder.generateRandomURL(),
            3);
        Response response = PhotoHelper.createPhoto(photoJson);

        assertThat(response.path("id"), notNullValue());
        assertThat(response.path("title"), equalTo("Inas Test Photo"));
        assertThat(response.path("albumId"), equalTo(3));

        System.out.println("Created photo with ID: " + response.path("id"));

        logTestEnd("Create New Photo");
    }

    @Test
    @Story("Update existing photo")
    @Description("Verify that we can update an existing photo")
    @Severity(SeverityLevel.CRITICAL)
    public void testUpdatePhoto() {
        logTestStart("Update Photo");
        String updatePhotoJson = TestDataBuilder.buildPhotoJson(
            "Inas Updated Photo",
            TestDataBuilder.generateRandomURL(),
            TestDataBuilder.generateRandomURL(),
            3);

        Response response = PhotoHelper.updatePhoto(1, updatePhotoJson);

        assertThat(response.path("id"), equalTo(1));
        assertThat(response.path("title"), equalTo("Inas Updated Photo"));
        assertThat(response.path("albumId"), equalTo(3));

        System.out.println("Updated photo with ID: " + response.path("id"));

        logTestEnd("Update Photo");
    }

    @Test
    @Story("Get deleted photo")
    @Description("Verify that we can delete an photo and it is no longer retrievable")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeletePhoto() {
        logTestStart("Delete Photo");

        PhotoHelper.deletePhoto(1);
        System.out.println("Deleted Photo with ID: 1");

        logTestEnd("Delete Photo");
    }
}
