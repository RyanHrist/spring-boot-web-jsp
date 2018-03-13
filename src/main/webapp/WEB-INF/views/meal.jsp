<%@ page import="application.models.User" %>
<%@ page import="application.models.Meals" %><%--
  Created by IntelliJ IDEA.
  User: Nancy
  Date: 3/6/2018
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="centered">

        <%
            boolean correctURL = (boolean) session.getAttribute("correctURL");
            if(correctURL) {
            User mealHost = (User) session.getAttribute("mealHost");
        Meals selectedMeal = (Meals) session.getAttribute("selectedMeal");
        pageContext.setAttribute("selectedMeal", selectedMeal);
        pageContext.setAttribute("mealHost", mealHost);
        %>
        <h1>${selectedMeal.mealTitle}</h1>
        <h2>Host: ${mealHost.name}</h2>
        <% if (selectedMeal.getImage().equals("") || selectedMeal.getImage() == null) {%>
        <img src="/images/i1.jpg" alt="Meal 1" style="width:256px;height:200px;">
        <% } else { %>
        <img src="${selectedMeal.image}" alt="Meal 1" style="width:256px;height:200px;">
        <%	} %>

        <h2>Location: ${selectedMeal.address}</h2>
        <h2>Price:${selectedMeal.price}</h2>
        <h2>Date: ${selectedMeal.date} ${selectedMeal.time}</h2>

        <h2>Host Rating</h2>
        <%--<img src="images/i1.png" alt="Meal 1" style="width:200px;height:50px;">--%>
        <%--<h2>Guest Rating</h2>--%>
        <%--<img src="images/i1.png" alt="Meal 1" style="width:200px;height:50px;">--%>

        <h3>Description</h3>
        <p>${selectedMeal.description}</p>

        <% User loggedUser = (User) session.getAttribute("user");
            pageContext.setAttribute("user", loggedUser);
            if (loggedUser != null) { %>
        <form  id="form" action="/meal/bookmeal/${selectedMeal.mealID}" autocomplete="on" method="POST">
            <button type="submit"><i class="fa fa-search"></i>Book Meal</button>
        </form>
        <% } else { %>
        <h2>Login to book a meal!</h2>
        <% } %>
    </div>
    <% } else {%>
        <h1>This meal does not exist...</h1>
    <% } %>
</body>
</html>
