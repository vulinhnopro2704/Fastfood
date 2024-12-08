<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 12/7/2024
  Time: 9:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList, org.exam.final_exam.entity.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
    <h2>test session : </h2>
    <h3>email : <%= email%></h3>
    <h3>role : <%= role%></h3>
    <h3>id : <%= id%></h3>

    <h1>list user</h1>
   <table border="1" width="80%">
    <c:forEach var="user" items="${users}">
        <tr>
            <td><input type="checkbox" class="rowCheckbox" value="${user.id}"></td>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.fullName}" /></td>
            <td><c:out value="${user.email}" /></td>
<%--&lt;%&ndash;            <td><c:out value="${user.password}" /></td>&ndash;%&gt;--%>
            <td><c:out value="${user.phoneNumber}" /></td>
            <td><c:out value="${user.address}" /></td>
            <td><c:out value="${user.role}" /></td>
<%--            1--%>
        </tr>
    </c:forEach>
   </table>


</body>
</html>
