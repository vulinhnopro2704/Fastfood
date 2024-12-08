package org.exam.final_exam.dao;

import org.exam.final_exam.entity.Orders;


import java.util.List;
public class OrdersDAO {
    private GenericDAO genericDAO;

    public OrdersDAO() {
        genericDAO = new GenericDAO();
    }

    public List<Orders> getAllOrders() {
        String sql = "SELECT * FROM orders";
        return genericDAO.executeQuery(sql, rs -> Orders.builder()
                .id(rs.getInt("id"))
                .userId(rs.getInt("userId"))
                .orderDate(rs.getDate("orderDate"))
                .totalAmount(rs.getDouble("totalAmount"))
                .status(rs.getInt("status"))
                .createAt(rs.getDate("createdat"))
                .type(rs.getString("type"))
                .build());
    }
    public Orders getOrdersByUserId(int userId) {
        String sql = "SELECT * FROM orders where userId = ? and status = 0 LIMIT 1";
        return genericDAO.find(sql, rs -> Orders.builder()
                .id(rs.getInt("id"))
                .userId(rs.getInt("userId"))
                .orderDate(rs.getDate("orderDate"))
                .totalAmount(rs.getDouble("totalAmount"))
                .status(rs.getInt("status"))
                .createAt(rs.getDate("createdat"))
                .type(rs.getString("type"))
                .build(),userId);
    }

    public Orders getOrderById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return genericDAO.find(sql, rs -> Orders.builder()
                .id(rs.getInt("id"))
                .userId(rs.getInt("userId"))
                .orderDate(rs.getDate("orderDate"))
                .totalAmount(rs.getDouble("totalAmount"))
                .status(rs.getInt("status"))
                .createAt(rs.getDate("createdat"))
                .type(rs.getString("type"))
                .build(), id);
    }

    public int addOrder(Orders order) {
        String sql = "INSERT INTO orders(userId, totalAmount, status,createdat, type) VALUES(?, ?, ?, ?, ?)";

        java.sql.Date sqlDate = new java.sql.Date(order.getCreateAt().getTime());
        return genericDAO.executeUpdate(sql,order.getUserId(), order.getTotalAmount(), order.getStatus(),sqlDate, order.getType());
    }

    public int updateOrder(Orders order) {
        String sql = "UPDATE orders SET userId = ?, orderDate = ?, totalAmount = ?, status = ?, type = ? WHERE id = ?";
        return genericDAO.executeUpdate(sql, order.getUserId(), order.getOrderDate(), order.getTotalAmount(), order.getStatus(),order.getType(), order.getId());
    }

    public int deleteOrder(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        return genericDAO.executeUpdate(sql, id);
    }

    public boolean isExistOrder(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return genericDAO.isExist(sql, id);
    }
}
