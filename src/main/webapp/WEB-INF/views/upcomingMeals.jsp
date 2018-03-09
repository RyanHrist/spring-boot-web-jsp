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
            pageContext.setAttribute("upcommingMeals", upcomingMeals);
            pageContext.setAttribute("user", loggedUser);
    %>
    <h1>${bookedMeal}</h1>
    <h1>Upcoming Meals</h1>
    <nav>
        <ul>
            <%
                if (upcomingMeals.size() != 0) {
                    for (Meals meal:upcomingMeals) {
                        pageContext.setAttribute("meal", meal);
            %>
            <form  action="/meal" autocomplete="on" method="POST">
            <li> <a href="/meal/${meal.mealID}"><img src ="${meal.image}" style="width:200px;height:100px;"> ${meal.description} </a></li>
            </form>
        </ul>
            <%}
            } else {%>
            <h3>You have no upcoming meals</h3>
            <%}%>



    </nav>
    <h1>Previous Meals</h1>
    <nav>
        <ul>
            <li> <button type="button" onclick=""> <img src ="https://www.w3schools.com/images/w3schools_green.jpg" style="width:200px;height:100px;"> Rice and Veggie Dinner  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Steak  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Burger  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Fries  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Test  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Tesstt </button> </li>
        </ul>
    </nav>

    <% } else { %>
    <h1>You must login to view Upcoming Meals.</h1>
    <% } %>

</div>




</body>
</html>
