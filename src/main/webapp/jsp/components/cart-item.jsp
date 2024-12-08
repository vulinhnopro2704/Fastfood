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
            <input type="number" value="${param.itemQty}" min="1">
        </div>
        <div class="cart-item-total">
            ${param.itemTotal}
        </div>
    </div>
    <div class="cart-item-bottom">
        <input type="text" placeholder="Ghi chú">
        <button class="btn">${param.buttonText}</button>
    </div>
</div>