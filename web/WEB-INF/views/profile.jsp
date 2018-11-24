<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="mytags" prefix="mydate" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/oxygen_font.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script src="<c:url value = "/scripts/jquery.js"/>"></script>
    <script src="<c:url value="/scripts/bootstrap.min.js"/>"></script>
</head>
<body>
<div id="navbar">
    <jsp:include page="../includes/navbar.jsp"/>
</div>
<mydate:dateNumber date="1542237543000"></mydate:dateNumber>
<mydate:dateNumber date="1542300000000"></mydate:dateNumber>
<main class="profile" style="background: url('<c:url
        value="/img/shooting-star-Michigan-jwhitephoto-2.jpg"/>') no-repeat center center; background-size: cover"></main>
<div id="footer">
    <jsp:include page="../includes/footer.jsp"/>
</body>
</html>
