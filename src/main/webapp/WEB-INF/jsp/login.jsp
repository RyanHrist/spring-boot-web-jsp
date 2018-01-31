<%--
  Created by IntelliJ IDEA.
  User: Ryan
  Date: 1/31/2018
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />

    <title>Hello</title>
</head>
<body>
<%@ include file = "header.jsp" %>
<font color="red">${message}</font>
<form method="post">
    Name : <input type="text" name="name" />
    Password : <input type="password" name="password" />
    <input type="submit" />
</form>
</body>
</body>
</html>
