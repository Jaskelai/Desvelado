<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/oxygen_font.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/registration_login_main.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">
    <script src="<c:url value = "/scripts/jquery.js"/>"></script>
    <script src="<c:url value="/scripts/bootstrap.min.js"/>"></script>
</head>
<body>
<div id="navbar">
    <jsp:include page="../includes/navbar.jsp"/>
</div>
<main class="profile" style="background: url('<c:url
        value="/img/shooting-star-Michigan-jwhitephoto-2.jpg"/>') no-repeat center center; background-size: cover">
    <div class="main-w3layouts wrapper">
        <h1>Profile:</h1>
        <div class="main-agileinfo">
            <div class="agileits-top">
                <form id="form" method="post">

                </form>
            </div>
        </div>
    </div>
</main>
<div id="footer">
    <jsp:include page="../includes/footer.jsp"/>
</body>
</html>
