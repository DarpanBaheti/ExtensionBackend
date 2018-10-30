package servlet;

import com.google.gson.Gson;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(name = "TwitterTrendServlet", urlPatterns = "/TwitterTrendServlet")
public class TwitterTrendServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");

//        String lat = request.getParameter("latitude");
//        String lon = request.getParameter("longitude");
        String country = request.getParameter("country");
        Integer idTrendLocation = getTrendLocationId(country);

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("KfEet8Ecgq6BqDFi0rwvyT4mv")
                .setOAuthConsumerSecret("e4MtE5Y15RJTPwTahYtpugfcQTKW9kMIbn0RY5ZNrMXMEnVOka")
                .setOAuthAccessToken("778340928121544704-58E0tCkp92pQh1wHsaoIUyTYqnRC05j")
                .setOAuthAccessTokenSecret("os1pQ0PbnxWx9UAh8xRcJ6SuDrCvaFLWucXfu0xhsOhtt");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        List trendData = new ArrayList<>();

        try {
            Trends trends = twitter.getPlaceTrends(idTrendLocation);
            int count = 0;
            for (Trend trend : trends.getTrends()) {
                if (count < 10) {
                    trendData.add(trend.getName());
                    count++;
                }
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        String trendJsonString = new Gson().toJson(trendData);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(trendJsonString);
        out.flush();
    }

    private static Integer getTrendLocationId(String locationName) {
        int idTrendLocation = 0;
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("KfEet8Ecgq6BqDFi0rwvyT4mv")
                    .setOAuthConsumerSecret("e4MtE5Y15RJTPwTahYtpugfcQTKW9kMIbn0RY5ZNrMXMEnVOka")
                    .setOAuthAccessToken("778340928121544704-58E0tCkp92pQh1wHsaoIUyTYqnRC05j")
                    .setOAuthAccessTokenSecret("os1pQ0PbnxWx9UAh8xRcJ6SuDrCvaFLWucXfu0xhsOhtt");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();

            ResponseList<Location> locations;
            locations = twitter.getAvailableTrends();

            for (Location location : locations) {
                if (location.getName().toLowerCase().equals(locationName.toLowerCase())) {
                    idTrendLocation = location.getWoeid();
                    break;
                }
            }

            if (idTrendLocation > 0) {
                return idTrendLocation;
            }

            return 0;

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get trends: " + te.getMessage());
            return 0;
        }
    }
}