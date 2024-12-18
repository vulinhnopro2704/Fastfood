<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/shared/sidebar.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/homepage.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/shared/cart.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/shared/card.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/shared/select-custom.css' />">
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
            <form action="/final_exam_war_exploded/timKiem" method="post">
            <div class="search_container">

                <input type="text" placeholder="Tìm kiếm..." name="search" >
                <button><i class="fa-solid fa-magnifying-glass"></i></button>
            </div>
            </form>
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
                <c:forEach var="food" items="${listFoods}">
                    <c:set var="imageLink" value="${food.imageLink}" />

                    <!-- Sử dụng c:url bên ngoài biểu thức để tạo URL chính xác -->
                    <c:url var="imageUrl" value="${imageLink}" />

                    <!-- Kiểm tra nếu imageLink không rỗng và có phần mở rộng đúng -->
                    <c:if test="${not empty imageLink and (fn:endsWith(imageLink, '.jpg') or fn:endsWith(imageLink, '.jpeg') or fn:endsWith(imageLink, '.png') or fn:endsWith(imageLink, '.gif'))}">
                        <c:set var="imageUrl" value="${imageUrl}" />
                    </c:if>
                    <c:if test="${empty imageLink or not (fn:endsWith(imageLink, '.jpg') or fn:endsWith(imageLink, '.jpeg') or fn:endsWith(imageLink, '.png') or fn:endsWith(imageLink, '.gif'))}">
                        <!-- Gán giá trị mặc định cho imageUrl -->
                        <c:set var="imageUrl" value="<c:url value='/asset/default/food.jpeg' />" />
                    </c:if>

                    <jsp:include page="/jsp/components/card.jsp">
                        <jsp:param name="imageSrc" value="${imageUrl}" />
                        <jsp:param name="altText" value="Healthy Noodles" />
                        <jsp:param name="title" value="${food.name}" />
                        <jsp:param name="price" value="${food.price}" />
                        <jsp:param name="description" value="${food.description}" />
                        <jsp:param name="idFood" value="${food.id}" />
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
    <div class="cart-type">
        <button class="btn cart-type-button" onclick="selectOption(this)" value="DINEIN">
            Tại quán
        </button>
        <button class="btn cart-type-button" onclick="selectOption(this)" value="TAKEAWAY">
            Mang về
        </button>
        <button class="btn cart-type-button" onclick="selectOption(this)" value="SHIP">
            Ship
        </button>
    </div>

    <div class="cart-items">
        <c:forEach var="foodorderDetail" items="${listFoodOrderDetails}">
            <c:set var="imageLink" value="${foodorderDetail.imageLink}" />

            <!-- Tạo URL cho hình ảnh -->
            <c:url var="imageUrl" value="${imageLink}" />

            <!-- Kiểm tra nếu imageLink không rỗng và có phần mở rộng đúng -->
            <c:if test="${not empty imageLink and (fn:endsWith(imageLink, '.jpg') or fn:endsWith(imageLink, '.jpeg') or fn:endsWith(imageLink, '.png') or fn:endsWith(imageLink, '.gif'))}">
                <c:set var="imageUrl" value="${imageUrl}" />
            </c:if>
            <c:if test="${empty imageLink or not (fn:endsWith(imageLink, '.jpg') or fn:endsWith(imageLink, '.jpeg') or fn:endsWith(imageLink, '.png') or fn:endsWith(imageLink, '.gif'))}">
                <!-- Gán giá trị mặc định cho imageUrl -->
                <c:set var="imageUrl" value="<c:url value='/assets/images/chicken.jpeg' />" />
            </c:if>

            <jsp:include page="/jsp/components/cart-item.jsp">
                <jsp:param name="imageSrc" value="${imageUrl}" />
                <jsp:param name="altText" value="Product" />
                <jsp:param name="itemName" value="${foodorderDetail.name}" />
                <jsp:param name="itemPrice" value="${foodorderDetail.price}" />
                <jsp:param name="itemQty" value="${foodorderDetail.quantity}" />
                <jsp:param name="itemTotal" value="${foodorderDetail.subtotal}" />
                <jsp:param name="message" value="${foodorderDetail.message}" />
                <jsp:param name="idOrderDetail" value="${foodorderDetail.orderDetailId}" />
                <jsp:param name="idForm" value="orderForm" />
                <jsp:param name="buttonText" value="Xóa" />
            </jsp:include>
        </c:forEach>
    </div>

    <form id="orderForm" action="<c:url value="/checkout" />" method="post" enctype="multipart/form-data">
        <input type="text" hidden="hidden" value="TAKEAWAY" name="typeOrder" id="typeOrder">
        <div class="cart-footer">
            <button class="btn" type="submit" >Order</button>
        </div>
    </form>
</div>

<script>
    const cartToggle = document.querySelector('.cart-toggle');
    const cart = document.querySelector('#cart');
    const typeOrder = document.querySelector('#typeOrder');
    cartToggle.addEventListener('click', () => {
        cart.classList.toggle('active');
    });

    function addFoodToCart(idFood) {
        console.log("addFood ", idFood)
        callController('<c:url value="/OrderDetail/addFood" />', idFood, () => {
            window.location.reload();
        }, 'GET');
    }

    function selectOption(button) {
        // Remove 'selected' class from all buttons
        const buttons = document.querySelectorAll('.cart-type .btn');
        buttons.forEach(btn => btn.classList.remove('selected'));

        // Add 'selected' class to the clicked button
        button.classList.add('selected');

        document.getElementById('typeOrder').value = button.value;
    }
</script>
<script defer src="<c:url value="/js/select-custom.js" />"></script>

<jsp:include page="/component/footer.jsp"/>
</body>

</html>
