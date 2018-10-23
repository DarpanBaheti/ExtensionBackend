package servlet;

import beans.YoutubeWidget;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import twitter4j.JSONArray;
import twitter4j.JSONObject;
import utils.HttpURLConnectionExample;
import utils.ParameterStringBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "YoutubeServlet", urlPatterns = "/YoutubeServlet")
public class YoutubeServlet extends HttpServlet {
    private final String youtubeAPI = "AIzaSyDfOyxnl6HYzGkI9bIelIPdG6w-G8Qu8g8";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");

        String countryCode = request.getParameter("countryCode");
        String vidCategoryID = request.getParameter("vidCategoryID");

        String apiUrl = "https://www.googleapis.com/youtube/v3/videos";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("part","snippet");
        parameters.put("videoCategoryId",vidCategoryID);
        parameters.put("chart","mostPopular");
        if(countryCode != null && !countryCode.equals("world"))
            parameters.put("regionCode",countryCode);
        parameters.put("maxResults","5");
        parameters.put("key","AIzaSyDXpwzqSs41Kp9IZj49efV3CSrVxUDAwS0");
        String paramString = ParameterStringBuilder.getParamsString(parameters);
        if(paramString != null)
            apiUrl = apiUrl + "?" + paramString;

//        System.out.println("apiUrl " + apiUrl);


        YoutubeWidget youtubeWidget = new YoutubeWidget();

        HttpURLConnectionExample http = new HttpURLConnectionExample();
        try {
            String resJsonString = http.sendGet(apiUrl);
            JSONObject jsonObj = new JSONObject(resJsonString);
            JSONArray getArray = jsonObj.getJSONArray("items");
            for(int i = 0; i < getArray.length(); i++) {
                JSONObject object = getArray.getJSONObject(i);
                JSONObject snippet = object.getJSONObject("snippet");
                JSONObject thumbnail = snippet.getJSONObject("thumbnails");
                JSONObject medium = thumbnail.getJSONObject("medium");

                String vidId = object.getString("id");
                String title = snippet.getString("title");
                String thumb = medium.getString("url");

                Map vidDetails = new HashMap();
                vidDetails.put("id",vidId);
                vidDetails.put("title",title);
                vidDetails.put("thumb",thumb);
                vidDetails.put("link","https://www.youtube.com/watch?v=" + vidId);
                vidDetails.put("text","");

                youtubeWidget.insertEntity(vidDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gsonBuilder = new GsonBuilder().create();
        String jsonFromYoutubeList = gsonBuilder.toJson(youtubeWidget.getTrendingVideos());

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonFromYoutubeList);
        out.flush();
    }
}