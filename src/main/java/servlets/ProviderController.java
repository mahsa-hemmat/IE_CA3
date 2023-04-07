package servlets;

import baloot.Baloot;
import baloot.repository.Provider;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProviderController", value = "/providers/*")
public class ProviderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String providerId = request.getPathInfo().substring(1);
            Baloot baloot = Baloot.getInstance();
            if (baloot.hasAnyUserLoggedIn()) {
                baloot.getProvider(Integer.parseInt(providerId));
                request.setAttribute("providers_id", providerId);
                request.getRequestDispatcher("/provider.jsp").forward(request, response);
            } else {
                response.sendRedirect("http://localhost:8080/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
