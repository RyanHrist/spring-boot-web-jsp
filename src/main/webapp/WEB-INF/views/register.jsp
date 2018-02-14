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
<div id="container_demo" >
    <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
    <a class="hiddenanchor" id="toregister"></a>
        <div id="register" class="animate form">
            <form  action="<%=request.getContextPath()%>/" autocomplete="on" method="POST">
                <h1> Sign up </h1>
                <p>
                    <label for="emailsignup" class="youmail" data-icon="e" > Your email</label>
                    <input id="emailsignup" name="emailsignup" required="required" type="email" placeholder="mysupermail@mail.com"/>
                </p>
                <p>
                    <label for="passwordsignup" class="youpasswd" data-icon="p">Your password </label>
                    <input id="passwordsignup" name="passwordsignup" required="required" type="password" placeholder="eg. X8df!90EO"/>
                </p>
                <p class="signin button">
                    <input type="submit"/>
                </p>
                <p class="change_link">
                    Already a member ?
                    <a href="/login" class="to_register"> Go and log in </a>
                </p>
            </form>
        </div>

    </div>
</div>

</body>
</html>
