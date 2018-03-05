<%@ page import="application.controllers.UserController" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <!---  Login Modal CSS --->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--- This is breaking the dropdown but is necessary for login popup --->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!--- End of Modal CSS ---->
    <c:url value="../../resources/static/css/loginPopup.css" var="loginpopup" />

    <link href="${loginpopup}" rel="stylesheet" />

    <c:url value="../../resources/static/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <meta name="viewport" content="width=device-width, initial-scale=1">


</head>
<body>
<!-- INCLUDE HEADER -->
<div class="navbar123">
    <div class="home" style="float:left;">
        <a href="/">HOME</a>
    </div>

    <%boolean loggedIn = UserController.loggedIn;
        if (loggedIn) {%>
    <div class="dropdownEAT">
        <button class="dropbtnEAT">${UserController.userFirstName}</button> <!-- $username}--->
        <div class="dropdown-contentEAT">
            <a href="/profile">Profile</a>
            <a href="/account">Account</a>
            <a href="/logout">Logout</a>
        </div>
    </div>

    <a href="/hostmeal/">HOST A MEAL</a>
    <a href="/upcoming_meals">UPCOMING MEALS</a>
    <%} else {%>
    <a href="/registration">REGISTER</a>
    <a href ="#" id="myBtn">LOGIN</a>
    <%}%>

</div>
<div class="greater-container">
    <div class="container">
        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header" style="padding:35px 50px;">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
                    </div>
                    <div class="modal-body" style="padding:40px 50px;">
                        <form  action="<%=request.getContextPath()%>/" autocomplete="on" method="POST">
                            <div class="form-group">
                                <label for="loginUsername"><span class="glyphicon glyphicon-user"></span> Username</label>
                                <input type="text" class="form-control" id="loginUsername" name="loginUsername" placeholder="Enter email">
                            </div>
                            <div class="form-group">
                                <label for="loginPassword"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
                                <input type="text" class="form-control" id="loginPassword" name="loginPassword" placeholder="Enter password">
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" value="" checked>Remember me</label>
                            </div>
                            <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
                        <p>Not a member? <a href="/registration" class="signUpLink">Sign Up</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../resources/static/js/loginPopup.js"></script>
</body>
</html>