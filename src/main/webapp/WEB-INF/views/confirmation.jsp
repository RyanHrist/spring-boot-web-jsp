<%@ page import="application.models.Meals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="main.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<%@ include file = "header.jsp" %>
<c:url value="../../resources/static/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<div class="centered">
    <h1>eat'n greet</h1>
    <h1>Congratulations on booking a meal</h1>
    </div>
</div>
</body>
</html>
