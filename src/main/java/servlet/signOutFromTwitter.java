package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class signOutFromTwitter extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
        HttpSession session=request.getSession();

        System.out.println(session);
        System.out.println(session.getId());
        System.out.println(session.getAttributeNames());
        System.out.println(session.getCreationTime());
        System.out.println(session.getLastAccessedTime());

        request.getSession().invalidate();
//        response.sendRedirect(request.getContextPath()+ "/");
//        response.sendRedirect("http://localhost:8000/newtab/index.html");
    }
}
