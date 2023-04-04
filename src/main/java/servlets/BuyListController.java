package servlets;

import baloot.Baloot;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BuyListController", value = "/buyList")
public class BuyListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Baloot baloot = Baloot.getInstance();
        if (baloot.hasAnyUserLoggedIn()) {
            request.getRequestDispatcher("buyList.jsp").forward(request, response);
        } else {
            response.sendRedirect("http://localhost:8080/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Baloot baloot = Baloot.getInstance();
        try {
            if(request.getParameter("userId") != null)
                baloot.getLoggedInUser().completePurchase();
            else if(request.getParameter("discountCode") != null)
                baloot.getLoggedInUser().submitDiscount(request.getParameter("discountCode"));
            else if(request.getParameter("commodityId") != null)
                baloot.getLoggedInUser().getBuyList().removeCommodity(Integer.parseInt(request.getParameter("commodityId")));
            response.sendRedirect("http://localhost:8080/buyList");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
