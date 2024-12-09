<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Management</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/food-manager.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/shared/sidebar.css" />">
    <script src="https://kit.fontawesome.com/ee8f6ead9c.js" crossorigin="anonymous"></script>
</head>

<body>
    <div class="settings">
        <jsp:include page="/jsp/components/side-bar.jsp" />
        <div class="sidebar">
            <ul>
                <li><a href="#" class="active">Quản lý món ăn</a></li>
                <li><a href="#">Security</a></li>
            </ul>
        </div>
        <div class="content">
            <h2>Quản lý món ăn</h2>
            <div class="menu-grid">
                <div class="add-new">+ Add new dish</div>
                <div class="menu-item">
                    <i class="fa-solid fa-x menu-item-exit"></i>
                    <img src="<c:url value="/assets/images/pasta.png" />" alt="Spicy seasoned seafood noodles">
                    <h3>Spicy seasoned seafood noodles</h3>
                    <p>230.000 đ</p>
                    <button>Sửa món ăn</button>
                </div>

            </div>
        </div>
    </div>
    <script src="<c:url value="/js/show-add-popup.js" />"></script>
    <script>
        const addNew = document.querySelector('.add-new');
        addNew.addEventListener('click', () => {
            showAddFood();
        });
    </script>
    <jsp:include page="/component/footer.jsp" />
</body>

</html>