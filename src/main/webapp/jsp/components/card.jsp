<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="card">
    <div class="card-image">
        <img src="${param.imageSrc}" alt="${param.altText}">
    </div>
    <div class="card-content">
        <h3>${param.title}</h3>
        <p class="price">${param.price}</p>
        <p class="description">${param.description}</p>
        <input type="hidden" name="idFood" value="${param.idFood}" disabled >
    </div>

    <div class="card-action">
        <button onclick="addFoodToCart(${param.idFood})">${param.buttonText}</button>
    </div>
</div>

