<%@ page import="application.models.User" %><%--
  Created by IntelliJ IDEA.
  User: Nancy
  Date: 2/22/2018
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<%@ include file = "header.jsp" %>


<div class="centered">
        <% User loggedUser = (User) session.getAttribute("user");
            pageContext.setAttribute("user", loggedUser);
            if (loggedUser != null) {
        %>
        <h1>${user.name}</h1>

    <img src="images/i1.jpg" alt="Meal 1" style="width:256px;height:200px;">
    <h2>St. Catharines, Ontario</h2>
    <h2>English</h2>
    <h2>About Me</h2>

    <p>This is where I write about me. This should be editable. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>

    <h2>Host Rating</h2>
    <img src="images/i1.jpg" alt="Meal 1" style="width:256px;height:200px;">

    <h2>Guest Rating</h2>
    <img src="images/i1.jpg" alt="Meal 1" style="width:256px;height:200px;">

    <h2>Meals I'm Hosting</h2>
    <img src="images/i1.jpg" alt="Meal 1" style="width:256px;height:200px;">

    <p>This is a meal description.</p>
    <% } else { %>
    <h1> You must be logged in to view your Profile.</h1>
    <% } %>
</div>
</body>
</html>

