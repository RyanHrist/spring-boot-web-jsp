<%@ page import="java.util.ArrayList" %>
<%@ page import="application.models.Meals" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %><%--
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
            ArrayList<Meals> previousMeals = (ArrayList<Meals>) session.getAttribute("upcomingMeals");
            String contextBookedMeal = (String) session.getAttribute("bookedMeal");
            pageContext.setAttribute("bookedMeal", contextBookedMeal);
            pageContext.setAttribute("upcomingMeals", upcomingMeals);
            pageContext.setAttribute("previousMeals", upcomingMeals);
            pageContext.setAttribute("user", loggedUser);
    %>
    <h1>Upcoming Meals</h1>
    <h3>${bookedMeal}</h3>
    <hr>

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
    <h3>${bookedMeal}</h3>
    <hr>

    <nav>
        <%
            if (previousMeals.size() != 0) {

                for (Meals meal:previousMeals) {
                    pageContext.setAttribute("meal", meal);

                    String date;
                    date = meal.getDate();
                    date=date.replaceAll("\\D","");
                    long DOM = Long.parseLong(date);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                    LocalDateTime now = LocalDateTime.now();
                    long CD =Long.parseLong(dtf.format(now));;
                    if(DOM>CD ) {
                        previousMeals.remove(meal);
                                         }
        %>
        <form  action="/meal" autocomplete="on" method="POST">
            <a href="/meal/${meal.mealID}"><img src ="${meal.image}" style="width:200px;height:100px;"> ${meal.description} </a>
        </form>
        <%}
        } else {%>
        <h3>You have no upcoming meals</h3>
        <%}%>

    </nav>









            <% } else { %>
    <h1>You must login to view Upcoming Meals.</h1>
    <% } %>

</div>




</body>
</html>
