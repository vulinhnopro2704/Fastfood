<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/shared/sidebar.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/checkout.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/shared/select-custom.css' />">
</head>

<body>
    <a id="back-button" href="<c:url value="/"/>">
        << Quay lại trang chủ
    </a>
    <div class="container">
        <h1>Giỏ hàng của bạn</h1>
        <div class="cart-container">
            <!-- Cart Items -->
            <div class="cart-items">
                <!-- Item 1 -->
                <div class="cart-item-wrap">
                    <div class="cart-item">
                        <img src="<c:url value="/assets/default/food.jpeg" />" alt="Hamburger" class="item-image">
                        <div class="item-details">
                            <h3>Hamburger</h3>
                            <div class="quantity-control">
                                <button class="btn decrement">-</button>
                                <span class="quantity">2</span>
                                <button class="btn increment">+</button>
                            </div>
                            <p class="item-price">40.000 đ</p>
                        </div>
                        <div class="item-total">
                            <span></span>
                            <button class="btn remove">X</button>
                        </div>
                    </div>
                    <div>
                        <input type="text" placeholder="Ghi chú" name="message" value="Đây là message đi kèm">
                    </div>
                </div>
                <!-- Repeat for other items -->
                <div class="cart-item-wrap">
                    <div class="cart-item">
                        <img src="<c:url value="/assets/default/food.jpeg" />" alt="Hamburger" class="item-image">
                        <div class="item-details">
                            <h3>Hamburger</h3>
                            <div class="quantity-control">
                                <button class="btn decrement">-</button>
                                <span class="quantity">2</span>
                                <button class="btn increment">+</button>
                            </div>
                            <p class="item-price">40.000 đ</p>
                        </div>
                        <div class="item-total">
                            <span></span>
                            <button class="btn remove">X</button>
                        </div>
                    </div>
                    <div>
                        <input type="text" placeholder="Ghi chú" name="message" value="Đây là message đi kèm">
                    </div>
                </div>
            </div>

            <!-- Order Summary -->
            <div class="order-summary">
                <div class="order-summary-info">
                    <h2>Tóm tắt đơn hàng</h2>
                    <div class="devider"></div>
                    <div class="order-summary-note">
                        <p>*Thanh toán sẽ <strong>không thực hiện qua website</strong></p>
                        <p>*Đối với <strong>giao hàng tận nơi</strong> thì bạn sẽ thanh toán cho Shipper</p>
                        <p>*Chi tiết liên hệ số điện thoại: <strong>09xx xxx xxx</strong></p>
                        <p>*Sau khi đặt hàng sẽ có nhân viên liên hệ lại với bạn để xác nhận.</p>
                    </div>
                </div>
                <jsp:include page="/jsp/components/select-custom.jsp">
                    <jsp:param name="selectedOption" value="Giao hàng tận nơi" />
                    <jsp:param name="items" value="Giao hàng tận nơi,Tại quán,Mang về" />
                </jsp:include>
                <div class="devider"></div>
                <ul class="order-summary-list">
                    <li>Tổng cộng: <span id="number-of-food"></span></li>
                    <li>Tổng đơn hàng: <span class="order-total"></span></li>
                    <li>Phí giao hàng: <span>0 đ</span></li>
                </ul>
                <div class="devider"></div>
                <button class="btn order-btn">Đặt hàng <span class="order-total"></span></button>
            </div>
        </div>
    </div>
    <script src="<c:url value="/js/checkout.js" />"></script>
    <script defer src="<c:url value="/js/select-custom.js" />"></script>
</body>

</html>