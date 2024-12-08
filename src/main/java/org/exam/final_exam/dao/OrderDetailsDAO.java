package org.exam.final_exam.dao;


import org.exam.final_exam.entity.OrderDetails;
import org.exam.final_exam.entity.VacXin;

import java.util.List;
public class OrderDetailsDAO {
    private GenericDAO genericDAO;

    public OrderDetailsDAO() {
        genericDAO = new GenericDAO();
    }

    public List<OrderDetails> getAllOrderDetails() {
        String sql = "SELECT * FROM orderdetails";
        return genericDAO.executeQuery(sql, rs -> OrderDetails.builder()
                .id(rs.getInt("id"))
                .orderId(rs.getInt("orderId"))
                .foodId(rs.getInt("foodId"))
                .quantity(rs.getInt("quantity"))
                .subtotal(rs.getDouble("subtotal"))
                .message(rs.getString("message"))
                .build());
    }
    public List<OrderDetails> getAllOrderDetailsByOrderId(int orderId) {
        String sql = "SELECT * FROM orderdetails where orderId = ?";
        return genericDAO.executeQuery(sql, rs -> OrderDetails.builder()
                .id(rs.getInt("id"))
                .orderId(rs.getInt("orderId"))
                .foodId(rs.getInt("foodId"))
                .quantity(rs.getInt("quantity"))
                .subtotal(rs.getDouble("subtotal"))
                .message(rs.getString("message"))
                .build(),orderId);
    }

    public OrderDetails getOrderDetailById(int id) {
        String sql = "SELECT * FROM orderdetails WHERE id = ?";
        return genericDAO.find(sql,  rs -> OrderDetails.builder()
                .id(rs.getInt("id"))
                .orderId(rs.getInt("orderId"))
                .foodId(rs.getInt("foodId"))
                .quantity(rs.getInt("quantity"))
                .subtotal(rs.getDouble("subtotal"))
                .message(rs.getString("message"))
                .build(), id);
    }

    public int addOrderDetail (OrderDetails orderDetail) {
        String sql = "INSERT INTO orderdetails(orderId, foodId, quantity, subtotal,message) VALUES(?, ?, ?, ?, ?)";
        return genericDAO.executeUpdate(sql, orderDetail.getOrderId(), orderDetail.getFoodId(), orderDetail.getQuantity(), orderDetail.getSubtotal());
    }

    public int updateOrderDetail(OrderDetails orderDetail) {
        String sql = "UPDATE orderdetails SET orderId = ?, foodId = ?, quantity = ?, subtotal = ?, message = ? WHERE id = ?";
        return genericDAO.executeUpdate(sql, orderDetail.getOrderId(), orderDetail.getFoodId(), orderDetail.getQuantity(), orderDetail.getSubtotal(),orderDetail.getMessage(),orderDetail.getId());
    }

    public int deleteOrderDetail(int id) {
        String sql = "DELETE FROM orderdetails WHERE id = ?";
        return genericDAO.executeUpdate(sql, id);
    }

    public boolean isExistOrderDetail(int id) {
        String sql = "SELECT * FROM orderdetails WHERE id = ?";
        return genericDAO.isExist(sql, id);
    }
}
