<%@ page import="java.util.ArrayList" %>
<%@ page import="application.models.Meals" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.amazonaws.util.StringUtils" %>
<%@ page import="java.util.ListIterator" %><%--
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
<c:url value="${pageContext.request.contextPath}/resources/static/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<div class="centered">

    <%  User loggedUser = (User) session.getAttribute("user");
        if(loggedUser != null) {
            ArrayList<Meals> allMeals = (ArrayList<Meals>) session.getAttribute("upcomingMeals");
            String contextBookedMeal = (String) session.getAttribute("bookedMeal");
            pageContext.setAttribute("bookedMeal", contextBookedMeal);
            pageContext.setAttribute("upcomingMeals", allMeals);
            pageContext.setAttribute("user", loggedUser);
    %>
    <h1>Upcoming Meals</h1>
    <h3>${bookedMeal}</h3>
    <hr>

    <nav>
            <%
                ArrayList<Meals> previousMeals = new ArrayList<Meals>();
                ArrayList<Meals> upcomingMeals = new ArrayList<Meals>();
                if (allMeals.size() != 0) {
                    for (int i=0;i<allMeals.size();i++) {



                        String date;
                        date = allMeals.get(i).getDate();
                        date = date.replaceAll("\\D", "");

                        System.out.println(date);
                        long DOM = Long.parseLong(date);
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                        LocalDateTime now = LocalDateTime.now();
                        long CD = Long.parseLong(dtf.format(now));
                        CD = CD*10;

                        if (CD > DOM) {
                            previousMeals.add(allMeals.get(i));
                        }
                        else{
                            upcomingMeals.add(allMeals.get(i));
                        }


                        // if (date)
                        // add meals

                    }
                }
                    if (upcomingMeals.size() != 0) {
                        for (Meals meal:upcomingMeals) {
                            pageContext.setAttribute("meal", meal);


            %>
            <form  action="/meal" autocomplete="on" method="POST">
            <a href="/meal/${meal.mealID}"><img src ="${meal.image}" style="width:200px;height:100px;"> ${meal.description} </a>
            </form>
            <%}
            } else {%>
            <h3>You have no upcoming meals</h3>
            <%}
            %>

    </nav>

    <h1>Previous Meals</h1>
    <hr>

    <nav>
        <%
            if (previousMeals.size() != 0) {

                for (Meals meal:previousMeals) {
                    pageContext.setAttribute("meal", meal);


        %>
        <form  action="/meal" autocomplete="on" method="POST">
            <a href="/meal/${meal.mealID}"><img src ="${meal.image}" style="width:200px;height:100px;"> ${meal.description} </a>
        </form>
        <%}
        } else {%>
        <h3>You have no upcoming meals</h3>
        <%}%>

    </nav>









            <% } else { %>
    <h1>You must login to view Upcoming Meals.</h1>
    <% } %>

</div>




</body>
</html>
