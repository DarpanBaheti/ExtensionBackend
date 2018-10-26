package servlet;

import beans.TwitterWidget;
import beans.UserProfile;
import com.google.gson.Gson;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import utils.HttpURLConnectionExample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitterSignedInUser extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
        String userId = (String) request.getSession().getAttribute("userId");

        String outString = "userId: " + userId;
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        outString += " | request_token: " + requestToken + " | oauth_verifier: " + verifier;
        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
            outString += " | access_token: " + accessToken.getToken() + " | access_secret: " + accessToken.getTokenSecret();
            insertIntoDB(userId,accessToken.getToken(),accessToken.getTokenSecret());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8000/newtab/authorized.html?twitterSignedInStatus=true");
    }

    public void insertIntoDB(String userId, String twitterAccessToken, String twitterAccessTokenSecret) {
        Map<String, UserProfile> userProfileMap = null;
        try {
            FileInputStream fileIn = new FileInputStream("./userData/userProfile.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userProfileMap = (Map<String, UserProfile>) in.readObject();
            in.close();
            fileIn.close();

            UserProfile userProfile = new UserProfile(userId,twitterAccessToken,twitterAccessTokenSecret);
            userProfileMap.put(userId,userProfile);

            FileOutputStream fileOut = new FileOutputStream("./userData/userProfile.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(userProfileMap);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}