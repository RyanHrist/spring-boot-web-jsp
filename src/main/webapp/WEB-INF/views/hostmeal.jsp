<%--
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
<% User loggedUser = (User) session.getAttribute("user");
    pageContext.setAttribute("user", loggedUser);
    String mealID = (String) session.getAttribute("mealID");
    pageContext.setAttribute("mealID", mealID);
    if(loggedUser != null) {
%>
<div class="centered">
    <h1>Host a meal</h1>

    <div class= "inner">
    <h1>${unsuccessMessage}</h1>

    <form  action="/hostmeal/" autocomplete="on" method="POST">
        Meal: <input type="text" name="mealName" value="Meal title" style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>

        Description: <input type="text" name="mealDescription" value="Meal description" style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>

        Price: <input type="text" name="mealPrice" value="Meal Price" style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>

        Image URL: <input type="text" name="imageURL" value="www.example.com/image.jpg" style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>

        City: <input type="text" name="city" value="City" style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>

        Date of Meal
        <input type="date" name="date">
        <br/> <br/>

        <input type="submit" value="Host Meal">
    </form>
</div>
<% } else { %>
<h1>Login to host a meal!</h1>
<% } %>
</div>
</body>
</html>
