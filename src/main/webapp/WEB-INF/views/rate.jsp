<%--
  Created by IntelliJ IDEA.
  User: Nancy
  Date: 3/6/2018
  Time: 8:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file = "header.jsp" %>

<div class="centered">

    <h1>Write Review</h1>
    <h1>RYAN HRISTOVSKI</h1>
    <img src="images/i1.png" alt="Meal 1" style="width:200px;height:200px;">
    <h2>St. Catharines, Ontario</h2>
    <h2>English</h2>
    <h2>Hospitality</h2>
    <form>
        <input type="radio" name="hospitality" value="1"> 1
        <input type="radio" name="hospitality" value="2"> 2
        <input type="radio" name="hospitality" value="3"> 3
        <input type="radio" name="hospitality" value="4"> 4
        <input type="radio" name="hospitality" value="5"> 5
    </form>
    <h2>Cleanliness</h2>
    <form>
        <input type="radio" name="cleanliness" value="1"> 1
        <input type="radio" name="cleanliness" value="2"> 2
        <input type="radio" name="cleanliness" value="3"> 3
        <input type="radio" name="cleanliness" value="4"> 4
        <input type="radio" name="cleanliness" value="5"> 5
    </form>
    <h2>Meal</h2>
    <form>
        <input type="radio" name="meal" value="1"> 1
        <input type="radio" name="meal" value="2"> 2
        <input type="radio" name="meal" value="3"> 3
        <input type="radio" name="meal" value="4"> 4
        <input type="radio" name="meal" value="5"> 5
    </form>

    <h3>Comments</h3>
    <textarea rows="4" cols="50">
			this guy was okay at food
		</textarea>
    <br/>
    <input type="submit" value="Submit Rating">
</div>

</body>
</html>
