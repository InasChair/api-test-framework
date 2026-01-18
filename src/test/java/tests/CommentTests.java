package tests;

import base.BaseTest;
import helpers.CommentHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TestDataBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("API Testing")
@Feature("Comment Management")
public class CommentTests extends BaseTest {

    @Test
    @Story("Get all comments")
    @Description("Verify that we can retrieve all comments from the API")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAllComments() {
        logTestStart("Get All Comments");

        Response response = CommentHelper.getAllComments();

        assertThat(response.jsonPath().getList("$").size(), greaterThan(0));

        System.out.println("Total comments found: " + response.jsonPath().getList("$").size());

        logTestEnd("Get All Comments");
    }

    @Test
    @Story("Get comment by ID")
    @Description("Verify that we can retrieve a specific comment by ID")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetCommentBYid() {
        logTestStart("Get Comment By ID");

        Response response = CommentHelper.getCommentById(1);

        assertThat(response.path("id"), equalTo(1));
        assertThat(response.path("name"), notNullValue());
        assertThat(response.path("email"), notNullValue());
        assertThat(response.path("body"), notNullValue());

        System.out.println("Retrieved comment: " + response.path("name"));


        logTestEnd("Get Comment By ID");
    }

    @Test
    @Story("Get comments by post ID")
    @Description("Verify that we can retrieve comments for a specific post by post ID")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetCommentByPostId() {
        logTestStart("Get Comments By Post ID");
        int postId = 1;
        Response  response = CommentHelper.getCommentByPostId(postId);
        assertThat(response.jsonPath().getList("$").size(), greaterThan(0));
        assertThat(response.jsonPath().getList("postId"), everyItem(equalTo(postId)));

        System.out.println("Found " + response.jsonPath().getList("$").size() + " comments for post " + postId);

        logTestEnd("Get Comments By Post ID");
    }

    @Test
    @Story("Create a new comment")
    @Description("Verify that we can create a new comment")
    @Severity(SeverityLevel.CRITICAL)
    public void createComment(){

        logTestStart("Create New Comment");

        String newCommentJson = TestDataBuilder.buildCommentJson(
            "id labore ex et quam laborum",
            "laudantium enim quasi est quidem magnam voluptate ipsam eosum",
            TestDataBuilder.generateRandomEmail(),
            10
        );

        Response response = CommentHelper.createComment(newCommentJson);
        assertThat(response.path("id"), notNullValue());
        assertThat(response.path("postId"), equalTo(10));

        System.out.println("Comment added successfully");

        logTestEnd("Create New Comment");
    }

    @Test
    @Story("Update an existing comment")
    @Description("Verify that we can update an existing comment")
    @Severity(SeverityLevel.CRITICAL)
    public void updateComment(){

        logTestStart("Create Update Comment");

        int commentIdToUpdate = 1;
        String newCommentJson = TestDataBuilder.buildCommentJson(
            "new name for comment",
            "laudantium enim quasi est quidem magnam voluptate ipsam eosum",
            TestDataBuilder.generateRandomEmail(),
            11
        );

        Response response = CommentHelper.updateComment(commentIdToUpdate,newCommentJson);
        assertThat(response.path("name"), equalTo("new name for comment"));
        assertThat(response.path("postId"), equalTo(11));

        System.out.println("Comment updated successfully");

        logTestEnd("Create Update Comment");
    }

    @Test
    @Story("Delete an existing comment")
    @Description("Verify that we can delete an existing comment")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteComment(){
        logTestStart("Delete Comment");

        int commentIdToDelete = 1;

        Response response = CommentHelper.deleteComment(commentIdToDelete);

        System.out.println("Comment deleted successfully");

        logTestEnd("Delete Comment");
    }
}
