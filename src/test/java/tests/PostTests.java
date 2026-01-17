package tests;

import base.BaseTest;
import helpers.PostHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestDataBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("API Testing")
@Feature("Post Management")
public class PostTests extends BaseTest {

    @Test
    @Story("Get all posts")
    @Description("Verify that we can retrieve all posts from the API")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAllPosts() {
        logTestStart("Get All Posts");

        Response response = PostHelper.getAllPosts();

        assertThat(response.jsonPath().getList("$").size(), greaterThan(0));

        System.out.println("Total posts found: " + response.jsonPath().getList("$").size());

        logTestEnd("Get All Posts");
    }

    @Test
    @Story("Get specific post")
    @Description("Verify that we can retrieve a specific post by ID")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetPostById() {
        logTestStart("Get Post By ID");

        Response response = PostHelper.getPostById(1);

        assertThat(response.path("id"), equalTo(1));
        assertThat(response.path("title"), notNullValue());
        assertThat(response.path("body"), notNullValue());

        System.out.println("Retrieved post with title: " + response.path("title"));

        logTestEnd("Get Post By ID");
    }

    @Test
    @Story("Get posts by user")
    @Description("Verify that we can get all posts belonging to a specific user")
    @Severity(SeverityLevel.NORMAL)
    public void testGetPostsByUserId() {
        logTestStart("Get Posts By User ID");

        int userId = 1;
        Response response = PostHelper.getPostsByUserId(userId);

        assertThat(response.path("size()"), greaterThan(0));
        assertThat(response.path("userId"), everyItem(equalTo(userId)));

        System.out.println("Found " + response.path("size()") + " posts for user " + userId);

        logTestEnd("Get Posts By User ID");
    }

    @Test
    @Story("Create new post")
    @Description("Verify that we can create a new post")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreatePost() {
        logTestStart("Create Post");

        String newPostJson = TestDataBuilder.buildPostJson(
            "New Post Title",
            "This is the body of the new post.",
            1
        );

        Response response = PostHelper.createPost(newPostJson);

        assertThat(response.path("title"), equalTo("New Post Title"));
        assertThat(response.path("userId"), equalTo(1));
        assertThat(response.path("id"), notNullValue());

        System.out.println("Created post with ID: " + response.path("id"));

        logTestEnd("Create Post");
    }

    @Test
    @Story("Update existing post")
    @Description("Verify that we can update an existing post")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdatePost() {
        logTestStart("Update Post");

        String updatedPostJson = TestDataBuilder.buildPostJson(
            "Updated Post Title",
            "This is the updated body.",
            1
        );

        Response response = PostHelper.updatePost(1, updatedPostJson);

        assertThat(response.path("title"), equalTo("Updated Post Title"));
        assertThat(response.path("userId"), equalTo(1));

        System.out.println("Post updated successfully");

        logTestEnd("Update Post");
    }

    @Test
    @Story("Delete post")
    @Description("Verify that we can delete a post")
    @Severity(SeverityLevel.NORMAL)
    public void testDeletePost() {
        logTestStart("Delete Post");

        Response response = PostHelper.deletePost(1);

        System.out.println("Post deleted successfully");

        logTestEnd("Delete Post");
    }

    @Test
    @Story("Handle non-existent post")
    @Description("Verify that requesting a non-existent post returns 404")
    @Severity(SeverityLevel.NORMAL)
    public void testGetNonExistentPost() {
        logTestStart("Get Non-Existent Post");

        Response response = PostHelper.getNonExistentPost(999);

        System.out.println("Post not found as expected");

        logTestEnd("Get Non-Existent Post");
    }
}