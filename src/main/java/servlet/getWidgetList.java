package servlet;

import beans.Database;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet(name = "getWidgetList", urlPatterns = "/getWidgetList")
public class getWidgetList extends HttpServlet {
    private Database db = new Database();
    private List widgetArrayList = db.getWidgetArrayList();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gsonBuilder = new GsonBuilder().create();
        String jsonFromWidgetArrayList = gsonBuilder.toJson(widgetArrayList);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonFromWidgetArrayList);
        out.flush();
    }
}