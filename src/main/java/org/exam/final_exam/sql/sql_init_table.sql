-- Drop all tables if they exist
DROP TABLE IF EXISTS CartDetails, Cart, OrderDetails, Orders, Foods, Categories, Users CASCADE;

-- Create Users table
CREATE TABLE Users (
                       id SERIAL PRIMARY KEY,
                       fullname VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       phoneNumber VARCHAR(20),
                       address TEXT,
                       role VARCHAR(50),
                       createdAt DATE DEFAULT CURRENT_DATE
);

-- Create Categories table
CREATE TABLE Categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description TEXT
);

-- Create Foods table
CREATE TABLE Foods (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description TEXT,
                       price DECIMAL(10, 2) NOT NULL,
                       imageLink TEXT,
                       categoryId INTEGER REFERENCES Categories(id) ON DELETE SET NULL,
                       createdAt DATE DEFAULT CURRENT_DATE
);

-- Create Orders table
CREATE TABLE Orders (
                        id SERIAL PRIMARY KEY,
                        userId INTEGER REFERENCES Users(id) ON DELETE CASCADE,
                        orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        totalAmount DECIMAL(10, 2),
                        status INTEGER,
                        createdAt DATE DEFAULT CURRENT_DATE,
                        type VARCHAR(50)
);

-- Create OrderDetails table
CREATE TABLE OrderDetails (
                              id SERIAL PRIMARY KEY,
                              orderId INTEGER REFERENCES Orders(id) ON DELETE CASCADE,
                              foodId INTEGER REFERENCES Foods(id) ON DELETE CASCADE,
                              quantity INTEGER NOT NULL,
                              subtotal DECIMAL(10, 2) NOT NULL,
                              message TEXT
);

-- Create Cart table
CREATE TABLE Cart (
                      id SERIAL PRIMARY KEY,
                      userId INTEGER REFERENCES Users(id) ON DELETE CASCADE,
                      createdAt DATE DEFAULT CURRENT_DATE
);

-- Create CartDetails table
CREATE TABLE CartDetails (
                             id SERIAL PRIMARY KEY,
                             cartId INTEGER REFERENCES Cart(id) ON DELETE CASCADE,
                             foodId INTEGER REFERENCES Foods(id) ON DELETE CASCADE,
                             quantity INTEGER NOT NULL,
                             subtotal DECIMAL(10, 2) NOT NULL,
                             message TEXT
);

-- Seed data for Users
INSERT INTO Users (fullname, email, password, phoneNumber, address, role, createdAt) VALUES
                                                                                         ('John Doe', 'john@example.com', 'password123', '1234567890', '123 Main St', 'customer', CURRENT_DATE),
                                                                                         ('Jane Smith', 'jane@example.com', 'securepassword', '0987654321', '456 Elm St', 'admin', CURRENT_DATE);

-- Seed data for Categories
INSERT INTO Categories (name, description) VALUES
                                               ('Beverages', 'Drinks and refreshments'),
                                               ('Snacks', 'Light and quick snacks');

-- Seed data for Foods
INSERT INTO Foods (name, description, price, imageLink, categoryId, createdAt) VALUES
                                                                                   ('Coca Cola', 'Chilled soft drink', 1.50, 'https://example.com/cocacola.jpg', 1, CURRENT_DATE),
                                                                                   ('Chips', 'Crispy potato chips', 2.00, 'https://example.com/chips.jpg', 2, CURRENT_DATE);

-- Seed data for Cart
INSERT INTO Cart (userId, createdAt) VALUES
    (1, CURRENT_DATE);

-- Seed data for CartDetails
INSERT INTO CartDetails (cartId, foodId, quantity, subtotal, message) VALUES
                                                                          (1, 1, 2, 3.00, '2 bottles of Coca Cola'),
                                                                          (1, 2, 1, 2.00, '1 packet of chips');

-- Seed data for Orders
INSERT INTO Orders (userId, orderDate, totalAmount, status, createdAt, type) VALUES
    (1, CURRENT_TIMESTAMP, 10.50, 1, CURRENT_DATE, 'delivery');

-- Seed data for OrderDetails
INSERT INTO OrderDetails (orderId, foodId, quantity, subtotal, message) VALUES
                                                                            (1, 1, 3, 4.50, '3 bottles of Coca Cola'),
                                                                            (1, 2, 1, 2.00, '1 packet of chips');
