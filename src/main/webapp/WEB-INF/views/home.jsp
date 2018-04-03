<%@ page import="java.sql.Connection" %>
<%@ page import="application.Database" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="application.models.Meals" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
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
	<h1>eat'n greet with mitch</h1>
	<div class="search">
			<script>
                function putQueryOnSubmit(){
                    var action_src = "/search/" + document.getElementsByName("searchQuery")[0].value;
                    var your_form = document.getElementById('search_form');
                    your_form.action = action_src ;
                }
			</script>
		<form  id="search_form" onsubmit="putQueryOnSubmit()" autocomplete="on" method="POST">
			<input type="text" placeholder="Search.." name="searchQuery">
			<button type="submit"><i class="fa fa-search"></i></button>
		</form>
	</div>

	<h1>${nothingFound}</h1>

	<%
		ArrayList<Meals> foundMeals = (ArrayList<Meals>) session.getAttribute("foundMeals");
		if(foundMeals != null) {
		for (Meals meals: foundMeals) {
		    pageContext.setAttribute("localMeal", meals);
		    if (meals.getImage().equals("") || meals.getImage() == null) {
	%>
	<a href ="/meal/${localMeal.mealID}"><img src="/images/i1.jpg" alt="Meal 1" style="width:256px;height:200px;"></a>
	<% } else { %>
	<a href ="/meal/${localMeal.mealID}"><img src="${localMeal.image}" alt="Meal 1" style="width:256px;height:200px;"></a>
	<%	}
	  }
	}%>
	<%--} else {--%>
		<%--ArrayList<Meals> allMeals = (ArrayList<Meals>) session.getAttribute("allMeals");--%>
		<%--for (Meals meals: allMeals) {--%>
			<%--pageContext.setAttribute("allMeals", meals);--%>

	<%--%>--%>
	<%--<img src="${allMeals.image}" alt="Meal 1" style="width:256px;height:200px;">--%>
	<%--<% }--%>
    <%--}--%>
    <%--%>--%>

</div>
</body>
</html>
