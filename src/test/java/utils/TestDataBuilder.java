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
     * Build comment JSON request body
     */
    public static String buildCommentJson(String name, String body, String email, int postId) {
        return String.format("""
            {
              "name": "%s",
              "body": "%s",
              "email": "%s",
              "postId": %d
            }
            """, name, body,email, postId);
    }

    /**
     * Build album JSON request body
     */
    public static String buildAlbumJson(String title, int userId) {
        return String.format("""
            {
              "title": "%s",
              "userId": %d
            }
            """, title, userId);
    }

    /**
     * Build photo JSON request body
     */
    public static String buildPhotoJson(String title, String URL, String thumbnailURL ,int albumId) {
        return String.format("""
            { 
             "title": "%s",
              "URL": "%s",
              "thumbnailURL": "%s",
              "albumId": %d
            }
            """, title, URL, thumbnailURL, albumId);
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

    /**
     * Generate random URL
     */
    public static String generateRandomURL() {
        long timestamp = System.currentTimeMillis();
        return "https://via.placeholder.com/150/" + timestamp;
    }
}