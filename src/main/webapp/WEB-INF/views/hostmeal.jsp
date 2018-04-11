<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>eat'n greet</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%
    pageContext.setAttribute("user", user);
    String mealID = (String) session.getAttribute("mealID");
    pageContext.setAttribute("mealID", mealID);
    if (user != null) {
%>
<div class="centered">
    <h1>Host a Meal</h1>

    <h1>${unsuccessMessage}</h1>

    <div class="eag-container" style="max-width: 46em">
        <form action="/hostmeal" autocomplete="on" method="POST">
            <div class="eag-row">
                <h4>Meal Information</h4>
                <div class="eag-col-half">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="mealName" placeholder="Name your meal" required/>
                        <div class="eag-input-icon"><i class="fa fa-utensils"></i></div>
                    </div>
                </div>
                <div class="eag-col-half">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="number" name="mealPrice" placeholder="Choose a price" required/>
                        <div class="eag-input-icon"><i class="fa fa-dollar-sign"></i></div>
                    </div>
                </div>
            </div>

            <div class="eag-row">
                <div class="eag-col-half">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="mealCategory" placeholder="Choose a category" required/>
                        <div class="eag-input-icon"><i class="fa fa-list-ul"></i></div>
                    </div>
                </div>
                <div class="eag-col-half">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="number" name="mealCapacity" placeholder="Maximum guests" required/>
                        <div class="eag-input-icon"><i class="fa fa-users"></i></div>
                    </div>
                </div>
            </div>

            <div class="eag-row">
                <div class="eag-col-half">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="date" name="mealDate" placeholder="Meal Date" required/>
                        <div class="eag-input-icon"><i class="fas fa-calendar-check"></i></div>
                    </div>
                </div>
                <div class="eag-col-half">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="time" name="mealTime" placeholder="Meal Time" required/>
                        <div class="eag-input-icon"><i class="far fa-clock"></i></div>
                    </div>
                </div>
            </div>


            <div class="eag-row">
                <h4>Cancellation Deadline</h4>
                <div class="eag-col-third">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="date" name="mealCancelDate" placeholder="Meal Cancel Date" required/>
                        <div class="eag-input-icon"><i class="fas fa-calendar-times"></i></div>
                    </div>
                </div>
                <div class="eag-col-third">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="time" name="mealCancelTime" placeholder="Meal Cancel Time" required/>
                        <div class="eag-input-icon"><i class="far fa-clock"></i></div>
                    </div>
                </div>
                <div class="eag-col-third">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="number" name="mealCancelFee" placeholder="Fee" required/>
                        <div class="eag-input-icon"><i class="fas fa-hand-holding-usd"></i></div>
                    </div>
                </div>
            </div>

            <div class="eag-row">
                <h4>Meal Location</h4>
                <div class="eag-col-half">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="mealCountry" placeholder="Country" required/>
                        <div class="eag-input-icon"><i class="fa fa-globe"></i></div>
                    </div>
                </div>
                <div class="eag-col-half">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="mealCity" placeholder="City" required/>
                        <div class="eag-input-icon"><i class="fas fa-location-arrow"></i></div>
                    </div>
                </div>
            </div>

            <div class="eag-row">
                <div class="eag-col-half">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="mealAddress" placeholder="Address" required/>
                        <div class="eag-input-icon"><i class="fas fa-road"></i></div>
                    </div>
                </div>
                <div class="eag-col-half">
                    <div class="eag-input-group eag-input-group-icon">
                        <input type="text" name="mealPostalCode" placeholder="Postal Code" required/>
                        <div class="eag-input-icon"><i class="fab fa-hubspot"></i></div>
                    </div>
                </div>
            </div>
            <br/>
            <div class="eag-row">
                <input type="submit" value="Host" class="button" id="submitbutton">
            </div>
            <br/>
            <br/>
        </form>
    </div>


    <% } else { %>
    <h1>Login to host a meal!</h1>
    <% } %>
</div>
</body>
</html>