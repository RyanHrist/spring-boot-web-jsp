<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file ="home.jsp" %>
    <c:url value="../../resources/static/css/popup.css" var="popup" />
    <link href="${popup}" rel="stylesheet" />
</head>
<body>
<!-- The Modal -->
<div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">&times;</span>
        </div>
        <div class="modal-body">
            <p>Welcome to Eat'n Greet, try searching for a meal in your area!</p>
        </div>
    </div>

</div>
<script src="../../resources/static/js/temporaryPopup.js"></script>
</body>
</html>