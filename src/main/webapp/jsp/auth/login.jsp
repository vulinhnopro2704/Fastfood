<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/auth.css" />">
    <script src="https://kit.fontawesome.com/ee8f6ead9c.js" crossorigin="anonymous"></script>
    <title>Đăng nhập</title>
</head>

<body>
    <div class="container">
        <h1 class="title">Đăng nhập</h1>
        <img src="<c:url value="/assets/images/pasta.png" />" alt="Noodle">
        <form action='<c:url value="/auth/login" />' method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập</label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Đăng nhập</button>
            <div>Chưa có tài khoản? <a href="<c:url value="/auth/signup"/>">Đăng ký</a></div>
            <div>Thăm quan trang chủ với tài khoản khách? <a href="<c:url value="/"/>">Trang chủ</a></div>
        </form>
    </div>
</body>

</html>