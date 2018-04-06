<%@ page import="application.models.User" %><%--
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
    <title>Account Info</title>
</head>
<body>
<%@ include file = "header.jsp" %>
<div class="centered">
    <h1>Account Info</h1>
</div>

<div class="centered">
    <% User loggedUser = (User) session.getAttribute("user");
        pageContext.setAttribute("user", loggedUser);
        if (loggedUser != null) {
    %>

    <div class="eag-container">

        <form action="/account" autocomplete="on" method="POST">
            <div class="eag-row">
                <div style = 'float: right'><button type="button" onclick="editFields()" class="editbutton" align = "right"> Edit <i class="fa fa-edit"></i></button></div>
            </div>
            <div class="eag-row">
                <div class="eag-col-half">
                    <h4>Name</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="name" id="name" value="${user.name}" readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-user"></i></div>
                    </div>
                </div>
                <div class="eag-col-half">
                    <h4>Email</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="email" id="email" value="${user.email}" readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-envelope"></i></div>
                    </div>
                </div>
            </div>
            <div class="eag-row">
                <div class="eag-col-half">
                    <h4>Password</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="pass" id="pass" value="${user.password}" readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-key"></i></div>
                    </div>
                </div>
                <div class="eag-col-half">
                    <h4>Location</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="location" id="location" value="${user.country}"  readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-globe"></i></div>
                    </div>
                </div>
            </div>
            <br/>
            <br/>
            <div class="eag-row">
                <h4>Credit Card Number</h4>
                <div class="eag-input-group eag-input-group-icon">
                    <input type="text" name="num" id="ccnum" value="${user.cccnumber}"  readonly=""/>
                </div>
            </div>

            <div class="eag-row">
                <div class="eag-col-half">
                    <h4>Card Type</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="cctype" value="Master Card" id="cctype" readonly=""/>
                    </div>
                </div>
                <div class="eag-col-half">
                    <h4>CVV</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="ccdigits" id="ccdigits" value="${user.cccvv}"  readonly=""/>
                    </div>
                </div>
            </div>
            <br/>
            <input type="submit" onclick="submit()" id = "submitbutton" class="button" disabled = "false" style="opacity:0.5"/>
        </form>



        <br/>
    </div>
    <% } else { %>
    <h1> You must be logged in to view your Profile.</h1>
    <% } %>
</div>



<script>
    function editFields() {
        document.getElementById("name").readOnly = false;
        document.getElementById("pass").readOnly = false;
        document.getElementById("location").readOnly = false;
        document.getElementById("ccnum").readOnly = false;
        document.getElementById("cctype").readOnly = false;
        document.getElementById("ccdigits").readOnly = false;
        document.getElementById("submitbutton").disabled = false;
        document.getElementById("submitbutton").style.opacity = "1";
    }

    function submit() {
        document.getElementById("name").readOnly = true;
        document.getElementById("pass").readOnly = true;
        document.getElementById("location").readOnly = true;
        document.getElementById("ccnum").readOnly = true;
        document.getElementById("cctype").readOnly = true;
        document.getElementById("ccdigits").readOnly = true;
    }
</script>
</body>
</html>
