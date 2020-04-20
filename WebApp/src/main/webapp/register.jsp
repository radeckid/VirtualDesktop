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
    <title>Wirtualna Przestrzeń - Rejestracja</title>
    <link rel="icon" href="images/linkLogo.png">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/cover/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


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
                <a class="nav-link" href="index.jsp">Start</a>
                <a class="nav-link" href="aboutus.jsp">O nas</a>
                <a class="nav-link" href="contact.jsp">Kontakt</a>
                <a class="nav-link" href="login.jsp">Zaloguj</a>
            </nav>
        </div>
    </header>

    <form class="form-signin" method="post" action="RegisterUser">
        <div class="text-center mb-4">
            <p style="font-size: xx-large">Rejestracja:</p>
        </div>

        <div class="form-label-group">
            <input type="text" id="inputFirstName" class="form-control" placeholder="Imię" required=""
                   autofocus="autofocus" name="inputFirstName">
            <label for="inputFirstName">Imię</label>
        </div>

        <div class="form-label-group">
            <input type="text" id="inputLastName" class="form-control" placeholder="Nazwisko" required=""
                   autofocus="autofocus" name="inputLastName">
            <label for="inputLastName">Nazwisko</label>
        </div>

        <div class="form-label-group">
            <input type="email" id="inputEmail" class="form-control" placeholder="Adres e-mail" required=""
                   autofocus="autofocus" name="inputEmail">
            <label for="inputEmail">Adres e-mail</label>
        </div>

        <div class="form-label-group">
            <input type="password" id="inputPassword" class="form-control" placeholder="Hasło" required=""
                   autofocus="autofocus" name="inputPassword" maxlength="32" minlength="8">
            <label for="inputPassword">Hasło</label>
        </div>

        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="regulations"> Akceptuję regulamin strony <a href="index.jsp"
                                                                                          style="color: #e83e8c">wirtualnaprzestrzeń.pl</a>
                zawarty na tej stronie <a href="pdf/regulamin-wirtualna-przestrzen.pdf" target="_blank"
                                          style="color: #00cc00">Regulamin</a>
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Zarejestruj</button>
        <span style="color:red">
                <%
                    Object errorUserExist = request.getSession().getAttribute("errorUserExist");
                    if (errorUserExist != null && errorUserExist.toString().length() != 0) {
                        out.print(errorUserExist.toString());
                    }
                %>
            </span>
    </form>

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
