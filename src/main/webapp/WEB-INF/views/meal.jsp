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
        <h1>Steak and Potatoes</h1>
        <h2>Host: ${mealHost.name}</h2>
        <img src="${selectedMeal.image}" alt="Meal 1" style="width:200px;height:200px;">
        <h2>Location: 123 test road, St. Catharines, Ontario</h2>
        <h2>Price: 123</h2>
        <h2>Date: Dec 12, 2018 4PM</h2>

        <h2>Host Rating</h2>
        <img src="images/i1.png" alt="Meal 1" style="width:200px;height:50px;">
        <h2>Guest Rating</h2>
        <img src="images/i1.png" alt="Meal 1" style="width:200px;height:50px;">

        <h3>Description</h3>
        <p>${selectedMeal.description}</p>
        <p>This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test. This is what the meal is going to be this is just a test</p>

        <button type="button" onclick="">Book Meal </button>
    </div>
    <% } else {%>
        <h1>This meal does not exist...</h1>
    <% } %>
</body>
</html>
