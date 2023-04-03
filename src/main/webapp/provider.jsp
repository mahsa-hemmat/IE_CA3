<%@ page import="baloot.repository.Provider" %>
<%@ page import="baloot.Baloot" %>
<%@ page import="baloot.repository.Commodity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mahsa
  Date: 03.04.23
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String providerId = request.getParameter("provider_id");
    Provider provider = Baloot.getInstance().getProvider(Integer.parseInt(providerId));
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Provider</title>
    <style>
        li {
            padding: 5px
        }
        table{
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<ul>
    <li id="id">Id:  <%=provider.getId()%></li>
    <li id="name">Name: <%=provider.getName()%></li>
    <li id="registryDate">Registry Date: <%=provider.getRegistryDate()%></li>
    <li id="rating">Rating: <%=provider.getRating()%></li>
</ul>
<table>
    <caption><h3>Provided Commodities</h3></caption>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Categories</th>
        <th>Rating</th>
        <th>In Stock</th>
    </tr>
    <%
        for (Commodity commodity : provider.getCommodities()) {
            List<String> categories = commodity.getCategories();
            String temp = categories.get(0);
            for(int i = 1; i < categories.size(); i++)
                temp += ", " + categories.get(i);

    %>
    <tr>
        <td><%=commodity.getId()%></td>
        <td><%=commodity.getName()%></td>
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
