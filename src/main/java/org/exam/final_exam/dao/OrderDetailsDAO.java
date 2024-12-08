package org.exam.final_exam.dao;


import org.exam.final_exam.entity.OrderDetails;
import org.exam.final_exam.entity.foodOrderDetails;


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

    public List<foodOrderDetails> getFoodOrderDetailsByOrderId(int orderId) {
        String sql = "SELECT od.id,od.quantity,od.subtotal,od.message, f.name ,f.price,f.imageLink\n" +
                "FROM orderdetails od INNER JOIN foods f ON od.foodid = f.id\n" +
                "where od.orderid = ?;";
        return genericDAO.executeQuery(sql, rs -> foodOrderDetails.builder()
                .orderDetailId(rs.getInt("id"))
                .quantity(rs.getInt("quantity"))
                .subtotal(rs.getDouble("subtotal"))
                .message(rs.getString("message"))
                .name(rs.getString("name"))
                .price(rs.getDouble("price"))
                .imageLink(rs.getString("imageLink"))
                .build(),orderId);
    }
    public int addOrderDetail (OrderDetails orderDetail) {
        String sql = "INSERT INTO orderdetails(orderId, foodId, quantity, subtotal,message) VALUES(?, ?, ?, ?, ?)";
        return genericDAO.executeUpdate(sql, orderDetail.getOrderId(), orderDetail.getFoodId(), orderDetail.getQuantity(), orderDetail.getSubtotal(),orderDetail.getMessage());
    }

    public int updateOrderDetail(OrderDetails orderDetail) {
        String sql = "UPDATE orderdetails SET orderId = ?, foodId = ?, quantity = ?, subtotal = ?, message = ? WHERE id = ?";
        return genericDAO.executeUpdate(sql, orderDetail.getOrderId(), orderDetail.getFoodId(), orderDetail.getQuantity(), orderDetail.getSubtotal(),orderDetail.getMessage(),orderDetail.getId());
    }

    public int deleteOrderDetail(int id) {
        String sql = "DELETE FROM orderdetails WHERE id = ?";
        return genericDAO.executeUpdate(sql, id);
    }

    public boolean isExistFoodInOrderDetail(int idorder , int foodId) {
        String sql = "SELECT * FROM orderdetails WHERE orderid = ? and foodid = ?";
        return genericDAO.isExist(sql, idorder ,foodId);
    }

    public boolean isExistOrderDetail(int id) {
        String sql = "SELECT * FROM orderdetails WHERE id = ?";
        return genericDAO.isExist(sql, id);
    }
}
