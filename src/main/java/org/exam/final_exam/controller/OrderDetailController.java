package org.exam.final_exam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.exam.final_exam.bo.OrderDetailsBO;
import org.exam.final_exam.entity.Users;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "OrderDetailController", urlPatterns = {"/OrderDetail/", "/OrderDetail/addFood"})
public class OrderDetailController extends HttpServlet {
    private final OrderDetailsBO orderDetailsBO;
    public OrderDetailController() {
        orderDetailsBO = new OrderDetailsBO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        System.out.println("path : " + path);
        switch (path){
            case "/OrderDetail/addFood":

                String idFood = request.getParameter("id");
                System.out.println("idFood : " + idFood);
//                HttpSession session = request.getSession();  // Lấy đối tượng session
//                String idOrder = (String) session.getAttribute("orderId");
//
//                int addFood = orderDetailsBO.addOrderDetails(Integer.parseInt(idOrder),Integer.parseInt(idFood),1,9,"more ice");
//                if(addFood >0 ){
//                    System.out.println("add food thanh cong");
//                }
//                request.getRequestDispatcher("/").forward(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {

    }
}
