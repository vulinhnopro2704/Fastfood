<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${not empty requestScope.vacxin}">
    <jsp:useBean id="vacxin" scope="request" type="org.exam.final_exam.entity.VacXin" />
</c:if>
<c:if test="${not empty requestScope.action}">
    <jsp:useBean id="action" scope="request" type="java.lang.String" />
</c:if>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${action}" /> VacXin</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/footer.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/form.css" />">
</head>

<body>
<jsp:include page="/component/header.jsp" />
<main class="container">
    <div class="form-card">
        <h1 class="form-header"><c:out value="${action}" /> VacXin</h1>
        <form action="<c:url value='/vacxin/${fn:toLowerCase(action)}' />" method="post">
            <div class="form-group">
                <input name="maVacXin" value="${vacxin != null ? vacxin.maVacXin : ''}" ${vacxin != null ? 'readonly' : ''} />
            </div>
            <div class="form-group">
                <label for="tenVacXin">Tên VacXin:</label>
                <input type="text" id="tenVacXin" name="tenVacXin" value="${vacxin != null ? vacxin.tenVacXin : ''}" required>
            </div>
            <div class="form-group">
                <label for="soMui">Số Mũi:</label>
                <input type="number" id="soMui" name="soMui" value="${vacxin != null ? vacxin.soMui : ''}" required>
            </div>
            <div class="form-group">
                <label for="moTa">Mô Tả:</label>
                <textarea id="moTa" name="moTa" required>${vacxin != null ? vacxin.moTa : ''}</textarea>
            </div>
            <div class="form-group">
                <label for="giaVacXin">Giá VacXin:</label>
                <input type="number" id="giaVacXin" name="giaVacXin" value="${vacxin != null ? vacxin.giaVacXin : ''}" required>
            </div>
            <div class="form-group">
                <label for="tenHangSX">Tên Hãng Sản Xuất:</label>
                <input type="text" id="tenHangSX" name="tenHangSX" value="${vacxin != null ? vacxin.tenHangSX : ''}" required>
            </div>
            <div class="form-group">
                <button style="background: #DC2B2B" type="reset">Reset</button>
                <button type="submit"><c:out value="${action}" /></button>
            </div>
        </form>
    </div>
</main>
<jsp:include page="/component/footer.jsp" />
</body>

</html>