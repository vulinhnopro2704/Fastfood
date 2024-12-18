<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order List</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/shared/sidebar.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/order.css' />">
    <script src="https://kit.fontawesome.com/ee8f6ead9c.js" crossorigin="anonymous"></script>
</head>

<body>
    <div class="container">
        <jsp:include page="/jsp/components/side-bar.jsp">
            <jsp:param name="currentPage" value="order"/>
        </jsp:include>
        <div class="order-container">
            <!-- Order Item -->
            <c:forEach var="order" items="${orders}">
                <div class="order-item">
                    <div class="order-header">
                        <span class="order-date"><c:out value="${order.orderDate}" /></span>
                        <span class="order-status">
                            <c:out value="${order.type}" />
                            <span class="status-delivered">
                            <c:choose>
                                <c:when test="${sessionScope.role.equalsIgnoreCase('ADMIN')}">
                                    <select name="status" class="status-select" data-order-id="${order.id}">
                                        <c:forEach var="entry" items="${statusValues}">
                                            <option value="${entry.key}" <c:if test="${entry.value == order.status}">selected</c:if>>${entry.value}</option>
                                        </c:forEach>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${order.status}" /> <c:out value="${sessionScope.role}" />
                                </c:otherwise>
                            </c:choose>
                        </span>
                        </span>
                    </div>
                    <hr class="divider">
                    <div class="order-list">
                        <c:forEach var="foodOrder" items="${listFoodOrderDetails}">
                            <c:if test="${foodOrder.orderId == order.id}">
                                <div class="order-list-item">
                                    <div class="order-details">

                                        <img src="<c:url value="${foodOrder.imageLink}"/>" alt="Noodles" class="order-image">
                                        <div>
                                            <h3 class="item-title"><c:out value="${foodOrder.name}" /></h3>
                                            <p class="item-qty-price"><c:out value="${foodOrder.quantity}" />x<c:out value="${foodOrder.price}" /></p>
                                        </div>
                                    </div>
                                    <p class="order-message"><c:out value="${foodOrder.message}" /></p>
                                </div>
                            </c:if>
                        </c:forEach>


                    </div>
                    <hr class="divider">
                    <div class="order-total">
                        <span class="total-text">Tổng cộng:</span>
                        <span class="total-price"><c:out value="${order.totalAmount}" /></span>
                    </div>
                </div>
            </c:forEach>


        </div>
    </div>
    <jsp:include page="/component/footer.jsp"/>
</body>

</html>