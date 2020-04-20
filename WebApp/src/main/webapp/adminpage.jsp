<%@ page import="vs.api.helpers.Authentication" %>
<%@ page import="vs.api.helpers.AdminPanelController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 23.11.2019
  Time: 00:38
  To change this template use File | Settings | File Templates.
--%>

<%
    Object token = request.getSession().getAttribute("email");
    Object key = request.getSession().getAttribute("key");
    if (!Authentication.checkToken(token.toString(), key.toString())) {
        throw new IllegalArgumentException();
    }
    Object isAdmin = request.getSession().getAttribute("usertype");
    if ((int) isAdmin != 1) {
        throw new IllegalArgumentException("Failed to identify administrator.");
    }
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/linkLogo.png">

    <title>Wirtualna Przestrzeń - Panel Administracyjny</title>

    <!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="adminpage.jsp">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-users-cog"></i>
            </div>
            <div class="sidebar-brand-text mx-sm3">Panel Administracyjny</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item">
            <a class="nav-link" href="index.jsp">
                <i class="fas fa-home"></i>
                <span>Strona główna</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Zarządzanie
        </div>

        <!-- Nav Item - Tables -->
        <li class="nav-item active">
            <a class="nav-link" href="adminpage.jsp">
                <i class="fas fa-fw fa-table"></i>
                <span>Użytkownicy</span></a>
        </li>

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="contact.jsp">
                <i class="fas fa-address-book"></i>
                <span>Kontakt</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false">
                        </a>
                    </li>


                    <li class="nav-item no-arrow dropdown">
                        <h5>Zalogowany Administrator:</h5>
                        <h5 style="font-style: oblique">
                            <b><% out.print(request.getSession().getAttribute("name").toString() + " " + request.getSession().getAttribute("surname").toString()); %></b>
                        </h5>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <Button class="btn btn-danger btn-lg nav-item" type="button" data-toggle="modal" data-target="#logoutModal">Wyloguj</Button>

                </ul>


            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Wszyscy użytkownicy</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Imię</th>
                                    <th>Nazwisko</th>
                                    <th>E-mail</th>
                                    <th>Dostępne miejsce (MB)</th>
                                    <th>Typ konta</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Imię</th>
                                    <th>Nazwisko</th>
                                    <th>E-mail</th>
                                    <th>Dostępne miejsce (MB)</th>
                                    <th>Typ konta</th>
                                    <th></th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <%
                                    out.print(AdminPanelController.LoadAllUsersTable());
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>DrimTim sp. z o.o. 2019</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<%--Button activated on click send to this form--%>
<form method="post" action="/LogOutUser" id="logOutForm"><input type="text" hidden="hidden" name="name"/></form>
<form method="post" id="deleteForm"><input type="text" hidden="hidden" name="name"/></form>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="logoutModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="logoutModalLabel">Już nas opuszczasz?</h5>
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
            <div class="modal-body" id="modal-delete-body">Czy aby napewno chcesz usunąć użytkownika <label id="delete-name"></label> <label
                    id="delete-surname"></label>?
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Anuluj</button>
                <button class="btn btn-danger" type="submit" form="deleteForm">USUŃ</button>
            </div>
        </div>
    </div>
</div>

<!-- Edit Button Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Tryb edycji</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editForm" action="updateUser.jsp" method="get">
                    <div class="form-group">
                        <label for="user-name" class="col-form-label">Imię:</label>
                        <input type="text" name="user-name" required="required" class="form-control" id="user-name">
                    </div>
                    <div class="form-group">
                        <label for="user-surname" class="col-form-label">Nazwisko:</label>
                        <input type="text" name="user-surname" required="required" class="form-control" id="user-surname">
                    </div>
                    <div class="form-group">
                        <label for="user-email" class="col-form-label">E-mail:</label>
                        <input type="email" name="user-email" required="required" class="form-control" id="user-email">
                        <input type="email" hidden="hidden" name="user-oldEmail" required="required" class="form-control" id="user-oldEmail">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj</button>
                <button type="submit" form="editForm" class="btn btn-primary">Zapisz</button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/datatables-demo.js"></script>

<%--My script for modal--%>
<script src="js/modalPassValues.js"></script>

</body>

</html>

