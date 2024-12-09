<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="sidebar">
    <div class="sidebar-item">
        <a href="#" class="sidebar-link active">
            <i class="fa-solid fa-house"></i>
            <span>Trang chủ</span>
        </a>
    </div>
    <div class="sidebar-item">
        <a href="#" class="sidebar-link">
            <i class="fa-regular fa-rectangle-list"></i>
            <span>Đơn hàng</span>
        </a>
    </div>
    <div class="sidebar-item">
        <a href="<c:url value="/settings" />" class="sidebar-link">
            <i class="fa-solid fa-gear"></i>
            <span>Settings</span>
        </a>
    </div>
    <div class="sidebar-item">
        <form action="<c:url value="/auth/logout" />" method="post">
            <a class="sidebar-link">
                <i class="fa-solid fa-arrow-right-from-bracket"></i>
                <span>Logout</span>
            </a>
        </form>
    </div>
</div>