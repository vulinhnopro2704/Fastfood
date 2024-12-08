<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 12/7/2024
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String email = (String) session.getAttribute("email");
        String role = (String) session.getAttribute("role");
        String id = (String) session.getAttribute("id");

    %>
    <h1> trang chu</h1>
    <h2>test session : </h2>
    <h3>email : <%= email%></h3>
    <h3>role : <%= role%></h3>
    <h3>id : <%= id%></h3>

    <a href="<c:url value="/user"/>">userlist</a>
</body>
</html>
