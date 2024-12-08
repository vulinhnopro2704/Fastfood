package org.exam.final_exam.bo;

import org.exam.final_exam.dao.OrderDetailsDAO;
import org.exam.final_exam.entity.OrderDetails;
import org.exam.final_exam.entity.foodOrderDetails;

import java.util.List;

public class OrderDetailsBO {
    private OrderDetailsDAO orderDetailsDAO;

    public OrderDetailsBO() {
        orderDetailsDAO = new OrderDetailsDAO();
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsDAO.getAllOrderDetails();
    }
    public List<OrderDetails> getOrderDetailsListByOrderId(int orderId) {
        return orderDetailsDAO.getAllOrderDetailsByOrderId(orderId);
    }
    public List<foodOrderDetails> getFoodOrderDetailsByOrderId(int orderid){
        return orderDetailsDAO.getFoodOrderDetailsByOrderId(orderid);
    }
    public OrderDetails getOrderDetails(int orderId) {
        return orderDetailsDAO.getOrderDetailById(orderId);
    }

    public int addOrderDetails(int orderId, int foodId, int quantity, double subtotal, String message) {
        if(orderDetailsDAO.isExistFoodInOrderDetail(orderId, foodId)) {
            return -1;
        }
        OrderDetails orderDetail = new OrderDetails(orderId, foodId, quantity, subtotal, message);

        return orderDetailsDAO.addOrderDetail(orderDetail);
    }
    public int updateOrderDetails(OrderDetails orderDetails) {
        return orderDetailsDAO.updateOrderDetail(orderDetails);
    }
    public int deleteOrderDetails(int orderId) {
        return  orderDetailsDAO.deleteOrderDetail(orderId);
    }
}
