<%@ page import="vs.api.helpers.Authentication" %>
<%@ page import="vs.api.helpers.FoldersController" %>
<%@ page import="vs.api.helpers.MainPageController" %>
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

    Object name = request.getSession().getAttribute("id");
    FoldersController foldersController = null;
    if (name != null) {
        foldersController = new FoldersController(name.toString(), (String) session.getAttribute("folderPath"));
        foldersController.clear();
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
        <%--        <form class="form-inline my-2 my-lg-0">--%>
        <%--            <input class="form-control mr-md-2" type="text" placeholder="Szukaj" aria-label="Search">--%>
        <%--            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Szukaj</button>--%>
        <%--        </form>--%>
    </div>
</nav>

<%--LOGO AND MAIN, AVAILABLE SIZE--%>
<main role="main" class="container">
    <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
        <img class="mr-3" src="images/linkLogo.png" alt="" width="48" height="48">
        <div class="lh-100" style="width: 100%">
            <%
                out.print(MainPageController.getProgressBar(request));
            %>
        </div>
    </div>

    <%--DRAG AND DROP--%>
    <p>
        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseDrop"
                aria-expanded="false" aria-controls="collapseExample"><i class="fas fa-cloud-upload-alt"></i>
            Prześlij pliki
        </button>
        <button class="btn btn-info ml-2 float-md-right" data-toggle="popover" title="Nazwa:" data-placement="left" data-html='true'
                data-content="<form method='post' action='AddDirectoryServlet'><input type='text' name='folderName' required='required'><br><br><button class='btn btn-primary' type='submit'>Stwórz</button></form>">
            <i class="fas fa-plus"></i>
            Utwórz folder
        </button>
    </p>
    <div class="collapse" id="collapseDrop">
        <div class="card card-body">
            <form action="SendFilesServlet" method="post" class="dropzone needsclick dz-clickable" id="upload-files-dragdrop">
                <div class="dz-message needsclick" style="color: #4e555b">
                    Upuść lub kliknij tutaj aby przesłać pliki.
                </div>
            </form>
            <form action="UploadFileServlet" method="post">
                <button class="btn btn-success" style="float: right;" type="submit">
                    Zapisz
                </button>
            </form>
        </div>
    </div>

    <ol class="breadcrumb">
        <%
            if (foldersController != null) {
                out.println(foldersController.getFoldersMenu());
            }
        %>
    </ol>

    <%--LOADED DATA--%>
    <div class="my-3 p-3 bg-white rounded shadow-sm">
        <h6 class="border-bottom border-gray pb-2 mb-0">Twoje pliki</h6>
        <%
            if (foldersController != null) {
                out.println(foldersController.getAllFilesFromDir((String) session.getAttribute("folderPath")));
            }
        %>
    </div>

    <%--            <div class="my-3 p-3 bg-white rounded shadow-sm">--%>
    <%--                <h6 class="border-bottom border-gray pb-2 mb-0">"Może jakieś reklamy czy coś :D"</h6>--%>
    <%--                <div class="media text-muted pt-3">--%>
    <%--                    <svg class="mr-2 rounded" width="32" height="32" focusable="false">--%>
    <%--                        <title>Placeholder</title>--%>
    <%--                        <rect width="100%" height="100%" fill="#007bff"></rect>--%>
    <%--                        <text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text>--%>
    <%--                    </svg>--%>
    <%--                    <img src="images/file-word-regular.svg" height="30" width="30" title="Coś" alt="coś">--%>
    <%--                    <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">--%>
    <%--                        <div class="d-flex justify-content-between align-items-center w-100">--%>
    <%--                            <strong9 class="text-gray-dark">Full Name</strong>--%>
    <%--                        </div>--%>
    <%--                        <span class="d-block">@username</span>--%>
    <%--                    </div>--%>
    <%--                    <a class="btn btn-danger btn-sm" href="" ><i class="far fa-trash-alt"></i> Usuń</a>--%>

    <%--                </div>--%>
    <%--            </div>--%>
    <%--    <button class="btn btn-success btn-lg" data-toggle="popover-x" data-target="#myPopover30a" data-placement="auto">Auto (Any)</button>--%>
    <%--    <button class="btn btn-success btn-lg" data-toggle="popover-x" data-target="#myPopover30a" data-placement="auto">Auto (Any)</button>--%>
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
<!-- Delete Modal-->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Usuń:</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body" id="modal-delete-body">Czy aby napewno chcesz usunąć plik/folder <strong><label id="delete-name"></label></strong>?
                <br>
                Jeśli to folder zostanie usunięta również jego zawartość!!!
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Anuluj</button>
                <button class="btn btn-danger" type="submit" form="deleteForm">USUŃ</button>
            </div>
        </div>
    </div>
</div>

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