<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${not empty sessionScope.fullname}">
    <div class="sidebar">
        <div class="sidebar-item">
            <a href="<c:url value='/' />" class="sidebar-link <c:out value='${param.currentPage == "home" ? "active" : ""}' />">
                <i class="fa-solid fa-house"></i>
                <span>Trang chủ</span>
            </a>
        </div>
        <div class="sidebar-item">
            <a href="<c:url value='/order' />" class="sidebar-link <c:out value='${param.currentPage == "order" ? "active" : ""}' />">
                <i class="fa-regular fa-rectangle-list"></i>
                <span>Đơn hàng</span>
            </a>
        </div>
        <div class="sidebar-item">
            <a href="<c:url value='/settings' />" class="sidebar-link <c:out value='${param.currentPage == "settings" ? "active" : ""}' />">
                <i class="fa-solid fa-gear"></i>
                <span>Settings</span>
            </a>
        </div>
        <div class="sidebar-item">
            <a href="<c:url value='/auth/logout' />" class="sidebar-link">
                <i class="fa-solid fa-arrow-right-from-bracket"></i>
                <span>Logout</span>
            </a>
        </div>
    </div>
</c:if>

<c:if test="${empty sessionScope.user}">
    <p></p>
</c:if>