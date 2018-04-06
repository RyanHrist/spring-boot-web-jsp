<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>Sign Up Form</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

    <link rel="stylesheet" href="css/style.css">


</head>

<body>
<%@ include file = "header.jsp" %>

<div class="centered">
    <h1>Register</h1>
</div>

<div class="eag-container">
    <form>
        <div class="eag-row">
            <h4>Accounts</h4>
            <div class="eag-input-group eag-input-group-icon">
                <input type="text" name="firstname" placeholder="Full Name"/>
                <div class="eag-input-icon"><i class="fa fa-user"></i></div>
            </div>
            <div class="eag-input-group eag-input-group-icon">
                <input type="email" name="emailsignup" placeholder="Email Address"/>
                <div class="eag-input-icon"><i class="fa fa-envelope"></i></div>
            </div>
            <div class="eag-input-group eag-input-group-icon">
                <input type="password" placeholder="Password"/>
                <div class="eag-input-icon"><i class="fa fa-key"></i></div>
            </div>
        </div>
        <div class="eag-row">
            <div class="eag-col-half">
                <h4>Date of Birth</h4>
                <div class="eag-input-group">
                    <div class="eag-col-third">
                        <input type="text" placeholder="DD"/>
                    </div>
                    <div class="eag-col-third">
                        <input type="text" placeholder="MM"/>
                    </div>
                    <div class="eag-col-third">
                        <input type="text" placeholder="YYYY"/>
                    </div>
                </div>
            </div>
            <div class="eag-col-half">
                <h4>Gender</h4>
                <div class="eag-input-group">
                    <input type="radio" name="gender" value="male" id="gender-male"/>
                    <label for="gender-male">Male</label>
                    <input type="radio" name="gender" value="female" id="gender-female"/>
                    <label for="gender-female">Female</label>
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
                <input type="text" placeholder="Card Number"/>
                <div class="eag-input-icon"><i class="fa fa-credit-card"></i></div>
            </div>
            <div class="eag-col-half">
                <div class="eag-input-group eag-input-group-icon">
                    <input type="text" placeholder="Card CVC"/>
                    <div class="eag-input-icon"><i class="fa fa-user"></i></div>
                </div>
            </div>
            <div class="eag-col-half">
                <div class="eag-input-group">
                    <select>
                        <option>01 Jan</option>
                        <option>02 Jan</option>
                    </select>
                    <select>
                        <option>2015</option>
                        <option>2016</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="eag-row">
            <h4>Terms and Conditions</h4>
            <div class="eag-input-group">
                <input type="checkbox" id="terms"/>
                <label for="terms">I accept the terms and conditions for signing up to this service, and hereby confirm I have read the privacy policy.</label>
            </div>
        </div>
        <div class="eag-row">
            <input type="submit" value="Register" class = "button">
        </div>
    </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>



<script  src="js/index.js"></script>




</body>

</html>
