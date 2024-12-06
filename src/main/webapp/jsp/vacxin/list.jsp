<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VacXin List</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/footer.css" />">
</head>

<body>
<jsp:include page="/component/header.jsp" />
<div class="content">
    <div class="search_bar">
        <form action="<c:url value='/vacxin/search' />" method="post">
            <div>
                <div style="margin-right: 20px;">
                    <input type="radio" id="MaVacXin" name="filter" value="MaVacXin"
                           <c:if test="${param.filter == 'MaVacXin'}">checked</c:if>>
                    <label for="MaVacXin">Ma Vac Xin</label>
                </div>
                <div>
                    <input type="radio" id="TenVacXin" name="filter" value="TenVacXin"
                           <c:if test="${param.filter == 'TenVacXin'}">checked</c:if>>
                    <label for="TenVacXin">Ten Vac Xin</label>
                </div>
            </div>
            <div>
                <input name="search" type="text" id="search" placeholder="Search VacXin">
                <button type="submit">Search</button>
            </div>
        </form>
    </div>

    <h1 style="text-align: center; margin: 40px">VacXin List</h1>
    <table>
        <thead>
        <tr>
            <th><input type="checkbox" id="selectAll" onclick="toggleSelectAll(this)"></th>
            <th>MaVacXin</th>
            <th>TenVacXin</th>
            <th>SoMui</th>
            <th>MoTa</th>
            <th>GiaVacXin</th>
            <th>TenHangSX</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vacxin" items="${vacXins}">
            <tr>
                <td><input type="checkbox" class="rowCheckbox" value="${vacxin.maVacXin}"></td>
                <td><c:out value="${vacxin.maVacXin}" /></td>
                <td><c:out value="${vacxin.tenVacXin}" /></td>
                <td><c:out value="${vacxin.soMui}" /></td>
                <td><c:out value="${vacxin.moTa}" /></td>
                <td><c:out value="${vacxin.giaVacXin}" /></td>
                <td><c:out value="${vacxin.tenHangSX}" /></td>
                <td>
                    <svg class="icon" width="20" height="20" viewBox="0 0 80 88" fill="none"
                         xmlns="http://www.w3.org/2000/svg"
                         onclick="updateVacxin('${vacxin.maVacXin}')">
                        <path class="icon"
                              d="M71.1625 4.11632C69.8971 2.85027 68.3947 1.84603 66.741 1.16101C65.0873 0.475982 63.3148 0.123606 61.5248 0.124024C59.7349 0.124442 57.9625 0.477645 56.3091 1.16344C54.6558 1.84924 53.1538 2.85418 51.889 4.12082L9.1075 46.9608C6.5584 49.5158 5.12627 52.9772 5.125 56.5863V70.9998C5.125 72.8628 6.637 74.3748 8.5 74.3748H23.0035C26.617 74.3748 30.082 72.9348 32.6335 70.3833L75.3835 27.6063C77.9336 25.0502 79.3657 21.587 79.3657 17.9763C79.3657 14.3657 77.9336 10.9024 75.3835 8.34632L71.1625 4.11632ZM4 81.1248C3.10489 81.1248 2.24645 81.4804 1.61351 82.1133C0.980579 82.7463 0.625 83.6047 0.625 84.4998C0.625 85.3949 0.980579 86.2534 1.61351 86.8863C2.24645 87.5192 3.10489 87.8748 4 87.8748H76C76.8951 87.8748 77.7536 87.5192 78.3865 86.8863C79.0194 86.2534 79.375 85.3949 79.375 84.4998C79.375 83.6047 79.0194 82.7463 78.3865 82.1133C77.7536 81.4804 76.8951 81.1248 76 81.1248H4Z"></path>
                    </svg>
                </td>
                <td>
                    <svg class="icon" width="20" height="20" viewBox="0 0 14 14" fill="none"
                         xmlns="http://www.w3.org/2000/svg"
                         onclick="deleteVacxin('${vacxin.maVacXin}')">
                        <path class="icon"
                              d="M11.1483 11.842C11.1131 12.3672 10.6769 12.7752 10.1506 12.7752H3.84163C3.31534 12.7752 2.87912 12.3672 2.84387 11.8421L2.27734 3.40063H11.7142L11.1483 11.842Z"
                              stroke-linecap="round" stroke-linejoin="round"></path>
                        <path class="icon" d="M1 3.40161H13" stroke-width="1.2" stroke-linecap="round"
                              stroke-linejoin="round"></path>
                        <path class="icon"
                              d="M8.52789 1.22485H5.46852C5.19806 1.22485 4.93867 1.33946 4.74742 1.54346C4.55617 1.74745 4.44873 2.02413 4.44873 2.31263V3.40041H9.54768V2.31263C9.54768 2.02413 9.44024 1.74745 9.24899 1.54346C9.05775 1.33946 8.79836 1.22485 8.52789 1.22485Z"
                              stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                    </svg>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button class="button" onclick="deleteSelected()">Delete Selected</button>
</div>

<script>
    function updateVacxin(maVacXin) {
        callController('/vacxin/update', `id=${maVacXin}`, () => {}, 'GET');
    }

    function deleteVacxin(maVacXin) {
        if (confirm('Are you sure you want to delete this VacXin?')) {
            callController('/vacxin/delete', `MaVacXin=${maVacXin}`, () => {
                window.location.reload();
            }, 'POST');
        }
    }

    function toggleSelectAll(source) {
        const checkboxes = document.querySelectorAll('.rowCheckbox');
        checkboxes.forEach(checkbox => checkbox.checked = source.checked);
    }

    function deleteSelected() {
        const selected = document.querySelectorAll('.rowCheckbox:checked');
        const ids = Array.from(selected).map(checkbox => checkbox.value);
        if (ids.length > 0 && confirm('Are you sure you want to delete the selected VacXins?')) {
            callController('/vacxin/delete_selected', `ids=${ids.join(',')}`, () => {
                window.location.reload();
            }, 'POST');
        }
    }
</script>
<jsp:include page="/component/footer.jsp" />
</body>

</html>