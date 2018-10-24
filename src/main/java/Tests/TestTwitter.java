package Tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

//public class TestTwitter {
//    public static void main(String[] args) throws IOException, TwitterException {
//        ConfigurationBuilder cb = new ConfigurationBuilder();
//        cb.setDebugEnabled(true)
//                .setOAuthConsumerKey("KfEet8Ecgq6BqDFi0rwvyT4mv")
//                .setOAuthConsumerSecret("e4MtE5Y15RJTPwTahYtpugfcQTKW9kMIbn0RY5ZNrMXMEnVOka")
//                .setOAuthAccessToken("778340928121544704-58E0tCkp92pQh1wHsaoIUyTYqnRC05j")
//                .setOAuthAccessTokenSecret("os1pQ0PbnxWx9UAh8xRcJ6SuDrCvaFLWucXfu0xhsOhtt");
//        TwitterFactory tf = new TwitterFactory(cb.build());
//        Twitter twitter = tf.getInstance();
//        Trends trends = twitter.getPlaceTrends(23424848);
//        int count = 0;
//        for (Trend trend : trends.getTrends()) {
//            if (count < 10) {
////                System.out.println(trend.getClass());
//                System.out.println(trend.getName());
//                count++;
//            }
//        }
//
////        ResponseList<Location> locations;
////        locations = twitter.getAvailableTrends();
////        System.out.println("Showing available trends");
////        for (Location location : locations) {
////            System.out.println(location.getName() + " (woeid:" + location.getWoeid() + ")");
////        }
//
////        Integer idTrendLocation = getTrendLocationId("India");
////        System.out.println(idTrendLocation);
//    }
//
//    private static Integer getTrendLocationId(String locationName) {
//
//        int idTrendLocation = 0;
//
//        try {
//
//            ConfigurationBuilder cb = new ConfigurationBuilder();
//            cb.setDebugEnabled(true)
//                    .setOAuthConsumerKey("KfEet8Ecgq6BqDFi0rwvyT4mv")
//                    .setOAuthConsumerSecret("e4MtE5Y15RJTPwTahYtpugfcQTKW9kMIbn0RY5ZNrMXMEnVOka")
//                    .setOAuthAccessToken("778340928121544704-58E0tCkp92pQh1wHsaoIUyTYqnRC05j")
//                    .setOAuthAccessTokenSecret("os1pQ0PbnxWx9UAh8xRcJ6SuDrCvaFLWucXfu0xhsOhtt");
//            TwitterFactory tf = new TwitterFactory(cb.build());
//            Twitter twitter = tf.getInstance();
//
//            ResponseList<Location> locations;
//            locations = twitter.getAvailableTrends();
//
//            for (Location location : locations) {
//                if (location.getName().toLowerCase().equals(locationName.toLowerCase())) {
//                    idTrendLocation = location.getWoeid();
//                    break;
//                }
//            }
//
//            if (idTrendLocation > 0) {
//                return idTrendLocation;
//            }
//
//            return null;
//
//        } catch (TwitterException te) {
//            te.printStackTrace();
//            System.out.println("Failed to get trends: " + te.getMessage());
//            return null;
//        }
//
//    }
//}

//import java.util.ArrayList;
//import twitter4j.Query;
//import twitter4j.QueryResult;
//import twitter4j.Status;
//
//public class TestTwitter
//{
//    public static void main(String[] args) throws Exception
//    {
//        ConfigurationBuilder cb = new ConfigurationBuilder();
//        cb.setDebugEnabled(true)
//                .setOAuthConsumerKey("KfEet8Ecgq6BqDFi0rwvyT4mv")
//                .setOAuthConsumerSecret("e4MtE5Y15RJTPwTahYtpugfcQTKW9kMIbn0RY5ZNrMXMEnVOka")
//                .setOAuthAccessToken("778340928121544704-58E0tCkp92pQh1wHsaoIUyTYqnRC05j")
//                .setOAuthAccessTokenSecret("os1pQ0PbnxWx9UAh8xRcJ6SuDrCvaFLWucXfu0xhsOhtt");
//        TwitterFactory tf = new TwitterFactory(cb.build());
//        Twitter twitter = tf.getInstance();
//
//        Query query = new Query("#INDvWI +exclude:retweets");
//        query.setResultType(Query.POPULAR);
////        query.setGeoCode(new GeoLocation(18.975,72.8258),1000.0,"mi");
//
//        int numberOfTweets = 10;
//        long lastID = Long.MAX_VALUE;
//        ArrayList<Status> tweets = new ArrayList<Status>();
//        while (tweets.size () < numberOfTweets) {
//            if (numberOfTweets - tweets.size() > 100)
//                query.setCount(100);
//            else
//                query.setCount(numberOfTweets - tweets.size());
//            try {
//                QueryResult result = twitter.search(query);
//                tweets.addAll(result.getTweets());
//                System.out.println("Gathered " + tweets.size() + " tweets"+"\n");
//                for (Status t: tweets)
//                    if(t.getId() < lastID)
//                        lastID = t.getId();
//
//            }
//            catch (TwitterException te) {
//                System.out.println("Couldn't connect: " + te);
//            };
//            query.setMaxId(lastID-1);
//        }
//
//        for (int i = 0; i < tweets.size(); i++) {
//            Status t = (Status) tweets.get(i);
//
//            // GeoLocation loc = t.getGeoLocation();
////            System.out.println(t);
//
//            String twitterJsonString = new Gson().toJson(t);
//            System.out.println(twitterJsonString);
//            System.out. println("|||||||||||||||||||||||||||||||||||||||||||||||");
//
//            String user = t.getUser().getScreenName();
//            String msg = t.getText();
//            //String time = "";
//            //if (loc!=null) {
//            //Double lat = t.getGeoLocation().getLatitude();
//            //Double lon = t.getGeoLocation().getLongitude();*/
//            System.out. println(i + " USER: " + user + " wrote: " + msg + "\n");
//            System.out. println("---------------------------------------------");
//
//        }
//        //else
//        //System.out.println(i + " USER: " + user + " wrote: " + msg+"\n");
//    }
//}

//import twitter4j.Status;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class TestTwitter {
//    public static void main(String[] args) {
//        ConfigurationBuilder cb = new ConfigurationBuilder();
//        cb.setDebugEnabled(true)
//                .setOAuthConsumerKey("KfEet8Ecgq6BqDFi0rwvyT4mv")
//                .setOAuthConsumerSecret("e4MtE5Y15RJTPwTahYtpugfcQTKW9kMIbn0RY5ZNrMXMEnVOka")
//                .setOAuthAccessToken("778340928121544704-58E0tCkp92pQh1wHsaoIUyTYqnRC05j")
//                .setOAuthAccessTokenSecret("os1pQ0PbnxWx9UAh8xRcJ6SuDrCvaFLWucXfu0xhsOhtt");
//        TwitterFactory tf = new TwitterFactory(cb.build());
//        Twitter twitter = tf.getInstance();
//        try {
//            List userData = new ArrayList<>();
//
//            List<Status> statuses;
//            String user = "imVkohli";
//            statuses = twitter.getUserTimeline(user);
//            System.out.println("Showing @" + user + "'s user timeline.\n");
//            int count = 1;
//            for (Status status : statuses) {
//                if(count>5) break;
//                count += 1;
//
////                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText() + "\n");
////                System.out.println(status + "\n");
////                String twitterJsonString = new Gson().toJson(status);
////                System.out.println(twitterJsonString + "\n");
//
//                Map postDetails = new HashMap();
//                String userName,id,text,profileImage;
//                userName = status.getUser().getScreenName();
//                id = String.valueOf(status.getId());
//                text = status.getText();
//                profileImage = status.getUser().getProfileImageURLHttps();
//
//                if(status.isRetweet() == true)
//                {
//                    userName = status.getRetweetedStatus().getUser().getScreenName();
//                    id = String.valueOf(status.getRetweetedStatus().getId());
//                    text = status.getRetweetedStatus().getText();
//                    profileImage = status.getRetweetedStatus().getUser().getProfileImageURLHttps();
//                }
//                postDetails.put("userName",userName);
//                postDetails.put("id",id);
//                postDetails.put("text",text);
//                postDetails.put("profileImage",profileImage);
//
//                userData.add(postDetails);
//            }
//            System.out.println(userData + "\n");
//        } catch (TwitterException te) {
//            te.printStackTrace();
//            System.out.println("Failed to get timeline: " + te.getMessage());
//            System.exit(-1);
//        }
//    }
//}

//public class TestTwitter {
//    public static void main(String[] args) throws IOException, TwitterException {
//        String queryString = "#TheBlindList";
//
//        ConfigurationBuilder cb = new ConfigurationBuilder();
//        cb.setDebugEnabled(true)
//                .setOAuthConsumerKey("KfEet8Ecgq6BqDFi0rwvyT4mv")
//                .setOAuthConsumerSecret("e4MtE5Y15RJTPwTahYtpugfcQTKW9kMIbn0RY5ZNrMXMEnVOka")
//                .setOAuthAccessToken("778340928121544704-58E0tCkp92pQh1wHsaoIUyTYqnRC05j")
//                .setOAuthAccessTokenSecret("os1pQ0PbnxWx9UAh8xRcJ6SuDrCvaFLWucXfu0xhsOhtt");
//        TwitterFactory tf = new TwitterFactory(cb.build());
//        Twitter twitter = tf.getInstance();
//
//        List searchData = new ArrayList<>();
//        try {
//            Query query = new Query(queryString + " +exclude:retweets");
//            query.setResultType(Query.MIXED);
////            query.setGeoCode(new GeoLocation(18.975,72.8258),1000.0,"mi");
//            int numberOfTweets = 10;
//            query.setCount(numberOfTweets);
//            QueryResult result = twitter.search(query);
//            List<Status> statuses = result.getTweets();
//
//            int count = 1;
//            for (Status status : statuses) {
//                if(count>5) break;
//                count += 1;
//
//                Map postDetails = new HashMap();
//                String userName,id,text,profileImage;
//                userName = status.getUser().getScreenName();
//                id = String.valueOf(status.getId());
//                text = status.getText();
//                profileImage = status.getUser().getProfileImageURLHttps();
//                if(status.isRetweet() == true)
//                {
//                    userName = status.getRetweetedStatus().getUser().getScreenName();
//                    id = String.valueOf(status.getRetweetedStatus().getId());
//                    text = "[RT @" + userName + " ] " + status.getRetweetedStatus().getText();
//                    profileImage = status.getRetweetedStatus().getUser().getProfileImageURLHttps();
//                }
//                postDetails.put("userName",userName);
//                postDetails.put("id",id);
//                postDetails.put("text",text);
//                postDetails.put("profileImage",profileImage);
//                searchData.add(postDetails);
//            }
//        } catch (TwitterException te) {
//            te.printStackTrace();
//            System.out.println("Failed to search tweets: " + te.getMessage());
//        }
//
//        String trendJsonString = new Gson().toJson(searchData);
//        System.out.println(trendJsonString);
//    }
//}

public class TestTwitter {
    public static void main(String[] args) throws IOException, TwitterException {
        String accessToken = "837345623670153219-vJcmjzQJTQpEewZC57GmXcqc0yOP3PN";
        String accessTokenVerifier = "YttdpITpDKQL0SrWXnFkR7orYp6yFDPtJgkaKOMHsIyLn";

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("KfEet8Ecgq6BqDFi0rwvyT4mv")
                .setOAuthConsumerSecret("e4MtE5Y15RJTPwTahYtpugfcQTKW9kMIbn0RY5ZNrMXMEnVOka")
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenVerifier);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        List userData = new ArrayList<>();
        try {
            List<Status> statuses;
            statuses = twitter.getHomeTimeline();
            int count = 1;
            for (Status status : statuses) {
                if (count > 5) break;
                count += 1;

                Map postDetails = new HashMap();
                String userName, id, text, profileImage;
                userName = status.getUser().getScreenName();
                id = String.valueOf(status.getId());
                text = status.getText();
                profileImage = status.getUser().getProfileImageURLHttps();
                if (status.isRetweet() == true) {
                    userName = status.getRetweetedStatus().getUser().getScreenName();
                    id = String.valueOf(status.getRetweetedStatus().getId());
                    text = "[RT @" + userName + " ] " + status.getRetweetedStatus().getText();
                    profileImage = status.getRetweetedStatus().getUser().getProfileImageURLHttps();
                }
                postDetails.put("userName", userName);
                postDetails.put("id", id);
                postDetails.put("text", text);
                postDetails.put("profileImage", profileImage);
                userData.add(postDetails);
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
        }

        String twitterJsonString = new Gson().toJson(userData);
        System.out.println(twitterJsonString);
    }
}