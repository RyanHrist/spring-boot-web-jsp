<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Sign Up Form</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

    <link rel="stylesheet" href="css/style.css">

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="centered">
    <h1>Register</h1>
    <h3>${unsuccessMessage}</h3>

<div class="eag-container">
    <form action="/registration" autocomplete="on" method="POST">

        <div class="eag-row">
            <h4>Account</h4>
            <div class="eag-col-half">
                <div class="eag-input-group eag-input-group-icon">
                    <input type="text" name="firstname" placeholder="First Name" required/>
                    <div class="eag-input-icon"><i class="fa fa-user"></i></div>
                </div>
            </div>
            <div class="eag-col-half">
                <div class="eag-input-group eag-input-group-icon">
                    <input type="text" name="lastname" placeholder="Last Name" required/>
                    <div class="eag-input-icon"><i class="fa fa-user"></i></div>
                </div>
            </div>
        </div>
        <div class="eag-row">
            <div class="eag-input-group eag-input-group-icon">
                <input type="email" name="emailsignup" placeholder="Email Address" required/>
                <div class="eag-input-icon"><i class="fa fa-envelope"></i></div>
            </div>
            <div class="eag-input-group eag-input-group-icon">
                <input type="password" name="passwordsignup" placeholder="Password" required/>
                <div class="eag-input-icon"><i class="fa fa-key"></i></div>
            </div>
        </div>
        <div class="eag-row">
            <div class="eag-col-half">
                <h4>Date of Birth</h4>
                <div class="eag-input-group">
                    <div class="eag-col-third">
                        <input type="text" name="day" placeholder="DD"/>
                    </div>
                    <div class="eag-col-third">
                        <input type="text" name="month" placeholder="MM"/>
                    </div>
                    <div class="eag-col-third">
                        <input type="text" name="year" placeholder="YYYY" required/>
                    </div>
                </div>
            </div>
            <div class="eag-col-half">
                <h4>Country</h4>
                <div class="eag-input-group eag-input-group-icon">
                    <input type="text" name="country" placeholder="Country" required/>
                    <div class="eag-input-icon"><i class="fa fa-globe"></i></div>
                </div>
            </div>
        </div>
        <div class="eag-row">
            <h4>Payment Details</h4>
            <div class="eag-input-group">
                <input type="radio" name="payment-method" value="visa" id="payment-method-card" checked="true"/>
                <label for="payment-method-card"><span><i class="fa fa-cc-visa"></i>Visa</span></label>
                <input type="radio" name="payment-method" value="mastercard" id="payment-method-paypal"/>
                <label for="payment-method-paypal"> <span><i class="fa fa-cc-mastercard"></i>Mastercard</span></label>
            </div>
            <div class="eag-input-group eag-input-group-icon">
                <input type="text" name="ccnum" placeholder="Card Number"/>
                <div class="eag-input-icon"><i class="fa fa-credit-card"></i></div>
            </div>
            <div class="eag-col-half">
                <div class="eag-input-group eag-input-group-icon">
                    <input type="text" name="cccvv" placeholder="Card CVC"/>
                    <div class="eag-input-icon"><i class="fa fa-user"></i></div>
                </div>
            </div>
            <div class="eag-col-half">
                <div class="eag-input-group">
                    <select name="ccexpmonth">
                        <option>01</option>
                        <option>02</option>
                        <option>03</option>
                        <option>04</option>
                        <option>05</option>
                        <option>06</option>
                        <option>07</option>
                        <option>08</option>
                        <option>09</option>
                        <option>10</option>
                        <option>11</option>
                        <option>12</option>
                    </select>
                    <select name="ccexpyear">
                        <option>2018</option>
                        <option>2019</option>
                        <option>2020</option>
                        <option>2021</option>
                        <option>2022</option>
                        <option>2023</option>
                        <option>2024</option>
                        <option>2025</option>
                        <option>2026</option>
                        <option>2027</option>
                        <option>2028</option>
                        <option>2029</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="eag-row">
            <h4>Terms and Conditions</h4>
            <div class="eag-input-group">
                <input type="checkbox" onclick="myFunction()" id="terms"/>
                <label for="terms">I accept the terms and conditions for signing up to this service, and hereby confirm
                    I have read the privacy policy.</label>
            </div>
        </div>
        <div class="eag-row">
            <input type="submit" value="Register" class="button" id="submitbutton" disabled="false" style="opacity:0.5">
        </div>
        <br/>
        <br/>
    </form>
</div>
</div>


<%--<script src="js/index.js"></script>--%>
<script src="../../resources/static/js/loginPopup.js"></script>

<script>
    function myFunction() {
        if (document.getElementById("terms").checked) {
            document.getElementById("submitbutton").disabled = false;
            document.getElementById("submitbutton").style.opacity = "1";
        } else {
            document.getElementById("submitbutton").disabled = true;
            document.getElementById("submitbutton").style.opacity = "0.5";
        }
    }

</script>


</body>

</html>
