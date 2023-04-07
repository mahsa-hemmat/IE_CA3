<!DOCTYPE html>
<%@page import="baloot.Baloot"%>
<%@ page import="baloot.repository.Commodity" %>
<%@ page import="java.util.List" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Commodities</title>
    <style>
        table{
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
    <a href="/">Home</a>
    <p id="username">username: <%=Baloot.getInstance().getLoggedInUser().getUsername()%></p>
    <br><br>
    <form action="" method="POST">
        <label>Search:</label>
        <input type="text" name="search" value="">
        <button type="submit" name="action" value="search_by_category">Search By Category</button>
        <button type="submit" name="action" value="search_by_name">Search By Name</button>
        <button type="submit" name="action" value="clear">Clear Search</button>
    </form>
    <br><br>
    <form action="" method="POST">
        <label>Sort By:</label>
        <button type="submit" name="action" value="sort_by_rate">Rate</button>
    </form>
    <br><br>
    <form action="" method="POST">
        <label>Sort By:</label>
        <button type="submit" name="action" value="sort_by_price">Price</button>
    </form>
    <br><br>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Provider Name</th>
            <th>Price</th>
            <th>Categories</th>
            <th>Rating</th>
            <th>In Stock</th>
            <th>Links</th>
        </tr>
        <%
            for (Commodity commodity : Baloot.getInstance().getDataBase().getCommodities().getList()) {
                List<String> categories = commodity.getCategories();
                String temp = categories.get(0);
                for(int i = 1; i < categories.size(); i++)
                    temp += ", " + categories.get(i);

        %>
        <tr>
            <td><%=commodity.getId()%></td>
            <td><%=commodity.getName()%></td>
            <td><%=Baloot.getInstance().getProvider(commodity.getProviderId()).getName()%></td>
            <td><%=commodity.getPrice()%></td>
            <td><%=temp%></td>
            <td><%=commodity.getRating()%></td>
            <td><%=commodity.getInStock()%></td>
            <td><a href="/commodities/<%=commodity.getId()%>">Link</a></td>
        </tr>
        <%}%>
    </table>
</body>
</html>