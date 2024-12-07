

CREATE TABLE Users (
                       id SERIAL PRIMARY KEY,
                       fullname VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       phoneNumber VARCHAR(20),
                       address TEXT,
                       role VARCHAR(50),
                       createdAt DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE Categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description TEXT
);

CREATE TABLE Foods (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description TEXT,
                       price DECIMAL(10, 2) NOT NULL,
                       imageLink TEXT,
                       categoryId INTEGER REFERENCES Categories(id),
                       createdAt DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE Orders (
                        id SERIAL PRIMARY KEY,
                        userId INTEGER REFERENCES Users(id),
                        orderDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        totalAmount DECIMAL(10, 2) NOT NULL,
                        status INTEGER NOT NULL,
                        createdAt DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE OrderDetails (
                              id SERIAL PRIMARY KEY,
                              orderId INTEGER REFERENCES Orders(id),
                              foodId INTEGER REFERENCES Foods(id),
                              quantity INTEGER NOT NULL,
                              subtotal DECIMAL(10, 2) NOT NULL
);

-- Seed data for Users table
INSERT INTO Users (fullname, email, password, phoneNumber, address, role, createdAt) VALUES
                                                                                         ('John Doe', 'john.doe@example.com', 'password123', '1234567890', '123 Main St', 'user', CURRENT_DATE),
                                                                                         ('Jane Smith', 'jane.smith@example.com', 'password456', '0987654321', '456 Elm St', 'admin', CURRENT_DATE);

-- Seed data for Categories table
INSERT INTO Categories (name, description) VALUES
                                               ('Beverages', 'Drinks and refreshments'),
                                               ('Snacks', 'Light meals and snacks');

-- Seed data for Foods table
INSERT INTO Foods (name, description, price, imageLink, categoryId, createdAt) VALUES
                                                                                   ('Coca Cola', 'Carbonated soft drink', 1.50, 'http://example.com/coca_cola.jpg', 1, CURRENT_DATE),
                                                                                   ('Chips', 'Crispy potato chips', 2.00, 'http://example.com/chips.jpg', 2, CURRENT_DATE);

-- Seed data for Orders table
INSERT INTO Orders (userId, orderDate, totalAmount, status, createdAt) VALUES
                                                                           (1, CURRENT_TIMESTAMP, 3.50, 1, CURRENT_DATE),
                                                                           (2, CURRENT_TIMESTAMP, 2.00, 0, CURRENT_DATE);

-- Seed data for OrderDetails table
INSERT INTO OrderDetails (orderId, foodId, quantity, subtotal) VALUES
                                                                   (1, 1, 2, 3.00),
                                                                   (1, 2, 1, 2.00),
                                                                   (2, 2, 1, 2.00);

