document.addEventListener("DOMContentLoaded", () => {
    const cartItems = document.querySelectorAll(".cart-item");
    const orderTotals = document.querySelectorAll(".order-total");
    const numberOfFood = document.getElementById("number-of-food");

    function updateNumberOfFood() {
        let total = 0;
        cartItems.forEach(item => {
            const quantity = parseInt(item.querySelector(".quantity").innerText);
            total += quantity;
        });
        numberOfFood.innerText = total
            + " món";
    }

    function updateOrderTotal() {
        let total = 0;
        cartItems.forEach(item => {
            const quantity = parseInt(item.querySelector(".quantity").innerText);
            const price = 40000; // Base price
            total += quantity * price;
            item.querySelector(".item-total span").innerText = `${price * quantity} đ`;
        });
        orderTotals.forEach((orderTotal) => {
            orderTotal.innerText = `${total} đ`;
        });

        // updateNumberOfFood();
    }

    cartItems.forEach(item => {
        const incrementBtn = item.querySelector(".increment");
        const decrementBtn = item.querySelector(".decrement");
        const quantityEl = item.querySelector(".quantity");

        incrementBtn.addEventListener("click", () => {
            let quantity = parseInt(quantityEl.innerText);
            quantityEl.innerText = quantity + 1;
            // updateOrderTotal();
        });

        decrementBtn.addEventListener("click", () => {
            let quantity = parseInt(quantityEl.innerText);
            if (quantity > 1) {
                quantityEl.innerText = quantity - 1;
                // updateOrderTotal();
            }
        });
    });

    // updateOrderTotal();
    // updateNumberOfFood();
});
