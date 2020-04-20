<%@ page import="vs.api.database.DatabaseManager" %>
<%@ page import="vs.api.repository.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="vs.api.database.IDBO" %>
<%@ page import="vs.api.database.UserDBO" %>
<%--
  Created by IntelliJ IDEA.
  User: Damian
  Date: 09.12.2019
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = request.getParameter("user-name");
    String surname = request.getParameter("user-surname");
    String email = request.getParameter("user-email");
    String oldEmail = request.getParameter("user-oldEmail");

    System.out.println(name);
    System.out.println(surname);
    System.out.println(email);
    DatabaseManager databaseManager = new DatabaseManager();
    try {
        IDBO<User> userIDBO = new UserDBO(databaseManager.dataSource.getConnection());
        userIDBO.edit(name, surname, email, oldEmail);
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        response.sendRedirect("adminpage.jsp");
    }
%>