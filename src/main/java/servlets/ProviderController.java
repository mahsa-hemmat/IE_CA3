package servlets;

import baloot.Baloot;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProviderController", value = "/providers/*")
public class ProviderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String providerId = request.getPathInfo().substring(1);
        System.out.println(providerId);
        Baloot baloot = Baloot.getInstance();
        if (baloot.hasAnyUserLoggedIn()) {
            request.getRequestDispatcher(String.format("/provider.jsp?provider_id=%s",request.getPathInfo().substring(1))).forward(request, response);
        } else {
            response.sendRedirect("http://localhost:8080/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
