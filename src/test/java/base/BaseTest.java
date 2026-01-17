package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        // Set base URI once for all tests
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Optional: Set default content type
        RestAssured.basePath = "";

        System.out.println("\nBase URI configured: " + RestAssured.baseURI);
    }

    // Common helper method for logging
    protected void logTestStart(String testName) {
        System.out.println("\n========================================");
        System.out.println("Starting Test: " + testName);
        System.out.println("========================================\n");
    }

    protected void logTestEnd(String testName) {
        System.out.println("\n========================================");
        System.out.println("Completed Test: " + testName);
        System.out.println("========================================\n");
    }
}