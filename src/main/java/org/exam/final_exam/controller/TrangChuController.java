package org.exam.final_exam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.exam.final_exam.bo.FoodsBO;
import org.exam.final_exam.bo.OrderDetailsBO;
import org.exam.final_exam.bo.OrdersBO;
import org.exam.final_exam.bo.UsersBO;
import org.exam.final_exam.entity.Foods;
import org.exam.final_exam.entity.Users;
import org.exam.final_exam.entity.foodOrderDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "TrangChuController", urlPatterns = {"/","/TrangChu/OrderDetail/addFood","/TrangChu/OrderDetail/deleteFood"})
public class TrangChuController extends HttpServlet {
    private final UsersBO usersBO ;
    private final OrdersBO ordersBO ;
    private final FoodsBO foodsBO ;
    private final OrderDetailsBO orderDetailsBO ;

    public TrangChuController() {
        usersBO = new UsersBO();
        ordersBO = new OrdersBO();
        foodsBO = new FoodsBO();
        orderDetailsBO = new OrderDetailsBO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        System.out.println("TrangChu");

        String uri = request.getRequestURI();
        System.out.println("Requested URI: " + uri);

//        // Kiểm tra xem yêu cầu có phải là file tĩnh
//        if (isStaticFile(uri)) {
//            // Chuyển tiếp yêu cầu file tĩnh tới default servlet
//            request.getRequestDispatcher(uri).forward(request, response);
//            return;
//        }

        // Lấy đối tượng session
        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
        if (session == null) {
            // Nếu không có session, chuyển hướng về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        // Kiểm tra và lấy các giá trị từ session
        String email = (String) session.getAttribute("email");
        String role = (String) session.getAttribute("role");
        Integer id = (Integer) session.getAttribute("id");
        Integer idOrder = (Integer) session.getAttribute("orderId");

        // Nếu các giá trị cần thiết không tồn tại hoặc không hợp lệ
        if (email == null || role == null || id == null || idOrder == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        // Xử lý dựa trên đường dẫn yêu cầu
        switch (path) {
            case "/":
                // Lấy danh sách tất cả foods từ database
                List<Foods> listFoods = foodsBO.getAllFoods();
                request.setAttribute("listFoods", listFoods);

                // Lấy danh sách foodOrderDetail từ giỏ hàng người dùng
                List<foodOrderDetails> listFoodOrderDetails = orderDetailsBO.getFoodOrderDetailsByOrderId(idOrder);
                request.setAttribute("listFoodOrderDetails", listFoodOrderDetails);

                // Chuyển tiếp đến trang JSP
                request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
                break;

            case "/TrangChu/OrderDetail/addFood":
                System.out.println("addFood");
                String idFoodStr = request.getParameter("id");
                if (idFoodStr != null) {
                    int idFood = Integer.parseInt(idFoodStr);
                    Foods foods = foodsBO.getFoodById(idFood);

                    int addFood = orderDetailsBO.addOrderDetails(idOrder, idFood, 1, foods.getPrice(), "");
                    if (addFood > 0) {
                        System.out.println("Thêm món thành công");
                    }
                }
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/TrangChu"));
                break;

            case "/TrangChu/OrderDetail/deleteFood":
                System.out.println("deleteOrderDetail");
                String idOrderDetailStr = request.getParameter("id");
                if (idOrderDetailStr != null) {
                    int idOrderDetail = Integer.parseInt(idOrderDetailStr);
                    int deleteOrderDetail = orderDetailsBO.deleteOrderDetails(idOrderDetail);
                    if (deleteOrderDetail > 0) {
                        System.out.println("Xóa món thành công");
                    }
                }
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/TrangChu"));
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/404");
                break;
        }
    }

    // Hàm kiểm tra xem URL có phải file tĩnh hay không
    private boolean isStaticFile(String uri) {
        return uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") ||
                uri.endsWith(".jpg") || uri.endsWith(".jpeg") || uri.endsWith(".gif");
    }
}
