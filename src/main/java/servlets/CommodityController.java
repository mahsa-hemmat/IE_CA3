package servlets;

import baloot.Baloot;
import baloot.repository.Commodity;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CommodityController", value = "/commodities/*")
public class CommodityController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String commodityId = request.getPathInfo().substring(1);
            Baloot baloot = Baloot.getInstance();
            if (baloot.hasAnyUserLoggedIn()) {
                baloot.getDataBase().getCommodityById(Integer.parseInt(commodityId));
                request.setAttribute("commodity_id", commodityId);
                request.getRequestDispatcher("/commodity.jsp").forward(request, response);
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
        try {
            Baloot baloot = Baloot.getInstance();
            String parameter = request.getParameter("action");
            int commodityId = Integer.parseInt(request.getPathInfo().substring(1));
            if(parameter.equals("rate")) {
                int rate = Integer.parseInt(request.getParameter("quantity"));
                baloot.getDataBase().rateCommodity(commodityId, rate);
            }
            else if(parameter.equals("add"))
                baloot.getDataBase().addToBuyList(commodityId);
            else if(parameter.equals("like")) {
                String commentId = request.getParameter("comment_id");
                baloot.getDataBase().voteComment(commentId, 1);
            }
            else if(parameter.equals("dislike")) {
                String commentId = request.getParameter("comment_id");
                baloot.getDataBase().voteComment(commentId, -1);
            }
            else if(parameter.equals("comment")) {
                String text = request.getParameter("comment");
                baloot.getDataBase().addComment(text, commodityId);
            }
            response.sendRedirect(String.format("http://localhost:8080/commodities/%s", request.getPathInfo().substring(1)));
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
