<%@ page import="application.models.User" %>
<%@ page import="application.models.Meals" %>
<%@ page import="jdk.nashorn.internal.runtime.RecompilableScriptFunctionData" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="application.Database" %>
<%@ page import="java.sql.Connection" %><%--
  Created by IntelliJ IDEA.
  User: Nancy
  Date: 3/6/2018
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
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
        <h2>${errorBooking}</h2>
        <h1>${selectedMeal.mealTitle}</h1>
        <hr>
        <h2>Host: ${mealHost.name}</h2>

        <h2>$${selectedMeal.price} per person</h2>
        <% if (selectedMeal.getImage().equals("") || selectedMeal.getImage() == null) {%>
        <img src="/images/i1.jpg" alt="Meal 1" style="width:256px;height:200px;">
        <% } else { %>
        <img src="${selectedMeal.image}" alt="Meal 1" style="width:256px;height:200px;">
        <%	} %>
        <h3>${selectedMeal.date}</h3>
        <h3>Category: ${selectedMeal.category}</h3>
        <%
            Connection conn = Database.connectDatabase();
            ResultSet rs = Database.selectAttending(conn, selectedMeal.getMealID(), null);
            int count = 0;
            rs.first();
            do {
                count++;
            } while(rs.next());
            pageContext.setAttribute("cap", selectedMeal.getCapacity() - count);
        %>
        <h3>Spots Left: ${cap}/${selectedMeal.capacity}</h3>

        <h2>Location</h2>
        <h3>${selectedMeal.city}, ${selectedMeal.country}</h3>
        <h3>${selectedMeal.address}</h3>
        <h3>${selectedMeal.postal}</h3>


        <h2>Cancelation Info</h2>
        <h3>Free Cancelation Before: ${selectedMeal.cancelBy}</h3>
        <h3>Cancelation Fee: $${selectedMeal.cancelationFee}</h3>

        <%--<h2>Host Rating</h2>--%>
        <%--<img src="images/i1.png" alt="Meal 1" style="width:200px;height:50px;">--%>
        <%--<h2>Guest Rating</h2>--%>
        <%--<img src="images/i1.png" alt="Meal 1" style="width:200px;height:50px;">--%>

        <h3>Description: </h3>
        <p>${selectedMeal.description}</p>

        <% User loggedUser = (User) session.getAttribute("user");
            pageContext.setAttribute("user", loggedUser);
            if (loggedUser != null) { %>
        <form  id="form" action="/meal/bookmeal/${selectedMeal.mealID}" autocomplete="on" method="POST">
            <button type="submit" class="button"><i class="fa fa-search"></i> Book Meal</button>
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
