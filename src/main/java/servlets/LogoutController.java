package servlets;

import baloot.Baloot;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutController", value = "/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Baloot baloot = Baloot.getInstance();
        if (baloot.hasAnyUserLoggedIn()) {
            baloot.logOutUser();
            response.sendRedirect("http://localhost:8080/login");
        } else {
            request.setAttribute("error", "No User Has Logged In");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
