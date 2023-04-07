<!DOCTYPE html>
<%@page import="baloot.Baloot"%>
<%@ page import="baloot.repository.Commodity" %>
<%@ page import="baloot.repository.Comment" %>
<%@ page import="java.util.List" %>
<html lang="en">
<%
    String commodityId = (String) request.getAttribute("commodity_id");
    Commodity commodity = Baloot.getInstance().getDataBase().getCommodityById(Integer.parseInt(commodityId));
%>
  <head>
    <meta charset="UTF-8" />
    <title>Commodity</title>
    <style>
      li {
        padding: 5px;
      }
      table {
        width: 100%;
        text-align: center;
      }
    </style>
  </head>
  <body>
    <span>username: <%=Baloot.getInstance().getLoggedInUser().getUsername()%></span>
    <br>
    <ul>
      <li id="id">Id: <%=commodity.getId()%></li>
      <li id="name">Name: <%=commodity.getName()%></li>
      <li id="providerName">Provider Name: <%=Baloot.getInstance().getProvider(commodity.getProviderId()).getName()%></li>
      <li id="price">Price: <%=commodity.getPrice()%></li>
        <%
            List<String> categories = commodity.getCategories();
                String temp = categories.get(0);
                for(int i = 1; i < categories.size(); i++)
                    temp += ", " + categories.get(i);
        %>
      <li id="categories">Categories: <%=temp%></li>
      <li id="rating">Rating: <%=commodity.getRating()%></li>
      <li id="inStock">In Stock: <%=commodity.getInStock()%></li>
    </ul>

    <label>Add Your Comment:</label>
    <form action="" method="post">
      <input type="text" name="comment" value="" />
      <button type="submit" name="action" value="comment">submit</button>
    </form>
    <br>
    <form action="" method="POST">
      <label>Rate(between 1 and 10):</label>
      <input type="number" id="quantity" name="quantity" min="1" max="10">
      <button type="submit" name="action" value="rate">Rate</button>
    </form>
    <br>
    <form action="" method="POST">
      <button type="submit" name="action" value="add">Add to BuyList</button>
    </form>
    <br />
    <table>
      <caption><h2>Comments</h2></caption>
      <tr>
        <th>username</th>
        <th>comment</th>
        <th>date</th>
        <th>likes</th>
        <th>dislikes</th>
      </tr>
      <%
            for (Comment comment : commodity.getComments().values()) {
                String username = Baloot.getInstance().getDataBase().getUserByEmail(comment.getUserEmail());
      %>
      <tr>
        <td><%=username%></td>
        <td><%=comment.getText()%></td>
        <td><%=comment.getDate()%></td>
        <td>
          <form action="" method="POST">
            <label for=""><%=comment.getLike()%></label>
            <input
              id="form_comment_id"
              type="hidden"
              name="comment_id"
              value="<%=comment.getId()%>"
            />
            <button type="submit" name="action" value="like">like</button>
          </form>
        </td>
        <td>
          <form action="" method="POST">
            <label for=""><%=comment.getDislike()%></label>
            <input
              id="form_comment_id"
              type="hidden"
              name="comment_id"
              value="<%=comment.getId()%>"
            />
            <button type="submit" name="action" value="dislike">dislike</button>
          </form>
        </td>
      </tr>
        <%}%>
    </table>
    <br><br>
    <table>
      <caption><h2>Suggested Commodities</h2></caption>
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
            for (Commodity recommended : Baloot.getInstance().getDataBase().recommenderSystem(Integer.parseInt(commodityId))) {
                List<String> cat = recommended.getCategories();
                temp = categories.get(0);
                for(int i = 1; i < cat.size(); i++)
                    temp += ", " + cat.get(i);
        %>
        <tr>
            <td><%=recommended.getId()%></td>
            <td><%=recommended.getName()%></td>
            <td><%=Baloot.getInstance().getProvider(recommended.getProviderId()).getName()%></td>
            <td><%=recommended.getPrice()%></td>
            <td><%=temp%></td>
            <td><%=recommended.getRating()%></td>
            <td><%=recommended.getInStock()%></td>
            <td><a href="/commodities/<%=recommended.getId()%>">Link</a></td>
        </tr>
        <%}%>
    </table>
  </body>
</html>
