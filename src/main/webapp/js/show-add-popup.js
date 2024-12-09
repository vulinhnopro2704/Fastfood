function showAddFood(id) {
    const style = `
        <style>
            .overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.7);
                display: flex;
                justify-content: center;
                align-items: center;
                z-index: 1000;
            }

            .add-dish-container {
                background-color: #25263b;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
                width: 400px;
            }

            .add-dish-container h2 {
                text-align: center;
                margin-bottom: 20px;
                color: #ff7f50;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-size: 0.9rem;
                color: #b3b3b3;
            }

            .form-group input,
            .form-group textarea,
            .form-group select {
                width: 100%;
                padding: 10px;
                border: none;
                border-radius: 5px;
                background-color: #1e1e2f;
                color: #ffffff;
                font-size: 1rem;
            }

            .form-group input[type="file"] {
                padding: 5px;
            }

            .form-group textarea {
                resize: none;
                height: 80px;
            }

            .form-actions {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }

            .form-actions button {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 1rem;
            }

            .form-actions .cancel {
                background-color: #ff4d4d;
                color: #ffffff;
            }

            .form-actions .cancel:hover {
                background-color: #e60000;
            }

            .form-actions .save {
                background-color: #4caf50;
                color: #ffffff;
            }

            .form-actions .save:hover {
                background-color: #388e3c;
            }

            .image-preview {
                width: 100%;
                height: 150px;
                background-color: #1e1e2f;
                border: 2px dashed #b3b3b3;
                border-radius: 5px;
                display: flex;
                justify-content: center;
                align-items: center;
                margin-bottom: 10px;
                overflow: hidden;
            }

            .image-preview img {
                max-width: 100%;
                max-height: 100%;
            }

            .hidden {
                display: none;
            }
        </style>
    `;

    const html = `
        <div class="overlay" id="overlay">
            <div class="add-dish-container">
                <h2>Thêm món ăn</h2>
                <form id="add-dish-form">
                    <div class="form-group">
                        <label for="dish-image">Ảnh món ăn</label>
                        <div class="image-preview" id="image-preview">
                            <span>Chọn ảnh</span>
                        </div>
                        <input type="file" id="dish-image" accept="image/*" class="hidden">
                    </div>

                    <div class="form-group">
                        <label for="dish-name">Tên món</label>
                        <input type="text" id="dish-name" placeholder="Nhập tên món">
                    </div>

                    <div class="form-group">
                        <label for="dish-category">Phân loại</label>
                        <select id="dish-category">
                            <option value="noodle">Noodle</option>
                            <option value="rice">Rice</option>
                            <option value="dessert">Dessert</option>
                            <option value="beverage">Beverage</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="dish-price">Giá</label>
                        <input type="number" id="dish-price" placeholder="Nhập giá món">
                    </div>

                    <div class="form-group">
                        <label for="dish-description">Mô tả</label>
                        <textarea id="dish-description" placeholder="Nhập mô tả món"></textarea>
                    </div>

                    <div class="form-actions">
                        <button type="button" class="cancel" id="close-popup">Hủy</button>
                        <button type="submit" class="save">Lưu</button>
                    </div>
                </form>
            </div>
        </div>
    `;

    // Append styles and content to the body
    document.head.insertAdjacentHTML('beforeend', style);
    document.body.insertAdjacentHTML('beforeend', html);

    const dishImageInput = document.getElementById('dish-image');
    const imagePreview = document.getElementById('image-preview');
    const overlay = document.getElementById('overlay');
    const closePopupButton = document.getElementById('close-popup');

    imagePreview.addEventListener('click', () => {
        dishImageInput.click();
    });

    dishImageInput.addEventListener('change', (event) => {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                imagePreview.innerHTML = `<img src="${e.target.result}" alt="Dish Image">`;
            };
            reader.readAsDataURL(file);
        }
    });

    closePopupButton.addEventListener('click', () => {
        overlay.remove();
    });
}

