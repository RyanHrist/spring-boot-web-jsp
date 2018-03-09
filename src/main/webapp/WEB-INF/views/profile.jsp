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

    <div class = "inner">
        <form action="/profile" autocomplete="on" method="POST">
            Host Name: <input type="text" name="hostname" id="hostname" value=" Ryan H" readonly=""><br>
            Photo: <input type="file" name="pic" id="upload" accept="image/*"  hidden >
            <img src="images/i1.png" alt="Meal 1" id = "pic" style="width:200px;height:200px;" > <br>
            Location: <input type="text" name="location" value="St. Catharines" id="location" readonly=""><br>
            Language: <input type="text" name="language" value="English"  id="language"readonly=""><br>
            About me: <textarea type = "textarea" name = "desciption" id="aboutme"  rows="10" cols="32" readonly="">
					This is my about me it is actually about me. Suprising I know. okay lets just keep testing to see if this is going to work..."
                </textarea><br>
            Host Rating: <input type="text" name="language" value="4.5" id="hostrating" readonly="" readonly=""><br>
            Guest Rating: <input type="text" name="language" value="4.2" id="guestrating" readonly="" readonly=""><br>
            <button type="button" onclick="myFunction()"> Edit </button>
            <button type="button" onclick="myFunction2()"> Save </button hidden>
        </form>
    </div>

</div>

<script>
    function myFunction() {
        document.getElementById("hostname").readOnly = false;
        document.getElementById("location").readOnly = false;
        document.getElementById("language").readOnly = false;
        document.getElementById("aboutme").readOnly = false;
        document.getElementById("upload").hidden = false;
    }

    function myFunction2() {
        document.getElementById("hostname").readOnly = true;
        document.getElementById("location").readOnly = true;
        document.getElementById("language").readOnly = true;
        document.getElementById("aboutme").readOnly = true;
        document.getElementById("upload").hidden = true;
    }
</script>
</body>
</html>

