<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="<c:url value="/hello-servlet" />">Hello Servlet</a>
<a href="<c:url value="/auth"/>">Auth</a>
<a href="<c:url value="/auth/login"/>">Login</a>
<a href="<c:url value="/auth/logout"/>">Logout</a>
<a href="<c:url value="/vacxin"/>">Vacxin</a>
</body>
</html>