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
        <form  action="<%=request.getContextPath()%>/" autocomplete="on" method="POST">
            First name: <input type="text" name="firstname" value=""
                               style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>
            Last name: <input type="text" name="lastname" value=""
                              style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>
            Email: <input type="text" name="emailsignup" value="example@test.com"
                          style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>
            Password: <input type="text" name="passwordsignup" value=""
                             style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>
            Confirm Password: <input type="text" name="passwordconfirm" value=""
                                     style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>
            Country: <input type="text" name="country" value=""
                            style="background-color:white;
                               border: solid 1px #2e2925;
                               height: 30px;
                               font-size:18px;
                               vertical-align:9px;color:#2e2925" ><br>
            Date of Birth
            <input type="date" name="bday">
            <br/> <br/>
            <input type="submit" value="Register">
        </form>
    </div>
</div>
</body>
</html>
