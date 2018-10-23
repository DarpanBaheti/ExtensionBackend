package servlet;

import com.google.gson.Gson;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@WebServlet(name = "TwitterSearchServlet", urlPatterns = "/TwitterSearchServlet")
public class TwitterSearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");

        String queryString = request.getParameter("query");

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("KfEet8Ecgq6BqDFi0rwvyT4mv")
                .setOAuthConsumerSecret("e4MtE5Y15RJTPwTahYtpugfcQTKW9kMIbn0RY5ZNrMXMEnVOka")
                .setOAuthAccessToken("778340928121544704-58E0tCkp92pQh1wHsaoIUyTYqnRC05j")
                .setOAuthAccessTokenSecret("os1pQ0PbnxWx9UAh8xRcJ6SuDrCvaFLWucXfu0xhsOhtt");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        List searchData = new ArrayList<>();
        try {
            Query query = new Query(queryString + " +exclude:retweets");
            query.setResultType(Query.MIXED);
//            query.setGeoCode(new GeoLocation(18.975,72.8258),1000.0,"mi");
            int numberOfTweets = 10;
            query.setCount(numberOfTweets);
            QueryResult result = twitter.search(query);
            List<Status> statuses = result.getTweets();

            int count = 1;
            for (Status status : statuses) {
                if(count>5) break;
                count += 1;

                Map postDetails = new HashMap();
                String userName,id,text,profileImage;
                userName = status.getUser().getScreenName();
                id = String.valueOf(status.getId());
                text = status.getText();
                profileImage = status.getUser().getProfileImageURLHttps();
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
                searchData.add(postDetails);
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }

        String searchJsonString = new Gson().toJson(searchData);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(searchJsonString);
        out.flush();
    }
}

