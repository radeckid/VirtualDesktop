<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 21.11.2019
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Wirtualna Przestrzeń</title>
    <link rel="icon" href="images/linkLogo.png">

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/offcanvas/">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="css/sticki-navbar.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="index.jsp">Wirtualna Przestrzeń</a>
    <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-collapse collapse" id="navbarCollapse" style="">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="index.jsp">Start <span class="sr-only"></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="contact.jsp">Kontakt</a>
            </li>
        </ul>
        <a class="btn btn-success btn-lg" role="button" href="login.jsp">Zaloguj się</a>
    </div>
</nav>

<%
    int statusCode = pageContext.getErrorData().getStatusCode();
    Throwable throwable = pageContext.getErrorData().getThrowable();
    String servletName = pageContext.getErrorData().getServletName();
    String requestUri = pageContext.getErrorData().getRequestURI();
%>

<main role="main" class="container">
    <div class="jumbotron">
        <h1><i class="fa fa-exclamation-triangle" style="color: orange"> </i> Wystąpił nieoczekiwany błąd
            <%
                if (statusCode != 0) {
                    out.print(" - " + statusCode);
                }
            %>
            <i class="fa fa-exclamation-triangle"
               style="color: orange"></i></h1>

        <div class="errorPage">
            <p>
                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseDrop"
                        aria-expanded="false" aria-controls="collapseExample">
                    Szczegóły <i class="fa fa-caret-down"></i>
                </button>
            </p>
            <div class="collapse" id="collapseDrop">
                <div class="card card-body">
                    <%
                        if (statusCode != 500) {
                            out.write("<h3>Error Details</h3>");
                            out.write("<strong>Status Code: </strong>" + statusCode + "<br>");
                            out.write("<strong>Requested URI: </strong>" + requestUri);
                        } else {
                            out.write("<h3>Exception Details</h3>");
                            out.write("<ul><li>Servlet Name: " + servletName + "</li>");
                            out.write("<li>Exception Name: " + throwable + "</li>");
                            out.write("<li>Requested URI: " + requestUri + "</li>");
                            out.write("<li>Exception Message: " + throwable.getMessage() + "</li>");
                            out.write("</ul>");
                        }
                    %>
                </div>
            </div>
        </div>

        <br>
        <p class="lead"><b>Przepraszamy za ten błąd. Jest nam bardzo przykro, że akurat ciebie to spotkało. Niezwłocznie nas powiadom,
            w którym momencie to się stało. Użyj do tego celu karty <a href="contact.jsp" style="color: #e83e8c"><b>kontakt</b></a>. Postaramy się
            pomóc.</b></p>
        <p class="lead"><a href="aboutus.jsp" style="color: #000062; "><b><b><i>Zespół DrimTim</i></b></b></a></p><br>
        <a class="btn btn-lg btn-primary" href="index.jsp" role="button">Wróć na stronę główną <i class="fa fa-arrow-right"></i></a>
    </div>
</main>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="js/jquery-slim.min.js"><\/script>')</script>
<script src="js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o"
        crossorigin="anonymous"></script>

</body>
</html>
