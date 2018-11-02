<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/oxygen_font.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/registration_login_main.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">
    <script src="<c:url value = "/scripts/jquery.js"/>"></script>
    <script src="<c:url value="/scripts/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/scripts/registration.js"/>"></script>
</head>
<body>
<div id="navbar">
    <jsp:include page="../includes/navbar.jsp"/>
</div>
<main class="regForm" style="background: url('<c:url
        value="/img/shooting-star-Michigan-jwhitephoto-2.jpg"/>') no-repeat center center; background-size: cover">
    <div class="main-w3layouts wrapper">
        <h1>Registration:</h1>
        <div class="main-agileinfo">
            <div class="agileits-top">
                <form id="form" method="post">
                    <div class="form-group">
                        <label for="emailField" class="text label">Enter email:</label>
                        <input class="text" type="text" name="email" id="emailField" placeholder="Enter your email" required>
                    </div>
                    <div class="form-group">
                        <label for="passField" class="text label">Enter password:</label>
                        <input class="text" type="password" name="password" id="passField" placeholder="Enter your password" required>
                    </div>
                    <div class="form-group">
                        <label for="passConfirmField" class="text label">Confirm password:</label>
                        <input class="text" type="password" name="passwordVerify" id="passConfirmField" placeholder="Confirm your password" required>
                    </div>
                    <div class="form-group">
                        <label for="countryField" class="text label">Choose country:</label>
                            <select name="country" class="custom-select" id="countryField" required>
                                <option value="" disabled selected>Select your option</option>
                                <c:forEach items="${listCountries}" var="country">
                                    <option value="${country}">
                                            ${country}
                                    </option>
                                </c:forEach>
                            </select>
                    </div>
                    <div class="form-group">
                        <label for="genderField" class="text label">Choose gender:</label>
                        <select name="gender" class="custom-select" id="genderField" required>
                            <option value="" disabled selected>Select your option</option>
                            <option>Male</option>
                            <option>Female</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for = "bDayField" class="text label">Choose birthdate:</label>
                        <input type="date" name="bDay" id="bDayField" class="custom-select date" required>
                    </div>
                    <div class="wthree-text">
                        <label class="anim">
                            <input type="checkbox" name="licenceCB" id="licenceCBField" class="checkbox" required>
                            <span>I Agree To The Terms & Conditions</span>
                        </label>
                        <div class="clear"> </div>
                    </div>
                    <input type="submit" value="SIGNUP" id="btnSubmit">
                </form>
                <p>Don't have an Account? <a href="#"> Login Now!</a></p>
            </div>
        </div>
    </div>
</main>
<div id="footer">
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
