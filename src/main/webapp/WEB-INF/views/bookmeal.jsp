<<<<<<< Updated upstream
<%@ page import="application.models.Meals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file = "header.jsp" %>
    <title>Book Meal</title>

</head>
<body>
=======
<html>
<head>
    <link rel="stylesheet" type="text/css" href="main.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<!-- INCLUDE HEADER -->
<%@ include file = "header.jsp" %>
<!-- END INCLUDE HEADER -->
>>>>>>> Stashed changes

<div class="centered">
    <h1>eat'n greet</h1>

    <div class= "inner">
<<<<<<< Updated upstream
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
            <input type="submit" value="Book Meal" class = "button">
        </form>
        <% } else {%>
            <h2> You must be logged in to book a meal</h2>
        <% } %>
=======
        <form action="/action_page.php">
            Meal Title: <input type="text" name="mealtitle" value=""><br>
            Host Name: <input type="text" name="hostname" value=""><br>
            Location: <input type="text" name="location" value=""><br>
            Price: <input type="text" name="price" value=""><br>
            Date: <input type="text" name="date" value=""><br>
            Desciption: <textarea type = "textarea" name = "desciption" rows="10" cols="32"></textarea><br>
            Photo: <input type="file" name="pic" accept="image/*">
            <button type="button" onclick="" class = "button" > Setup Meal </button>
        </form>
>>>>>>> Stashed changes
    </div>
</div>
</body>
</html>
