<%--
  Created by IntelliJ IDEA.
  User: Nancy
  Date: 2/22/2018
  Time: 11:41 AM
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
    <% User loggedUser = (User) session.getAttribute("user");
        pageContext.setAttribute("user", loggedUser);
        if (loggedUser != null) {
    %>
    <h1>Account Info</h1>
    <div class = "inner">
        <form action="/account" autocomplete="on" method="POST">
            Name: <input type="text" name="name" id="name" value="${user.name}" readonly=""><br/>

            <%--i dont think user should be able to change their email--%>
            Email: <input type="text" name="email" id="email" value="${user.email}" readonly=""><br/>

            Password: <input type="text" name="pass" id="pass" value="${user.password}" readonly=""><br/>

            Location: <input type="text" name="location" id="location" value="${user.country}"  readonly=""><br/>

            Credit Card Info: <br>

            Credit Card Number: <input type="text" name="num" id="ccnum" value="${user.cccnumber}"  readonly=""> <br/>

            Type: <input type="text" name="cctype" value="Master Card" id="cctype" readonly=""> <br/>

            CVV: <input type="text" name="ccdigits" id="ccdigits" value="${user.cccvv}"  readonly=""/> <br/>

            <button type="button" onclick="myFunction()" class="button"> Edit </button>
            <input type="submit" onclick="myFunction2()" value="Save" class="button"/>
        </form>
    </div>
    <% } else { %>
    <h1> You must be logged in to view your Profile.</h1>
    <% } %>

</div>

<script>
    function myFunction() {
        document.getElementById("name").readOnly = false;
        //should not be able to edit email
        //document.getElementById("email").readOnly = true;
        document.getElementById("pass").readOnly = false;
        document.getElementById("location").readOnly = false;
        document.getElementById("ccnum").readOnly = false;
        document.getElementById("cctype").readOnly = false;
        document.getElementById("ccdigits").readOnly = false;

    }

    function myFunction2() {
        document.getElementById("name").readOnly = true;
        //document.getElementById("email").readOnly = true;
        document.getElementById("pass").readOnly = true;
        document.getElementById("location").readOnly = true;
        document.getElementById("ccnum").readOnly = true;
        document.getElementById("cctype").readOnly = true;
        document.getElementById("ccdigits").readOnly = true;
    }
</script>
</body>
</html>
