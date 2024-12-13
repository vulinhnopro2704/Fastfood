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
                width: 500px;
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

            #drop-area {
                width: 100%;
                height: 300px;
                padding: 30px;
                border-radius: 20px;
                display: flex;
                justify-content: center;
                align-items: center;
                background-color: #1e1e2f;
            }
        
            @keyframes dropAreaPulse {
                0% {
                    transform: scale(1);
                }
                50% {
                    transform: scale(1.1);
                }
                100% {
                    transform: scale(1);
                }
            }
        
            @keyframes imgViewBounce {
                0% {
                    transform: translateY(0);
                }
                50% {
                    transform: translateY(-10px);
                }
                100% {
                    transform: translateY(0);
                }
            }
        
            #drop-area.drag-over {
                opacity: 0.7;
                animation: dropAreaPulse 0.5s ease-in-out infinite;
            }
        
            #drop-area.drag-over #img-view {
                animation: imgViewBounce 0.5s ease-in-out infinite;
            }
        
            #img-view {
                width: 100%;
                height: 100%;
                object-fit: cover;
                border-radius: 20px;
                border: 2px dashed #bbb5ff;
                font-size: 16px;
                color: #bbb5ff;
                text-align: center;
                cursor: pointer;
                background: #1e1e2f center;
                background-size: cover;
            }
        
            #img-view img {
                width: 100px;
                margin-top: 25px;
            }
        
            #img-view .heading {
                font-size: 20px;
                margin-top: 10px;
                color: #ffffff;
                font-weight: 500;
            }
        
            #img-view .sub-heading {
                font-size: 16px;
                margin-top: 5px;
                color: #666;
            }
            
        </style>
    `;
    const html = `
        <div class="overlay" id="overlay">
            <div class="add-dish-container">
                <h2>Thêm món ăn</h2>
                <form id="add-dish-form" method="post" action="/final_exam_war_exploded/food" enctype="multipart/form-data">
                    <label for="input-file" id="drop-area">
                        <input type="file" id="input-file" name="image" accept="image/*" hidden>
                        <div id="img-view">
                            <img src="/final_exam_war_exploded/assets/default/upload.png" alt="Drag and drop here">
                            <p class="heading">Drag and drop or click here</p>
                            <p class="sub-heading">to upload image</p>
                        </div>
                    </label>
                
                    <div class="form-group">
                        <label for="dish-name">Tên món</label>
                        <input type="text" id="dish-name" name="name" placeholder="Nhập tên món">
                    </div>
                
                    <div class="form-group">
                        <label for="dish-category">Phân loại</label>
                        <select id="dish-category" name="categoryId">
                            <option value="1">Noodle</option>
                            <option value="2">Rice</option>
                            <option value="3">Dessert</option>
                            <option value="4">Beverage</option>
                        </select>
                    </div>
                
                    <div class="form-group">
                        <label for="dish-price">Giá</label>
                        <input type="number" id="dish-price" name="price" placeholder="Nhập giá món">
                    </div>
                
                    <div class="form-group">
                        <label for="dish-description">Mô tả</label>
                        <textarea id="dish-description" name="description" placeholder="Nhập mô tả món"></textarea>
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

    const dropArea = document.getElementById('drop-area');
    const inputFile = document.getElementById('input-file');
    const imgView = document.getElementById('img-view');
    const closePopupButton = document.getElementById('close-popup');

    dropArea.addEventListener('dragover', (e) => {
        e.preventDefault();
        dropArea.classList.add('drag-over');
    });

    dropArea.addEventListener('dragleave', (e) => {
        e.preventDefault();
        dropArea.classList.remove('drag-over');
    });

    dropArea.addEventListener('drop', (e) => {
        e.preventDefault();
        dropArea.classList.remove('drag-over');
        inputFile.files = e.dataTransfer.files;
        uploadImage();
    });

    inputFile.addEventListener('change', uploadImage);

    function uploadImage() {
        let imgLink = URL.createObjectURL(inputFile.files[0]);
        imgView.style.backgroundImage = `url(${imgLink})`;
        imgView.textContent = '';
    }

    closePopupButton.addEventListener('click', () => {
        overlay.remove();
    });
}