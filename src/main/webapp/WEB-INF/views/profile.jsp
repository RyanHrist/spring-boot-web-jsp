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
<%@ include file="header.jsp" %>
<div class="centered">
    <h1>Profile</h1>
</div>

<div class="centered">
        <% User userBeingViewed = (User) session.getAttribute("userBeingViewed");
        Boolean profileExists = (Boolean) session.getAttribute("existingProfile");
        Boolean viewingOwnProfile = (Boolean) session.getAttribute("viewingOwnProfile");
        User loggedUser = (User) session.getAttribute("user");
        pageContext.setAttribute("user", loggedUser);
        // If a user is logged in and viewing their own profile
        if (loggedUser != null && viewingOwnProfile) {
    %>

    <div class="eag-container">
        <form action="/registration" autocomplete="on" method="POST">
            <div class="eag-row">
                <img src="${user.profilePicture}" alt="Profile Picture??" id="pic" style="width:200px;height:200px;">
            </div>
            <div class="eag-row">
                <button type="button"  onclick="myFunction3()" class ="button" id = "upload2" hidden = "true"> Upload Photo </button>
            </div>
            <div class="eag-row">
                <div class="eag-col-half">
                    <h4>Name</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="hostname" id="hostname" value="${user.name}" readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-user"></i></div>
                    </div>
                </div>
                <div class="eag-col-half">
                    <h4>Location</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="location" value="${user.country}" id="location" readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-globe"></i></div>
                    </div>
                </div>
            </div>
            <div class="eag-row">
                <div class="eag-col-half">
                    <h4>Language</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="language" value="${user.language}" id="language" readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-font"></i></div>
                    </div>
                </div>
                <div class="eag-col-half">
                    <h4>Rating</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="hostrating" value="4.5" id="hostrating" readonly="" readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-star"></i></div>
                    </div>
                </div>
            </div>
            <div class="eag-row">
                <h4>About ${user.name}</h4>
                <div class="eag-input-group eag-input-group-icon">
                <textarea type="textarea" name="description" id="aboutme" rows="10" cols="32" readonly="">
                    ${user.userDescription}
                </textarea>
                </div>
            </div>
            <br/>
        </form>

        <input type="file" name="imageURL" accept="image/*" id="selectedFile" style="display: none;" >
        <button type="button" onclick="myFunction()" class="button"> Edit</button>
        <button type="button" onclick="myFunction2()" class="button"> Save</button>
        <br/>
    </div>

        <% // If profile ID exists and its not own, or profile exists and a user is logged in
    } else if ((profileExists && !viewingOwnProfile) || profileExists && loggedUser == null) {
        pageContext.setAttribute("userBeingViewed", userBeingViewed);%>

    <div class="eag-container">
            <div class="eag-row">
                <% if (userBeingViewed.getProfilePicture().equals("") || userBeingViewed.getProfilePicture() == null) {%>
                <img src="../images/i1.jpg" alt="Profile Picture546" style="width:200px;height:200px;">
                <% } else { %>
                <img src="${userBeingViewed.getProfilePicture()}" alt="Profile Picture258" style="width:200px;height:200px;">
                <% } %>
            </div>
            <div class="eag-row">
                <div class="eag-col-half">
                    <h4>Name</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="hostname" id="hostname" value="${userBeingViewed.name}" readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-user"></i></div>
                    </div>
                </div>
                <div class="eag-col-half">
                    <h4>Location</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="location" value="${userBeingViewed.country}" id="location" readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-globe"></i></div>
                    </div>
                </div>
            </div>
            <div class="eag-row">
                <div class="eag-col-half">
                    <h4>Language</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="language" value="${userBeingViewed.language}" id="language" readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-font"></i></div>
                    </div>
                </div>
                <div class="eag-col-half">
                    <h4>Rating</h4>
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="hostrating" value="4.5" id="hostrating" readonly="" readonly=""/>
                        <div class="eag-input-icon"><i class="fa fa-star"></i></div>
                    </div>
                </div>
            </div>
            <div class="eag-row">
                <h4>About ${userBeingViewed.name}</h4>
                <div class="eag-input-group eag-input-group-icon">
                <textarea type="textarea" name="description" id="aboutme" rows="10" cols="32" readonly="">
                    ${userBeingViewed.userDescription}
                </textarea>
                </div>
            </div>
            <br/>
    </div>

        <% // If profile trying to be viewed does not exist
    } else if (!profileExists) { %>
    <h1>This profile does not exist.</h1>
        <% } else { %>
    <h1> You must be logged in to view your Profile.</h1>
        <% } %>




    <script>
        function myFunction() {
            document.getElementById("hostname").readOnly = false;
            document.getElementById("location").readOnly = false;
            document.getElementById("language").readOnly = false;
            document.getElementById("aboutme").readOnly = false;
            document.getElementById("upload2").hidden = false;
        }

        function myFunction2() {
            document.getElementById("hostname").readOnly = true;
            document.getElementById("location").readOnly = true;
            document.getElementById("language").readOnly = true;
            document.getElementById("aboutme").readOnly = true;
            document.getElementById("upload2").hidden = true;
        }

        function myFunction3(){
            document.getElementById('selectedFile').click();

        }
    </script>
</body>
</html>

