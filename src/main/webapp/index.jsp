<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/homepage.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/shared/cart.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/shared/card.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/shared/select-custom.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/shared/sidebar.css" />">
    <script src="https://kit.fontawesome.com/ee8f6ead9c.js" crossorigin="anonymous"></script>
</head>

<body>
    <div class="container">
        <jsp:include page="/jsp/components/side-bar.jsp" />
        <!-- Main content -->
        <div class="main-content">
            <!-- Header of Home page -->
            <header class="header">
                <div>
                    <h1>Trang chủ</h1>
                    <h3>Thứ 2, 12/07/2024</h3>
                </div>

                <!-- Search bar -->
                <div class="search_container">
                    <input type="text" placeholder="Tìm kiếm...">
                    <button><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>

<%--                Cart--%>
                <div>
                    <button class="btn cart-toggle">
                        <i class="fa-solid fa-cart-shopping"></i>
                    </button>
                </div>
            </header>

            <!-- Body content -->
            <main class="body-content">
                <div class="filter">
                    <!-- Page title -->
                    <h2>Chọn món</h2>

                    <!-- Category -->
                    <jsp:include page="/jsp/components/select-custom.jsp">
                        <jsp:param name="selectedOption" value="Snack" />
                        <jsp:param name="items" value="Snack,Beverage,Noodles,Fried" />
                    </jsp:include>
                </div>

                <!-- List of cards -->
                <div class="card-container">
                    <c:forEach var="i" begin="1" end="5">
                        <c:url var="imageUrl" value="/assets/images/chicken.jpeg" />
                        <jsp:include page="/jsp/components/card.jsp">
                            <jsp:param name="imageSrc" value="${imageUrl}" />
                            <jsp:param name="altText" value="Healthy Noodles" />
                            <jsp:param name="title" value="Healthy noodle with spinach leaf" />
                            <jsp:param name="price" value="250.000d" />
                            <jsp:param name="description" value="3 miếng Gà + 1 Mì Ý Popcorn + 1 Khoai Tây Chiên (vừa) + 2 Pepsi lớn" />
                            <jsp:param name="buttonText" value="Thêm" />
                        </jsp:include>
                    </c:forEach>
                </div>
            </main>
        </div>
    </div>

    <!-- Cart -->
    <div class="cart-container" id="cart">
        <div class="cart-header">Giỏ hàng của bạn</div>
        <div class="cart-items">
            <c:forEach var="i" begin="1" end="3">
                <c:url var="imageUrl" value="/assets/images/chicken.jpeg" />
                <jsp:include page="/jsp/components/cart-item.jsp">
                    <jsp:param name="imageSrc" value="${imageUrl}" />
                    <jsp:param name="altText" value="Product" />
                    <jsp:param name="itemName" value="Spicy seasoned seafood noodles" />
                    <jsp:param name="itemPrice" value="230,000đ" />
                    <jsp:param name="itemQty" value="2" />
                    <jsp:param name="itemTotal" value="460,000đ" />
                    <jsp:param name="buttonText" value="Xóa" />
                </jsp:include>
            </c:forEach>
        </div>
        <div class="cart-footer">
            <button class="btn">Order</button>
        </div>
    </div>

    <script>
        const cartToggle = document.querySelector('.cart-toggle');
        const cart = document.querySelector('#cart');

        cartToggle.addEventListener('click', () => {
            cart.classList.toggle('active');
        });
    </script>
    <jsp:include page="/component/footer.jsp" />
</body>

</html>