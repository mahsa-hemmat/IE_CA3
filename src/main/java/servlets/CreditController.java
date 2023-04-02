package servlets;

import baloot.Baloot;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreditController", value = "/credit")
public class CreditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("credit.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String credit = request.getParameter("credit");
        Baloot baloot = Baloot.getInstance();
        if (baloot.hasAnyUserLoggedIn()) {
            //if(credit < 0)
            //    response.sendRedirect("http://localhost:8080/error");
            baloot.increaseCredit(Integer.parseInt(credit));
        } else {
            response.sendRedirect("http://localhost:8080/login");
        }
    }
}
