<%--
  Created by IntelliJ IDEA.
  User: mahsa
  Date: 02.04.23
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="baloot.Baloot"%>
<%@ page import="baloot.repository.User" %>
<%@ page import="baloot.repository.BuyList" %>
<%@ page import="baloot.repository.Commodity" %>
<%@ page import="java.util.List" %>
<%
    User user = Baloot.getInstance().getLoggedInUser();
    BuyList buyList = user.getBuyList();

%>
<html lang="en"><head>
    <meta charset="UTF-8">
    <title>User</title>
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
    <li id="username">Username: <%=user.getUsername()%></li>
    <li id="email">Email: <%=user.getEmail()%></li>
    <li id="birthDate">Birth Date:  <%=user.getBirthDate()%></li>
    <li id="address">Address:  <%=user.getAddress()%></li>
    <li id="credit">Credit:  <%=user.getCredit()%></li>
    <li>Current Buy List Price: <%=buyList.getTotalCost()%></li>
    <li>
        <a href="/credit">Add Credit</a>
    </li>
    <li>
        <form action="" method="POST">
            <label>Submit & Pay</label>
            <input id="form_payment" type="hidden" name="userId" value=<%=user.getUsername()%>>
            <button type="submit">Payment</button>
        </form>
    </li>
</ul>
<table>
    <caption>
        <h2>Buy List</h2>
    </caption>
    <tbody><tr>
        <th>Id</th>
        <th>Name</th>
        <th>Provider Name</th>
        <th>Price</th>
        <th>Categories</th>
        <th>Rating</th>
        <th>In Stock</th>
        <th></th>
        <th></th>
    </tr>
    <%
        for (Commodity commodity : buyList.getCommodities().values()) {
            List<String> categories = commodity.getCategories();
            String temp = categories.get(0);
            for(int i = 1; i < categories.size(); i++)
                temp += ", " + categories.get(i);
    %>
    <tr>
        <td><%=commodity.getId()%></td>
        <td><%=commodity.getName()%></td>
        <td><%=commodity.getProviderId()%></td>
        <td><%=commodity.getPrice()%></td>
        <td><%=temp%></td>
        <td><%=commodity.getRating()%></td>
        <td><%=commodity.getInStock()%></td>
        <td><a href="/commodities/<%=commodity.getId()%>">Link</a></td>
        <td>
            <form action="" method="POST">
                <input id="form_commodity_id" type="hidden" name="commodityId" value=<%=commodity.getId()%>>
                <button type="submit">Remove</button>
            </form>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
    <br>
    <form method="post" action="">
        <label>Discount Code</label>
        <input  name="discountCode" type="text" />
        <button type="submit">Add Discount</button>
    </form>
</body>
</html>