<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 16.01.2020
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="vs.api.helpers.Authentication" %>
<%@ page import="vs.api.helpers.FoldersController" %>
<%@ page import="vs.api.helpers.MainPageController" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Object token = request.getSession().getAttribute("email");
    Object key = request.getSession().getAttribute("key");
    if (token != null && key != null) {
        if (!Authentication.checkToken(token.toString(), key.toString())) {
            throw new IllegalArgumentException();
        }
    } else {
        response.sendRedirect("/login.jsp");
    }


%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <title>Wirtualna Przestrzeń</title>
    <link rel="icon" href="images/linkLogo.png">

    <link rel="stylesheet" href="vendor/fontawesome-free/css/all.css">
    <link rel="stylesheet" type="text/css" href="css/component.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/drop_style.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/progressBarLoading.css">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">

    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.min.js" type="text/javascript"></script>

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
    <link href="css/offcanvas.css" rel="stylesheet">
</head>

<body class="bg-light">

<%--NAVBAR--%>
<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark" id="navbarColor01">
    <a class="navbar-brand mr-auto mr-md-0" href="mainpage.jsp">Wirtualna Przestrzeń</a>
    <button class="navbar-toggler p-0 border-0" type="button" data-toggle="offcanvas">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="navbar-collapse offcanvas-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="contact.jsp">Kontakt </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true"
                   aria-expanded="false">Konto</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="settings.jsp">
                        <i class="fas fa-user-cog fa-sm fa-fw mr-2 text-gray-400"></i>
                        Ustawienia</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                        Wyloguj
                    </a>
                </div>
            </li>
            <%
                Object userType = request.getSession().getAttribute("usertype");
                if (userType != null && (int) userType == 1) {
                    out.write("<li><input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Panel Administracyjny\" onclick=\"window.location='adminpage.jsp';\"/></li>");
                }
            %>
        </ul>
    </div>
</nav>

<%
    String filePath = request.getParameter("file");

    String user = String.valueOf((int) request.getSession().getAttribute("id"));

    FoldersController foldersController = new FoldersController(user, null);
    String userPath = foldersController.getUserPath();

    foldersController.getCurrentPath();

    String fileFullPath = userPath + "\\" + filePath;

    File file = new File(fileFullPath);
    ArrayList<String> fileContent = new ArrayList<String>();

    try {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            fileContent.add(next);
        }

        scanner.close();
    } catch (Exception ex) {
        fileContent.add(ex.getMessage());
    }
%>

<%--LOGO AND MAIN, AVAILABLE SIZE--%>
<main role="main" class="container">

    <form action="EditTextServlet" method="post">
        <div class="form-group mt-4">
            <label for="fileTextArea"> <b>Nazwa pliku: </b> </label>
            <label>
                <input type="text" hidden="hidden" name="fileName" value="<% out.print(filePath.substring(0, filePath.length() - 4)); %>">
                <input type="text" disabled="disabled" value="<% out.print(filePath.substring(0, filePath.length() - 4)); %>">
            </label>
            <textarea class="form-control" required="required" name="fileTextArea" id="fileTextArea" rows="12"><%
                for (String line : fileContent) {
                    out.println(line);
                }
            %></textarea>
        </div>
        <button class="btn btn-lg btn-success mt-2 shadow-lg" type="submit">Zapisz</button>
    </form>

</main>

<%--FOOTER--%>
<div class="text-center">
    <footer class="mastfoot mt-auto">
        <div class="inner">
            <p>DrimTim sp. z o.o. 2019</p>
        </div>
    </footer>
</div>

<%--Button activated on click send to this form--%>
<form method="post" action="LogOutUser" id="logOutForm"><input type="text" hidden="hidden" name="name"/></form>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Już nas opuszczasz?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Do zobaczenia nastepnym razem!</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Anuluj</button>
                <button class="btn btn-primary" type="submit" formaction="/LogOutUser" form="logOutForm">Wyloguj</button>
            </div>
        </div>
    </div>
</div>

<form method="get" id="deleteForm"><input type="text" hidden="hidden" id="form-value" name="file" value=""/></form>

<script src="js/popoverJS.js" type="text/javascript"></script>
<script src="js/jquery-3.3.1.slim.min.js"></script>
<script>window.jQuery || document.write('<script src="js/jquery-slim.min.js"><\/script>')</script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/offcanvas.js"></script>
<script src="js/dropzone.js"></script>
<script src="js/progressBarAnim.js"></script>
<script src="js/modalDeleteMainPage.js"></script>

</body>
</html>
