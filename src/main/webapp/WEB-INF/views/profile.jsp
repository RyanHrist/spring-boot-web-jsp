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
    <title>Profile</title>
</head>
<body>
<%@ include file = "header.jsp" %>


    <h1>Profile information</h1>
    <h1> ${UserController.userFirstName} ${UserController.userLastName}</h1>
    <h1> ${UserController.userEmail}</h1>



</body>
</html>
