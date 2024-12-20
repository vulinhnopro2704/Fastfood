package org.exam.final_exam.dao;

import org.exam.final_exam.entity.Orders;


import java.util.List;
public class OrdersDAO {
    private GenericDAO genericDAO;

    public OrdersDAO() {
        genericDAO = new GenericDAO();
    }

    public List<Orders> getAllOrders() {
        String sql = "SELECT * FROM orders order by orderDate desc";
        return genericDAO.executeQuery(sql, rs -> Orders.builder()
                .id(rs.getInt("id"))
                .userId(rs.getInt("userId"))
                .orderDate(rs.getDate("orderDate"))
                .totalAmount(rs.getDouble("totalAmount"))
                .status(rs.getString("status"))
                .createAt(rs.getDate("createdat"))
                .type(rs.getString("type"))
                .build());
    }
    public List<Orders> getAllOrdersByUserId(int userId) {
        String sql = "SELECT * FROM orders where userId = ? order by orderDate desc";
        return genericDAO.executeQuery(sql, rs -> Orders.builder()
                .id(rs.getInt("id"))
                .userId(rs.getInt("userId"))
                .orderDate(rs.getDate("orderDate"))
                .totalAmount(rs.getDouble("totalAmount"))
                .status(rs.getString("status"))
                .createAt(rs.getDate("createdat"))
                .type(rs.getString("type"))
                .build(),userId);
    }
    public Orders getOrdersByUserId(int userId) {
        String sql = "SELECT * FROM orders where userId = ? LIMIT 1";
        return genericDAO.find(sql, rs -> Orders.builder()
                .id(rs.getInt("id"))
                .userId(rs.getInt("userId"))
                .orderDate(rs.getDate("orderDate"))
                .totalAmount(rs.getDouble("totalAmount"))
                .status(rs.getString("status"))
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
                .status(rs.getString("status"))
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

    public int updateOrderStatus(int id, String status) {
        String sql = "UPDATE orders SET status = ?,orderDate = ? WHERE id = ?";
        long currentTimeMillis = System.currentTimeMillis();
        java.sql.Date sqlDate = new java.sql.Date(currentTimeMillis);
        return genericDAO.executeUpdate(sql, status,sqlDate, id);
    }

    public int updateOderType(int id, String type) {
        String sql = "UPDATE orders SET type = ? WHERE id = ?";
        return genericDAO.executeUpdate(sql, type, id);
    }

    public int updateTotalAmount(int idOrder, double totalAmount) {
        String sql = "UPDATE orders SET totalAmount = ? WHERE id = ?";
        return genericDAO.executeUpdate(sql, totalAmount, idOrder);

    }

    public int updateTotalAmountDelete(int idOrder, double totalAmount) {
        String sql = "UPDATE orders SET totalAmount = totalAmount - ? WHERE id = ?";
        return genericDAO.executeUpdate(sql, totalAmount, idOrder);

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
