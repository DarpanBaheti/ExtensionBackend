package servlet;

import beans.TwitterWidget;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitterSignedInUser extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");

        String outString = "";

        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        outString += "request_token: " + requestToken + " | oauth_verifier: " + verifier;

        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
            outString += " | access_token: " + accessToken.getToken() + " | access_secret: " + accessToken.getTokenSecret();
            response.setHeader("access_token",accessToken.getToken());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        System.out.println(outString);

//        response.sendRedirect("http://localhost:8000/newtab/index.html");

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(outString);
        out.flush();
    }
}
