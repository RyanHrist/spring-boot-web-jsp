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
<div class="centered">
    <% User loggedUser = (User) session.getAttribute("user");
        pageContext.setAttribute("user", loggedUser);
        if(loggedUser != null) {
    %>
    <h1>Upcoming Meals</h1>
    <nav>
        <ul>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Rice and Veggie Dinner  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Rice and Stuff </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Christmas Dinner  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Test   </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> TESTEE  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> TESTEEEE  </button> </li>
        </ul>
    </nav>

    <h1>Previous Meals</h1>
    <nav>
        <ul>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Rice and Veggie Dinner  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Steak  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Burger  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Fries  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Test  </button> </li>
            <li> <button type="button" onclick=""> <img src ="images/i1.png" style="width:200px;height:100px;"> Tesstt </button> </li>
        </ul>
    </nav>
    <% } else { %>
    <h1>You must login to view Upcoming Meals.</h1>
    <% } %>

</div>




</body>
</html>
