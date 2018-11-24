<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="inputTag" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Videos</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/oxygen_font.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/navbar.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/video_catalog_main.css"/>">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script src="<c:url value = "/scripts/jquery.js"/>"></script>
    <script src="<c:url value="/scripts/bootstrap.min.js"/>"></script>
    <script src="<c:url value ="/scripts/videoCatalog.js"/>"></script>
</head>
<body>
<div id="navbar">
    <jsp:include page="../includes/navbar.jsp"/>
</div>
<main class="profile" style="background: url('<c:url
        value="/img/shooting-star-Michigan-jwhitephoto-2.jpg"/>') no-repeat center center; background-size: cover">
    <div class="row top-buffer">
        <inputTag:video id="dQw4w9WgXcQ" description="HAHA"/>
        <inputTag:video id="g_9YilEyN48" description="???????"/>
        <inputTag:video id="O4irXQhgMqg" description=""/>
    </div>
    <div class="modal fade" id="modalContactForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Add a video from youtube</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body mx-3">
                    <div class="md-form mb-5">
                        <div class="form-check form-check-inline">
                            <i class="fas fa-link prefix grey-text fa-2x"></i>
                            <input type="text" id="linkInput" class="form-control"
                                   placeholder="Give a link for the video" maxlength="50">
                        </div>
                    </div>

                    <div class="md-form mb-5">
                        <div class="form-check form-check-inline">
                            <i class="fas fa-heading prefix grey-text fa-2x"></i>
                            <input type="text" id="headerInput" class="form-control"
                                   placeholder="Give the name" maxlength="30">
                        </div>
                    </div>
                    <div class="md-form mb-5">
                        <div class="form-check form-check-inline">
                            <i class="fas fa-tag prefix grey-text fa-2x"></i>
                            <textarea id="descriptionInput" class="form-control"
                                      placeholder="Give the description " maxlength="500"></textarea>
                        </div>
                    </div>

                </div>
                <div class="errors">
                        ${requestScope.errorUpload}
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button class="btn btn-unique" id="btnUpload">Upload<</button>
                </div>
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test='${sessionScope.username != null}'>
            <a class="btn-float" href="" data-toggle="modal" data-target="#modalContactForm"><i
                    class="fas fa-plus fa-3x iconAdd"></i></a>
        </c:when>
    </c:choose>
</main>
<div id="footer">
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
