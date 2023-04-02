package servlets;

import baloot.Baloot;
import baloot.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.*;
import java.util.List;


@WebServlet(name = "HomeController", urlPatterns = "")
public class HomeController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        final String USERS_URL = "http://5.253.25.110:5000/api/users";
        final String COMMODITIES_URL = "http://5.253.25.110:5000/api/commodities";
        final String PROVIDERS_URL = "http://5.253.25.110:5000/api/providers";
        final String COMMENTS_URL = "http://5.253.25.110:5000/api/comments";
        try {
            List<User> users = importUsers(USERS_URL);
            List<Provider> providers = importProviders(PROVIDERS_URL);
            List<Commodity> commodities = importCommodities(COMMODITIES_URL);
            List<Comment> comments =  importComments(COMMENTS_URL);
            Baloot.getInstance().addData(users, providers, commodities, comments);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Baloot baloot = Baloot.getInstance();
        if (baloot.hasAnyUserLoggedIn()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("http://localhost:8080/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private List<Comment> importComments(String comments_url) throws Exception{
        String strJsonData = getData(comments_url);
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(strJsonData, new TypeReference<List<Comment>>(){});
    }

    private List<Commodity> importCommodities(String commodities_url) throws Exception{
        String strJsonData = getData(commodities_url);
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(strJsonData, new TypeReference<List<Commodity>>(){});
    }

    private List<Provider> importProviders(String providers_url) throws Exception{
        String strJsonData = getData(providers_url);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(strJsonData, new TypeReference<List<Provider>>(){});
    }

    private List<User> importUsers(String users_url) throws Exception {
        String strJsonData = getData(users_url);
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.readValue(strJsonData, new TypeReference<List<User>>(){});
        for(User user: users)
            System.out.println(user.getUsername());
        return users;
    }

    private String getData(String users_url) throws URISyntaxException, IOException {
        URL url =  new URI(users_url).toURL();
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuffer lines = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            lines.append(line);
        }
        String strJsonData = lines.toString();
        bufferedReader.close();
        return strJsonData;
    }
}
