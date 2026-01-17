package tests;

import base.BaseTest;
import helpers.UserHelper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestDataBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTests extends BaseTest {

    @Test
    public void testGetAllUsers() {
        logTestStart("Get All Users");

        Response response = UserHelper.getAllUsers();

        // Verify we have users
        assertThat(response.jsonPath().getList("$").size(), greaterThan(5));
        logTestEnd("Get All Users");
    }

    @Test
    public void testGetSingleUser() {
        logTestStart("Get Single User");

        Response response = UserHelper.getUserById(1);

        // Verify user details
        assertThat(response.path("id"), equalTo(1));
        assertThat(response.path("name"), notNullValue());
        assertThat(response.path("email"), notNullValue());

        System.out.println("Retrieved user: " + response.path("name"));

        logTestEnd("Get Single User");
    }

    @Test
    public void testGetNonExistentUser() {
        logTestStart("Get Non-Existent User (Negative Test)");

        Response response = UserHelper.getNonExistentUser(999);

        System.out.println("Correctly received 404 for non-existent user");

        logTestEnd("Get Non-Existent User");
    }

    @Test
    public void testCreateUser() {
        logTestStart("Create New User");

        String userJson = TestDataBuilder.buildUserJson(
            "Inas Test User",
            TestDataBuilder.generateRandomUsername(),
            TestDataBuilder.generateRandomEmail()
        );

        Response response = UserHelper.createUser(userJson);

        // Verify created user
        assertThat(response.path("name"), equalTo("Inas Test User"));
        assertThat(response.path("id"), notNullValue());

        System.out.println("Created user with ID: " + response.path("id"));

        logTestEnd("Create New User");
    }

    @Test
    public void testUpdateUser() {
        logTestStart("Update User");

        String updatedUserJson = TestDataBuilder.buildUserJson(
            "Updated Name",
            "updateduser",
            "updated@example.com"
        );

        Response response = UserHelper.updateUser(1, updatedUserJson);

        // Verify update
        assertThat(response.path("name"), equalTo("Updated Name"));

        logTestEnd("Update User");
    }

    @Test
    public void testDeleteUser() {
        logTestStart("Delete User");

        Response response = UserHelper.deleteUser(1);

        System.out.println("User deleted successfully");

        logTestEnd("Delete User");
    }
}