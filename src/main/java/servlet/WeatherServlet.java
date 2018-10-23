package servlet;

import utils.HttpURLConnectionExample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "WeatherServlet", urlPatterns = "/WeatherServlet")
public class WeatherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");

        String lat = request.getParameter("latitude");
        String lon = request.getParameter("longitude");

        //String apiUrl = "http://extensionslabs.com/hapi/getWeatherData?latitude=18.975&longitude=72.8258";
        String apiUrl = "http://extensionslabs.com/hapi/getWeatherData";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("latitude", lat);
        parameters.put("longitude", lon);

        String weatherJsonString = "";
        HttpURLConnectionExample http = new HttpURLConnectionExample();
        try {
            weatherJsonString = http.sendGet(apiUrl,parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(weatherJsonString);
        out.flush();
    }
}