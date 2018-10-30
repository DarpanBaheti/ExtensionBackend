package servlet;

import beans.UserProfile;
import com.google.gson.Gson;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitterHomeTimeline extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");

        String userId = request.getParameter("userId");
        UserProfile userProfile = getUserProfileDetails(userId);

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("KfEet8Ecgq6BqDFi0rwvyT4mv")
                .setOAuthConsumerSecret("e4MtE5Y15RJTPwTahYtpugfcQTKW9kMIbn0RY5ZNrMXMEnVOka")
                .setOAuthAccessToken(userProfile.twitterAccessToken)
                .setOAuthAccessTokenSecret(userProfile.twitterAccessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        List userData = new ArrayList<>();
        try {
            List<Status> statuses;
            statuses = twitter.getHomeTimeline();
            int count = 1;
            for (Status status : statuses) {
                if(count>5) break;
                count += 1;

                Map postDetails = new HashMap();
                String userName = status.getUser().getScreenName();
                String id = String.valueOf(status.getId());
                String text = status.getText();
                String profileImage = status.getUser().getProfileImageURLHttps();
                if(status.isRetweet() == true)
                {
                    userName = status.getRetweetedStatus().getUser().getScreenName();
                    id = String.valueOf(status.getRetweetedStatus().getId());
                    text = "[RT @" + userName + " ] " + status.getRetweetedStatus().getText();
                    profileImage = status.getRetweetedStatus().getUser().getProfileImageURLHttps();
                }
                postDetails.put("userName",userName);
                postDetails.put("id",id);
                postDetails.put("text",text);
                postDetails.put("profileImage",profileImage);
                userData.add(postDetails);
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
        }

        String twitterJsonString = new Gson().toJson(userData);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(twitterJsonString);
        out.flush();
    }

    public UserProfile getUserProfileDetails(String userId) {
        UserProfile userProfile = null;
        Map<String, UserProfile> userProfileMap = null;
        try {
            FileInputStream fileIn = new FileInputStream("./userData/userProfile.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userProfileMap = (Map<String, UserProfile>) in.readObject();
            in.close();
            fileIn.close();

            userProfile = userProfileMap.get(userId);
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userProfile;
    }
}
