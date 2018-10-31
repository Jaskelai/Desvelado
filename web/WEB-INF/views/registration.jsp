<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/oxygen_font.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/registration_main.css"/>">
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
        value="/img/shooting-star-Michigan-jwhitephoto-2.jpg"/>') no-repeat center center; background-size: cover">>
    <div class="row justify-content-center">
        <div class="col-md-7">
            <div class="card">
                <div class="card-header">Register</div>
                <div class="card-body">
                    <form method="post" id="forms">
                        <div class="form-group row">
                            <label for="emailField" class="col-md-4 col-form-label text-md-right">Email address:</label>
                            <div class="col-md-6">
                                <input type="text" name="email" class="form-control" id="emailField"
                                       placeholder="Enter your Email" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="passField" class="col-md-4 col-form-label text-md-right">Password:</label>
                            <div class="col-md-6">
                                <input type="password" name="password" class="form-control" id="passField"
                                       placeholder="Enter your Password"
                                       required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="passConfirmField" class="col-md-4 col-form-label text-md-right">Confirm
                                password:</label>
                            <div class="col-md-6">
                                <input type="password" name="passwordVerify" class="form-control" id="passConfirmField"
                                       required
                                       placeholder="Confirm your Password">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="countryField" class="col-md-4 col-form-label text-md-right">Country:</label>
                            <div class="col-md-6">
                                <select name="country" class="form-control" id="countryField" required>
                                    <option value="" selected disabled hidden>Choose here</option>
                                    <c:forEach items="${listCountries}" var="country">
                                        <option value="${country}">
                                                ${country}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="sexField" class="col-md-4 col-form-label text-md-right">Choose sex:</label>
                            <div class="col-md-6">
                                <select name="sex" class="form-control" id="sexField" required>
                                    <option value="" selected disabled hidden>Choose here</option>
                                    <option>Male</option>
                                    <option>Female</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="bDay" class="col-md-4 col-form-label text-md-right">Birthday:</label>
                            <div class="col-md-6">
                                <input type="date" name="bDay" id="bDay" class="input-group date" required>
                            </div>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" name="licenceCB" class="form-check-input" id="licenceCB" required>
                            <label class="form-check-label" for="licenceCB">Agree with licence</label>
                        </div>
                        <button type="submit" class="btn btn-primary col-md-6 offset-md-4" id="btnSubmit">Submit
                        </button>
                        <p id="result_reg"><${requestScope.error}/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
<div id="footer">
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
