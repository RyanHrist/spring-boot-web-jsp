<%@ page import="application.models.User" %><%--
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


<div class="centered">
        <%  User userBeingViewed = (User) session.getAttribute("userBeingViewed");
            Boolean profileExists = (Boolean) session.getAttribute("existingProfile");
            Boolean viewingOwnProfile = (Boolean) session.getAttribute("viewingOwnProfile");
            User loggedUser = (User) session.getAttribute("user");
            pageContext.setAttribute("user", loggedUser);
            // If a user is logged in and viewing their own profile
            if (loggedUser != null && viewingOwnProfile) {
        %>
        <h1>${user.name}</h1>
    <div class = "inner">
        <form action="/profile" autocomplete="on" method="POST">
            Host Name: <input type="text" name="hostname" id="hostname" value="${user.name}" readonly=""><br>
            <img src="${user.profilePicture}" alt="Meal 1" id = "pic" style="width:200px;height:200px;" > <br>
            Location: <input type="text" name="location" value="${user.cccity}" id="location" readonly=""><br>
            Language: <input type="text" name="language" value="${user.language}"  id="language"readonly=""><br>
            About me: <textarea type = "textarea" name = "desciption" id="aboutme"  rows="10" cols="32" readonly="">
					${user.userDescription}
                </textarea><br>
            Host Rating: <input type="text" name="language" value="4.5" id="hostrating" readonly="" readonly=""><br>
            Guest Rating: <input type="text" name="language" value="4.2" id="guestrating" readonly="" readonly=""><br>
            <button type="button" onclick="myFunction()"> Edit </button>
            <button type="button" onclick="myFunction2()"> Save </button>
        </form>
    </div>
    <% // If profile ID exists and its not own, or profile exists and a user is logged in
    } else if ((profileExists && !viewingOwnProfile) || profileExists && loggedUser == null) {
                pageContext.setAttribute("userBeingViewed", userBeingViewed);%>
    <div class = "inner">
            Host Name: ${userBeingViewed.name}<br>
            Photo: <input type="file" name="pic" id="upload" accept="image/*"  hidden >
            <img src="${userBeingViewed.profilePicture}" alt="Meal 1" id = "pic" style="width:200px;height:200px;" > <br>
            Location: ${userBeingViewed.cccity}, ${userBeingViewed.ccprovince}, ${userBeingViewed.cccountry}<br>
            Language: <br> ${userBeingViewed.language}
            About me:<br> ${userBeingViewed.userDescription} <br>
            Host Rating: <br>
    </div>
    <% // If profile trying to be viewed does not exist
    } else if (!profileExists){ %>
    <h1>This profile does not exist.</h1>
    <% } else { %>
    <h1> You must be logged in to view your Profile.</h1>
    <% } %>
</div>

<script>
    function myFunction() {
        document.getElementById("hostname").readOnly = false;
        document.getElementById("location").readOnly = false;
        document.getElementById("language").readOnly = false;
        document.getElementById("aboutme").readOnly = false;
        document.getElementById("upload").hidden = false;
    }

    function myFunction2() {
        document.getElementById("hostname").readOnly = true;
        document.getElementById("location").readOnly = true;
        document.getElementById("language").readOnly = true;
        document.getElementById("aboutme").readOnly = true;
        document.getElementById("upload").hidden = true;
    }
</script>
</body>
</html>

