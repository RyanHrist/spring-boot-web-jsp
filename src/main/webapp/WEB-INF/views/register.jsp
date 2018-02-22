<%@ page import="application.controllers.RegisterController" %><%--
  Created by IntelliJ IDEA.
  User: Nancy
  Date: 2/13/2018
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file = "header.jsp" %>
    <title>Registration</title>

</head>
<body>

<div class="centered">
    <h1>eat'n greet</h1>

    <div class= "inner">
        <form action="/action_page.php">
            First name: <input type="text" name="FirstName" value=""><br>
            Last name: <input type="text" name="LastName" value=""><br>
            Email: <input type="text" name="emailsignup" value="example@test.com"><br>
            Password: <input type="text" name="passwordsignup" value=""><br>
            Confirm Password: <input type="text" name="passwordconfirm" value=""><br>
            Country: <input type="text" name="country" value=""><br>
            Date of Birth
            <input type="date" name="bday">
            <br/> <br/>
            <input type="submit" value="Register">
        </form>
    </div>
</div>
</body>
</html>
