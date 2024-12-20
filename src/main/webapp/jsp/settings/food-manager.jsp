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
        <jsp:include page="/jsp/components/side-bar.jsp">
            <jsp:param name="currentPage" value="settings"/>
        </jsp:include>
        <div class="sidebar-food-manager">
            <ul>
                <li><a href="#" class="active">Quản lý món ăn</a></li>
                <li><a href="#">Security</a></li>
            </ul>
        </div>
        <div class="content">
            <h2>Quản lý món ăn</h2>
            <div class="menu-grid">
                <div class="add-new">+ Add new dish</div>

                <c:forEach var="food" items="${listFoods}">
                    <div class="menu-item" data-food-id="${food.id}">
                        <i class="fa-solid fa-x menu-item-exit"></i>
                        <img src="<c:url value="${food.imageLink}" />" alt="${food.description}">
                        <h3><c:out value="${food.name}" /></h3>
                        <p><c:out value="${food.price}" /></p>
                        <button>Sửa món ăn</button>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <script src="<c:url value="/js/show-add-popup.js" />"></script>
    <script>
        document.addEventListener('DOMContentLoaded', () => {
            document.querySelectorAll('.menu-item button').forEach(button => {
                button.addEventListener('click', (event) => {
                    const foodId = event.target.closest('.menu-item').getAttribute('data-food-id');
                    console.log('foodId:', foodId);
                    fetch(`/final_exam_war_exploded/food?id=` + foodId)
                        .then(response => response.json())
                        .then(data => {
                            showAddFood({
                                ...data,
                                imageLink: '/final_exam_war_exploded/' + data.imageLink
                            });
                        })
                        .catch(error => console.error('Error fetching food details:', error));
                });
            });

            const addNew = document.querySelector('.add-new');
            addNew.addEventListener('click', () => {
                showAddFood();
            });
        });
    </script>
    <jsp:include page="/component/footer.jsp" />
</body>

</html>