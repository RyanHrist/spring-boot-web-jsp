<%@ page import="java.util.ArrayList" %>
<%@ page import="application.models.Meals" %><%--
  Created by IntelliJ IDEA.
  User: Nancy
  Date: 2/22/2018
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>eat'n greet</title>
</head>
<body>
<%@ include file = "header.jsp" %>
<div class="centered">

    <%  User loggedUser = (User) session.getAttribute("user");
        if(loggedUser != null) {
            ArrayList<Meals> upcomingMeals = (ArrayList<Meals>) session.getAttribute("upcomingMeals");
            String contextBookedMeal = (String) session.getAttribute("bookedMeal");
            pageContext.setAttribute("bookedMeal", contextBookedMeal);
            pageContext.setAttribute("upcomingMeals", upcomingMeals);
            pageContext.setAttribute("user", loggedUser);
    %>
    <h1>Upcoming Meals</h1>
    <h1>${bookedMeal}</h1>

    <nav>
            <%
                if (upcomingMeals.size() != 0) {
                    for (Meals meal:upcomingMeals) {
                        pageContext.setAttribute("meal", meal);
            %>
            <form  action="/meal" autocomplete="on" method="POST">
            <a href="/meal/${meal.mealID}"><img src ="${meal.image}" style="width:200px;height:100px;"> ${meal.description} </a>
            </form>
            <%}
            } else {%>
            <h3>You have no upcoming meals</h3>
            <%}%>



    </nav>
    <h1>Previous Meals</h1>
    <%
        if (false) {
    %>
    <form  action="/meal" autocomplete="on" method="POST">
        <a href="/meal/${meal.mealID}"><img src ="${meal.image}" style="width:200px;height:100px;"> ${meal.description} </a>
    </form>
    <%} else {%>
    <h3>You have no previous meals</h3>
    <% } %>
    <%--<%--%>
        <%--if (upcomingMeals.size() != 0) {--%>
            <%--for (Meals previousMeal:upcomingMeals) {--%>
                <%--pageContext.setAttribute("meal", previousMeal);--%>
                <%--//TODO:JORDAN--%>
                <%--if(previousMeal.getDate() > todaysDateOrSomething) {--%>
    <%--%>--%>
    <%--<form  action="/meal" autocomplete="on" method="POST">--%>
        <%--<a href="/meal/${previousMeal.mealID}"><img src ="${previousMeal.image}" style="width:200px;height:100px;"> ${previousMeal.description} </a>--%>
    <%--</form>--%>
    <%--<%      }--%>
        <%--}--%>
    <%--} else {%>--%>
    <%--<h3>You have no upcoming meals</h3>--%>
    <%--<%}%>--%>


    <% } else { %>
    <h1>You must login to view Upcoming Meals.</h1>
    <% } %>

</div>




</body>
</html>
