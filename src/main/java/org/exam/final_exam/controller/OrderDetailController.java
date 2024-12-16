package org.exam.final_exam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.exam.final_exam.bo.FoodsBO;
import org.exam.final_exam.bo.OrderDetailsBO;
import org.exam.final_exam.entity.Foods;
import org.exam.final_exam.entity.Users;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "OrderDetailController", urlPatterns = {"/OrderDetail/", "/OrderDetail/addFood","/OrderDetail/deleteFood"})
public class OrderDetailController extends HttpServlet {
    private final OrderDetailsBO orderDetailsBO;
    private final FoodsBO foodsBO;

    public OrderDetailController() {

        orderDetailsBO = new OrderDetailsBO();
        foodsBO = new FoodsBO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        System.out.println("path : " + path);
        switch (path){
            default :
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        System.out.println("path : " + path);
        // Lấy đối tượng session
        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
        if (session == null) {
            // Nếu không có session, chuyển hướng về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }
        Integer idOrder = (Integer) session.getAttribute("orderId");
        // Nếu các giá trị cần thiết không tồn tại hoặc không hợp lệ
        if (idOrder == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        switch (path){

            case "/OrderDetail/addFood":
                System.out.println("addFood");
                String idFoodStr = request.getParameter("idFood");
                System.out.println("idFoodStr : " + idFoodStr);
                if (idFoodStr != null) {
                    int idFood = Integer.parseInt(idFoodStr);
                    Foods foods = foodsBO.getFoodById(idFood);

                    int addFood = orderDetailsBO.addOrderDetails(idOrder, idFood, 1, foods.getPrice(), "");
                    if (addFood > 0) {
                        System.out.println("Thêm món thành công");
                    }
                }
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
//                request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
                break;


            case "/OrderDetail/deleteFood":
                System.out.println("deleteOrderDetail");
                String idOrderDetailStr = request.getParameter("idOrderDetail");

                System.out.println("idFoodStr : " + idOrderDetailStr);
                if (idOrderDetailStr != null) {
                    int idOrderDetail = Integer.parseInt(idOrderDetailStr);
                    int deleteOrderDetail = orderDetailsBO.deleteOrderDetails(idOrderDetail);
                    if (deleteOrderDetail > 0) {
                        System.out.println("Xóa món thành công");
                    }
                }
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
//                request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
                break;

            default :
                break;
        }
    }
}
