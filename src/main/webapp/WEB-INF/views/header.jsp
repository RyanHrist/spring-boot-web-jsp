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
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Eat'n Greet</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/login">Login</a></li>
                <%
                    // Example of how to write java in JSP
                    boolean isLoggedIn = true;
                    if (isLoggedIn) {
                %>
                <li><a href="/profile">Profile</a></li>
                <%
                    }
                %>>
                <li><a href="/register">Register</a></li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
