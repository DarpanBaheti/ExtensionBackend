package servlet;

import beans.SampleWidget;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "SampleServlet", urlPatterns = "/SampleServlet")
public class SampleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");

//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<h3>Hello World!</h3>");

        SampleWidget sampleWidget = new SampleWidget("Sample Widget!");
        String sampleWidgetJsonString = new Gson().toJson(sampleWidget);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(sampleWidgetJsonString);
        out.flush();
    }
}