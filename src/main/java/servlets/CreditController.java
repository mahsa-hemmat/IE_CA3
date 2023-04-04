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
        Baloot baloot = Baloot.getInstance();
        if (baloot.hasAnyUserLoggedIn()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("credit.jsp");
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("http://localhost:8080/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int credit = Integer.parseInt(request.getParameter("credit"));
        Baloot baloot = Baloot.getInstance();
        if (baloot.hasAnyUserLoggedIn()) {
            if(credit < 0) {
                request.setAttribute("error", "Credit Cannot Be Negative");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            else {
                baloot.increaseCredit(credit);
                response.sendRedirect("http://localhost:8080/credit");
            }
        } else {
            response.sendRedirect("http://localhost:8080/login");
        }
    }
}
