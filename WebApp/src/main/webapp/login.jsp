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
    <title>Wirtualna Przestrzeń - Logowanie</title>
    <link rel="icon" href="images/linkLogo.png">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/cover/">

    <link rel="stylesheet" href="css/customSwitch.css">
    <link rel="stylesheet" href="vendor/fontawesome-free/css/fontawesome.min.css">

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

    <script src="https://www.google.com/recaptcha/api.js?hl=pl"></script>
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
                <a class="nav-link active" href="login.jsp">Zaloguj</a>
            </nav>
        </div>
    </header>

    <form class="form-signin" method="post" action="LogInUser">
        <div class="text-center mb-4">
            <img class="mb-5" src="images/wpLOGO2.png" alt="" width="50%" height="auto">
            <p>Logowanie:</p>
        </div>

        <%
            Cookie[] cookies = request.getCookies();
            String emailCk = "";
            String passwordCk = "";
            String checkedRemMe = "";

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookieEmail")) {
                    emailCk = "value=\"" + cookie.getValue() + "\"";
                    ;
                } else if (cookie.getName().equals("cookiePassword")) {
                    passwordCk = "value=\"" + cookie.getValue() + "\"";
                } else if (cookie.getName().equals("cookieRememberMe")) {
                    if ("remember-me".equals(cookie.getValue())) {
                        checkedRemMe = "checked=\"checked\"";
                    }
                }
            }
        %>

        <div class="form-label-group">
            <input type="email" id="inputEmail" class="form-control" placeholder="Adres e-mail" required="" <% out.print(emailCk); %>
                   autofocus="autofocus" name="inputEmail">
            <label for="inputEmail">Adres e-mail</label>
        </div>

        <div class="form-label-group">
            <input type="password" id="inputPassword" class="form-control" placeholder="Hasło" required=""
                   name="inputPassword" <% out.print(passwordCk); %>>
            <label for="inputPassword">Hasło</label>
        </div>
        <span style="color:red">
        <%
            Object errorLogIn = request.getSession().getAttribute("errorLogIn");
            if (errorLogIn != null && errorLogIn.toString().length() != 0) {
                out.print(errorLogIn);
            }
        %>
        </span>

        <div class="mb-3 d-flex justify-content-center">

            <div class="switchToggle" style="float: left;">
                <input type="checkbox" id="checkboxRememberMe" <% out.print(checkedRemMe); %> name="checkboxRememberMe" value="remember-me">
                <label for="checkboxRememberMe">Toggle</label>
            </div>
            <div style="float: left; padding-left: 10px;">Zapamiętać dane logowania?</div>
        </div>

        <div class="g-recaptcha mb-3 d-flex justify-content-center" data-sitekey="6LcQFMcUAAAAAMtXgaa6nrLVkhbqQCxIZa8v8dIp"></div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj</button>
        <p class="mt-5 mb-3 text-muted text-center">© 2019</p>
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
<script src="js/bootstrap4-toggle.min.js"></script>

</body>
</html>
