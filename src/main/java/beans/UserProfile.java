package beans;

public class UserProfile implements java.io.Serializable{
    public String userId;
    public String twitterAccessToken;
    public String twitterAccessTokenSecret;

    public UserProfile(String userId, String twitterAccessToken, String twitterAccessTokenSecret) {
        this.userId = userId;
        this.twitterAccessToken = twitterAccessToken;
        this.twitterAccessTokenSecret = twitterAccessTokenSecret;
    }
}
