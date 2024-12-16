<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="cart-item">
    <div class="cart-item-top">
        <div class="cart-item-top-left">
            <img src="${param.imageSrc}" alt="${param.altText}">
            <div class="cart-item-details">
                <div class="cart-item-name">${param.itemName}</div>
                <div class="cart-item-price">${param.itemPrice}</div>
            </div>
        </div>

        <div class="cart-item-qty">
            <input type="hidden" name="idOrderdt" value="${param.idOrderDetail}" form="${param.idForm}"  >
            <input type="number" name="quantity" value="${param.itemQty}" min="1"  form="${param.idForm}">
        </div>
        <div class="cart-item-total">
            ${param.itemTotal}
        </div>



    </div>
    <div class="cart-item-bottom">

        <input type="hidden" name="idOrderdt" value="${param.idOrderDetail}" form="${param.idForm}"  >
        <input type="text" placeholder="Ghi chú" name="message" value="${param.message}" form="${param.idForm}">

<%--        <a href="/final_exam_war_exploded/TrangChu/OrderDetail/deleteFood?id=${param.idOrderDetail}" > <button class="btn">${param.buttonText}</button></a>--%>
<%--        <button class="btn">${param.buttonText}</button>--%>

        <form action="/final_exam_war_exploded/OrderDetail/deleteFood" method="post">
            <input type="hidden" name="idOrderDetail" value="${param.idOrderDetail}" >
            <button class="btn" type="submit" >${param.buttonText}</button>
        </form>

    </div>
</div>