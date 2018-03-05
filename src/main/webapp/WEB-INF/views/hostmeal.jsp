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
    <h1>Host a meal:</h1>

<div class= "inner">
    <h1>${unsuccessMessage}</h1>

    <form  action="/hostmeal" autocomplete="on" method="POST">
        Meal: <input type="text" name="mealName" value="" style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>

        Description: <input type="text" name="mealDesciption" value="" style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>

        Cancellation: <input type="text" name="cancellation" value="$2.5" style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>

        City: <input type="text" name="city" value="" style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>

        Date of Meal
        <input type="date" name="bday">
        <br/> <br/>
        <input type="submit" value="HostMeal">
    </form>
</div>
</body>
</html>
