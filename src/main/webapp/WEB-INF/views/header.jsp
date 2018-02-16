<%--
  Created by IntelliJ IDEA.
  User: Nancy
  Date: 1/31/2018
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <c:url value="../../resources/static/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
</head>
<body>
    <div class="navbar">
        <div class="dropdown">
            <button class="dropbtn">RYAN</button>
            <div class="dropdown-content">
                <a href="#">Profile</a>
                <a href="#">Account</a>
                <a href="#">Logout</a>
            </div>
        </div>
        <a href="#host">HOST A MEAL</a>
        <a href="#upcoming">UPCOMING MEALS</a>
    </div>
</body>
</html>
