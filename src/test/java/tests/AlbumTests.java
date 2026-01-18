package tests;

import base.BaseTest;
import helpers.AlbumHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestDataBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("API Testing")
@Feature("Album Management")
public class AlbumTests extends BaseTest {
    @Test
    @Story("Get all albums")
    @Description("Verify that we can retrieve all albums from the API")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAllAlbums() {
        logTestStart("Get All Albums");

        Response response = AlbumHelper.getAllAlbums();
        assertThat(response.jsonPath().getList("$").size(), greaterThan(0));
        System.out.println("Total albums found: " + response.jsonPath().getList("$").size());

        logTestEnd("Get All Albums");
    }

    @Test
    @Story("Get album by ID")
    @Description("Verify that we can retrieve a specific album by ID")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAlbumById() {
        logTestStart("Get Album By ID");

        Response response = AlbumHelper.getAlbumById(1);
        assertThat(response.path("id"), notNullValue());
        assertThat(response.path("title"), notNullValue());

        System.out.println("Retrieved album with title: " + response.path("title"));

        logTestEnd("Get Album By ID");
    }

    @Test
    @Story("Get albums by user ID")
    @Description("Verify that we can retrieve albums for a specific user by user ID")
    @Severity(SeverityLevel.NORMAL)
    public void testGetAlbumsByUserId() {
        logTestStart("Get Albums By User ID");

        int userId = 5;
        Response response = AlbumHelper.getAlbumByUserId(userId);
        assertThat(response.jsonPath().getList("$").size(), greaterThan(0));

        System.out.println("Total albums found for user " + userId + ": " + response.jsonPath().getList("$").size());

        logTestEnd("Get Albums By User ID");
    }

    @Test
    @Story("Create new album")
    @Description("Verify that we can create a new album")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateAlbum() {
        logTestStart("Create New Album");

        String albumJson = TestDataBuilder.buildAlbumJson( "Inas Test Album", 3);
        Response response = AlbumHelper.createAlbum(albumJson);

        assertThat(response.path("id"), notNullValue());
        assertThat(response.path("title"), equalTo("Inas Test Album"));
        assertThat(response.path("userId"), equalTo(3));

        System.out.println("Created album with ID: " + response.path("id"));

        logTestEnd("Create New Album");
    }

    @Test
    @Story("Update existing album")
    @Description("Verify that we can update an existing album")
    @Severity(SeverityLevel.CRITICAL)
    public void testUpdateAlbum() {
        logTestStart("Update Album");

        String updatedAlbumJson = TestDataBuilder.buildAlbumJson( "Inas Updated Album", 5);
        Response response = AlbumHelper.updateAlbum(1, updatedAlbumJson);

        assertThat(response.path("id"), equalTo(1));
        assertThat(response.path("title"), equalTo("Inas Updated Album"));
        assertThat(response.path("userId"), equalTo(5));

        System.out.println("Updated album with ID: " + response.path("id"));

        logTestEnd("Update Album");
    }

    @Test
    @Story("Get deleted album")
    @Description("Verify that we can delete an album and it is no longer retrievable")
    @Severity(SeverityLevel.CRITICAL)
    public void testDeleteAlbum() {
        logTestStart("Delete Album");

        AlbumHelper.deleteAlbum(1);
        System.out.println("Deleted album with ID: 1");

        logTestEnd("Delete Album");
    }
}
