package Tests;

import twitter4j.JSONArray;
import twitter4j.JSONObject;
import utils.HttpURLConnectionExample;

public class TestYoutube {
    private final static String youtubeAPI = "AIzaSyDfOyxnl6HYzGkI9bIelIPdG6w-G8Qu8g8";

    public static void main(String[] args) {
        String apiUrl = "https://www.googleapis.com/youtube/v3/videos?part=contentDetails&chart=mostPopular&regionCode=IN&maxResults=5&key=" + youtubeAPI;

        HttpURLConnectionExample http = new HttpURLConnectionExample();
        try {
            String resJsonString = http.sendGet(apiUrl);
            JSONObject jsonObj = new JSONObject(resJsonString);
            JSONArray getArray = jsonObj.getJSONArray("items");
            for(int i = 0; i < getArray.length(); i++) {
                JSONObject object = getArray.getJSONObject(i);

                String vidId = object.getString("id");
                String title = getVidTitle(vidId);
                System.out.println(title);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getVidTitle(String vidId) {
        String vidTit = "";
        String apiUrl = "https://www.googleapis.com/youtube/v3/videos?id=" + vidId + "&key=" + youtubeAPI + "&fields=items(id,snippet(channelId,title,categoryId),statistics)&part=snippet,statistics";

        System.out.println(apiUrl);
        HttpURLConnectionExample http = new HttpURLConnectionExample();
        try {
            String resJsonString = http.sendGet(apiUrl);
            System.out.println(resJsonString);
            JSONObject jsonObj = new JSONObject(resJsonString);
            JSONArray getArray = jsonObj.getJSONArray("items");
            JSONObject object = getArray.getJSONObject(0);
            JSONObject snippetObject = object.getJSONObject("snippet");
            vidTit = snippetObject.getString("title");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vidTit;
    }
}
