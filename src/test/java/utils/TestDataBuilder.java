package utils;

public class TestDataBuilder {

    /**
     * Build user JSON request body
     */
    public static String buildUserJson(String name, String username, String email) {
        return String.format("""
            {
              "name": "%s",
              "username": "%s",
              "email": "%s"
            }
            """, name, username, email);
    }

    /**
     * Build post JSON request body
     */
    public static String buildPostJson(String title, String body, int userId) {
        return String.format("""
            {
              "title": "%s",
              "body": "%s",
              "userId": %d
            }
            """, title, body, userId);
    }

    /**
     * Generate random email
     */
    public static String generateRandomEmail() {
        long timestamp = System.currentTimeMillis();
        return "testuser" + timestamp + "@example.com";
    }

    /**
     * Generate random username
     */
    public static String generateRandomUsername() {
        long timestamp = System.currentTimeMillis();
        return "user" + timestamp;
    }
}