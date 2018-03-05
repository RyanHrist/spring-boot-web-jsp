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
	<h1>eat'n greet</h1>
	<div class="search">
		<form  action="<%=request.getContextPath()%>/search" autocomplete="on" method="POST">
			<input type="text" placeholder="Search.." name="search">
			<button type="submit"><i class="fa fa-search"></i></button>
		</form>
	</div>

	<h1>${nothingFound}</h1>
	<img src="images/i1.jpg" alt="Meal 1" style="width:256px;height:200px;">
	<img src="images/i1.jpg" alt="Meal 2" style="width:256px;height:200px;">
	<img src="images/i1.jpg" alt="Meal 3" style="width:256px;height:200px;">

</div>

</body>
</html>
