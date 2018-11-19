package servlet;

import beans.UserProfile;
import com.google.gson.Gson;
import constants.ConstantsWidgets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class checkTwitterSignInStatus extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");

        String userId = request.getParameter("userId");

        Map defaultRes = new HashMap();
        defaultRes.put("status","notFound");
        String userDetailsJsonString = new Gson().toJson(defaultRes);

        Map<String, UserProfile> userProfileMap = null;
        try {
            FileInputStream fileIn = new FileInputStream(ConstantsWidgets.UserDataPath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userProfileMap = (Map<String, UserProfile>) in.readObject();
            in.close();
            fileIn.close();

            UserProfile userProfile = userProfileMap.get(userId);
            if(userProfile != null) {
                userDetailsJsonString = new Gson().toJson(userProfile);
            }
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userDetailsJsonString);
        out.flush();
    }
}