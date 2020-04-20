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
    int id = Integer.parseInt(request.getParameter("id"));
    DatabaseManager databaseManager = new DatabaseManager();
    try {
        IDBO<User> userIDBO = new UserDBO(databaseManager.dataSource.getConnection());
        userIDBO.delete(id);
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        response.sendRedirect("adminpage.jsp");
    }
%>