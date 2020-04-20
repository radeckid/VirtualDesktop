<%@ page import="vs.api.helpers.Authentication" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Object key = request.getSession().getAttribute("key");
    Object email = request.getSession().getAttribute("email");
    if (email != null && key != null) {
        if (Authentication.checkToken(email.toString(), key.toString())) {
            response.sendRedirect("/mainpage.jsp");
        }
    }
%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Wirtualna Przestrzeń</title>
    <link rel="icon" href="images/linkLogo.png">

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/cover/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css" crossorigin="anonymous">


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
    <link href="css/cover.css" rel="stylesheet">
</head>
<body class="text-center">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
    <header class="masthead mb-auto">
        <div class="inner">
            <h3 class="masthead-brand">Wirtualna Przestrzeń</h3>
            <nav class="nav nav-masthead justify-content-center">
                <a class="nav-link" href="aboutus.jsp">O nas</a>
                <a class="nav-link" href="contact.jsp">Kontakt</a>
                <a class="nav-link" href="login.jsp">Zaloguj</a>
            </nav>
        </div>
    </header>


    <main role="main" class="inner cover">
        <h1 class="cover-heading">Wirtualna Przestrzeń</h1>
        <p class="lead">Szukasz miejsca gdzie możesz przechowywać swoje wartościowe pliki? Oto twoje idealne
            miejsce!</p>
        <p class="lead">
            <a href="register.jsp" class="btn btn-lg btn-secondary">Zarejestruj</a>
        </p>
    </main>

    <footer class="mastfoot mt-auto">
        <div class="inner">
            <p>DrimTim sp. z o.o. 2019</p>
        </div>
    </footer>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

</body>
</html>

