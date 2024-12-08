package org.exam.final_exam.bo;



import org.exam.final_exam.dao.OrdersDAO;
import org.exam.final_exam.entity.Orders;

import java.util.Date;
import java.util.List;
public class OrdersBO {

    private OrdersDAO ordersDAO;

    public OrdersBO(){
        ordersDAO = new OrdersDAO();
    }
    public List<Orders> getAllOrders(){
        return  ordersDAO.getAllOrders();
    }

    public List<Orders> getOrdersListByUserId(int userId){
        return ordersDAO.getAllOrdersByUserId(userId);
    }

    public Orders getOrderById(int id){
        return  ordersDAO.getOrderById(id);
    }


    public int addOrder(int userId, Date orderDate, long totalamount,int status,String type){
        Orders orders = new Orders(userId, orderDate, totalamount, status, type);

        return  ordersDAO.addOrder(orders);
    }

    public int updateOrder(Orders orders){
        return  ordersDAO.updateOrder(orders);
    }

    public int deleteOrder(int id){
        return ordersDAO.deleteOrder(id);
    }

}
