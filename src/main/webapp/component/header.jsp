<%--
  Created by IntelliJ IDEA.
  User: vulin
  Date: 12/5/2024
  Time: 6:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if (session == null || session.isNew()) {
        session = request.getSession();
    }
%>
<header>
    <nav>
        <ul>
            <li>
                <a href="<c:url value='/vacxin' />">
                    <button type="button">Danh sách Vacxin</button>
                </a>
            </li>
            <li>
                <a href="<c:url value='/vacxin/add' />">
                    <button type="button">Thêm Vacxin</button>
                </a>
            </li>
            <li>
                <a href="<c:url value='/benhnhan/read' />">
                    <button type="button">Bệnh nhân</button>
                </a>
            </li>
        </ul>
    </nav>
    <div style="display: flex; gap: 15px; align-items: center;">
        <h2>Welcome, <c:out value="${sessionScope.username != null ? sessionScope.username : ''}" /></h2>
        <c:choose>
            <c:when test="${sessionScope.username != null && !sessionScope.username.trim().isEmpty()}">
                <form action="<c:url value='/logout' />" method="post" style="display:inline;">
                    <button type="submit">Logout</button>
                </form>
            </c:when>
            <c:otherwise>
                <form action="<c:url value='/login' />" method="post" style="display:inline;">
                    <button type="submit">Login</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</header>