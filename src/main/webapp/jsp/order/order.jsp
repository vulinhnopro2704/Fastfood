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
        <jsp:include page="/jsp/components/side-bar.jsp" />
        <div class="order-container">
            <!-- Order Item -->
            <div class="order-item">
                <div class="order-header">
                    <span class="order-date">02/08/2004</span>
                    <span class="order-status">Giao hàng tận nơi   <span class="status-delivered">Đã giao</span></span>
                </div>
                <hr class="divider">
                <div class="order-list">
                    <div class="order-list-item">
                        <div class="order-details">
                            <img src="https://via.placeholder.com/70" alt="Noodles" class="order-image">
                            <div>
                                <h3 class="item-title">Spicy seasoned seafood noodles</h3>
                                <p class="item-qty-price">2 x 129.000 đ</p>
                            </div>
                        </div>
                        <p class="order-message">This is the message</p>
                    </div>

                    <div class="order-list-item">
                        <div class="order-details">
                            <img src="https://via.placeholder.com/70" alt="Noodles" class="order-image">
                            <div>
                                <h3 class="item-title">Spicy seasoned seafood noodles</h3>
                                <p class="item-qty-price">2 x 129.000 đ</p>
                            </div>
                        </div>
                        <p class="order-message">This is the message</p>
                    </div>

                    <div class="order-list-item">
                        <div class="order-details">
                            <img src="https://via.placeholder.com/70" alt="Noodles" class="order-image">
                            <div>
                                <h3 class="item-title">Spicy seasoned seafood noodles</h3>
                                <p class="item-qty-price">2 x 129.000 đ</p>
                            </div>
                        </div>
                        <p class="order-message">This is the message</p>
                    </div>
                </div>
                <hr class="divider">
                <div class="order-total">
                    <span class="total-text">Tổng cộng:</span>
                    <span class="total-price">396.000 đ</span>
                </div>
            </div>

            <div class="order-item">
                <div class="order-header">
                    <span class="order-date">02/08/2004</span>
                    <span class="order-status">Giao hàng tận nơi   <span class="status-delivered">Đã giao</span></span>
                </div>
                <hr class="divider">
                <div class="order-list">
                    <div class="order-list-item">
                        <div class="order-details">
                            <img src="https://via.placeholder.com/70" alt="Noodles" class="order-image">
                            <div>
                                <h3 class="item-title">Spicy seasoned seafood noodles</h3>
                                <p class="item-qty-price">2 x 129.000 đ</p>
                            </div>
                        </div>
                        <p class="order-message">This is the message</p>
                    </div>

                    <div class="order-list-item">
                        <div class="order-details">
                            <img src="https://via.placeholder.com/70" alt="Noodles" class="order-image">
                            <div>
                                <h3 class="item-title">Spicy seasoned seafood noodles</h3>
                                <p class="item-qty-price">2 x 129.000 đ</p>
                            </div>
                        </div>
                        <p class="order-message">This is the message</p>
                    </div>

                    <div class="order-list-item">
                        <div class="order-details">
                            <img src="https://via.placeholder.com/70" alt="Noodles" class="order-image">
                            <div>
                                <h3 class="item-title">Spicy seasoned seafood noodles</h3>
                                <p class="item-qty-price">2 x 129.000 đ</p>
                            </div>
                        </div>
                        <p class="order-message">This is the message</p>
                    </div>
                </div>
                <hr class="divider">
                <div class="order-total">
                    <span class="total-text">Tổng cộng:</span>
                    <span class="total-price">396.000 đ</span>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/component/footer.jsp"/>
</body>

</html>