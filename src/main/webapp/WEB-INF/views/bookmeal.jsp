<%@ page import="application.models.Meals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Book Meal</title>

</head>
<body>


<%@ include file = "header.jsp" %>

<div class="centered">
    <h1>Confirmation</h1>

        <%
            boolean correctURL = (boolean) session.getAttribute("correctURL");
            if(correctURL) {
                User mealHost = (User) session.getAttribute("mealHost");
                Meals selectedMeal = (Meals) session.getAttribute("selectedMeal");
                pageContext.setAttribute("selectedMeal", selectedMeal);
                pageContext.setAttribute("mealHost", mealHost);
        %>
        <div class="eag-container">
        <form  action="/meal/bookmeal/${selectedMeal.mealID}/confirmation" autocomplete="on" method="POST">

            <div class="eag-row">
                <h3>Host name: <b>${mealHost.name}</b></h3><br>
            </div>
            <div class="eag-row">
                <h3> Location: <b>${selectedMeal.address}</b></h3><br>
            </div>
            <div class="eag-row">
                <h3>Meal: <b>${selectedMeal.mealTitle}</b> </h3><br>
            </div>
            <div class="eag-row">
                <h3>Time: <b>${selectedMeal.time}</b></h3>
            </div>
            <div class="eag-row">
                <h2>Price: $${selectedMeal.price}</h2>
            </div>
            <input type="submit" value="Book Meal" class = "button">
        </form>
        </div>
        <% } else {%>
            <h2> You must be logged in to book a meal</h2>
        <% } %>
    </div>
</body>
</html>
