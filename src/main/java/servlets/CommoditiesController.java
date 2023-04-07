package servlets;

import baloot.Baloot;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "CommoditiesController", value = "/commodities")
public class CommoditiesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Baloot baloot = Baloot.getInstance();
        if (baloot.hasAnyUserLoggedIn()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("commodities.jsp");
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("http://localhost:8080/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     Baloot baloot = Baloot.getInstance();
     String parameter = request.getParameter("action");
     String filter = request.getParameter("search");
     if(parameter.equals("search_by_category"))
         baloot.getDataBase().getCommodities().addFilter(filter, "category");
     else if(parameter.equals("search_by_name"))
         baloot.getDataBase().getCommodities().addFilter(filter, "name");
     else if(parameter.equals("clear"))
         baloot.getDataBase().getCommodities().clearFilter();
     else if(parameter.equals("sort_by_rate"))
         baloot.getDataBase().getCommodities().sortByRate();
     else if(parameter.equals("sort_by_price"))
         baloot.getDataBase().getCommodities().sortByPrice();
            response.sendRedirect("http://localhost:8080/commodities");
    }
}
