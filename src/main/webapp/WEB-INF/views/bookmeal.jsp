<%@ page import="application.models.Meals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file = "header.jsp" %>
    <title>Book Meal</title>

</head>
<body>

<div class="centered">
    <h1>eat'n greet</h1>

    <div class= "inner">
        <%
            boolean correctURL = (boolean) session.getAttribute("correctURL");
            if(correctURL) {
                User mealHost = (User) session.getAttribute("mealHost");
                Meals selectedMeal = (Meals) session.getAttribute("selectedMeal");
                pageContext.setAttribute("selectedMeal", selectedMeal);
                pageContext.setAttribute("mealHost", mealHost);
        %>
        <form  action="/meal/bookmeal/${selectedMeal.mealID}/confirmation" autocomplete="on" method="POST">
            Host name: ${mealHost.name}<br>

            Location: ${selectedMeal.address}<br>

            Meal: ${selectedMeal.mealTitle} <br>

            Time: ${selectedMeal.time}
            <input type="submit" value="Book Meal">
        </form>
        <% } else {%>
            <h2> You must be logged in to book a meal</h2>
        <% } %>
    </div>
</div>
</body>
</html>
