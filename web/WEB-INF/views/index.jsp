<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Desvelado</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/oxygen_font.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/home_main.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">
    <script src="<c:url value = "/scripts/jquery.js"/>"></script>
    <script src="<c:url value="/scripts/bootstrap.min.js"/>"></script>
</head>
<body>
<div id="pageContainer">
    <div id="navbar">
        <jsp:include page="../includes/navbar.jsp"/>
    </div>
    <header class="masthead text-white text-center"
            style="background: url('<c:url
                    value="/img/shooting-star-Michigan-jwhitephoto-2.jpg"/>') no-repeat center center; background-size: cover">
        <div class="overlay"></div>
        <div class="container">
            <div class="row">
                <div class="col-xl-9 mx-auto">
                    <h1 class="mb-5">Welcome to Desvelado!</h1>
                </div>
            </div>
        </div>
    </header>
    <div id="footer">
        <jsp:include page="../includes/footer.jsp"/>
    </div>
</div>
</body>
</html>