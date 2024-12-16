<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="card">
    <div class="card-image">
        <img src="${param.imageSrc}" alt="${param.altText}">
    </div>
    <div class="card-content">
        <h3>${param.title}</h3>
        <p class="price">${param.price}</p>
        <p class="description">${param.description}</p>

    </div>

    <div class="card-action">
        <form action="/final_exam_war_exploded/OrderDetail/addFood" method="post">
            <input type="hidden" name="idFood" value="${param.idFood}" >
            <button type="submit" >${param.buttonText}</button>
        </form>

    </div>
</div>

