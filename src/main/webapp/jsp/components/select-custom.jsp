<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="dropdown">
    <button class="dropdown-toggle">
        <span class="selected-option">${param.selectedOption}</span>
        <span class="arrow">&#9662;</span>
    </button>
    <ul class="dropdown-menu">
        <c:forEach var="item" items="${param.items}">
            <li class="dropdown-item ${item == param.selectedOption ? 'active' : ''}" data-value="${item}" onclick="handlClick(this)">
                <span class="dot"></span> ${item}
            </li>
        </c:forEach>
    </ul>
</div>

