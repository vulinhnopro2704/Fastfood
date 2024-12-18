package org.exam.final_exam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

import org.exam.final_exam.bo.OrderDetailsBO;
import org.exam.final_exam.bo.OrdersBO;
import org.exam.final_exam.entity.Orders;
import org.exam.final_exam.entity.foodOrderDetails;
import org.exam.final_exam.enums.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "OrderController", urlPatterns = {"/order","/Order"})
public class OrderController extends HttpServlet {

    private final OrdersBO ordersBO ;
    private final OrderDetailsBO orderDetailsBO ;
    public OrderController() {
        ordersBO = new OrdersBO();
        orderDetailsBO = new OrderDetailsBO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        System.out.println("OrderController - request.getContextPath() : " + request.getContextPath());
        System.out.println("OrderController - path : " + path);

        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
        if (session == null) {
            // Nếu không có session, chuyển hướng về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }
        Integer id = (Integer) session.getAttribute("id");

        // Nếu các giá trị cần thiết không tồn tại hoặc không hợp lệ
        if (id == null ) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        switch (path) {
            case "/order":
                // lay tat ca order cua user by id user
                String role = session.getAttribute("role").toString();
                List<Orders> orders = new ArrayList<>();
                if (role.equalsIgnoreCase("USER")) {
                    orders = ordersBO.getAllOrdersByUserId(id);
                }
                else if (role.equalsIgnoreCase("ADMIN")) {
                    orders = ordersBO.getAllOrders();
                }
                else  {
                    response.sendRedirect(request.getContextPath() + "/auth/login");
                    return;
                }

                // lay tat ca orderdetail and food cua tung order
                List<foodOrderDetails> listFoodOrderDetails = new ArrayList<>();

                for (Orders item : orders) {
                    List<foodOrderDetails> listFoodOrder = orderDetailsBO.getFoodOrderDetailsByOrderId(item.getId());
                    listFoodOrderDetails.addAll(listFoodOrder);
                }

                orders = orders.reversed();
                request.setAttribute("orders", orders);
                request.setAttribute("listFoodOrderDetails", listFoodOrderDetails);

                // Set enum values as request attribute
                HashMap<String, String> statusValues = Status.getValues();
                request.setAttribute("statusValues", statusValues);

                request.getRequestDispatcher("/jsp/order/order.jsp").forward(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        System.out.println("path dpo: " + path);
        switch (path){
            case "/Order":
                System.out.println("order");
                if (!JakartaServletFileUpload.isMultipartContent(request)) {
                    response.getWriter().println("Error: Form must have enctype=multipart/form-data.");
                    return;
                }
                try {
                    // Khởi tạo Factory và FileUpload handler
                    DiskFileItemFactory factory = new DiskFileItemFactory.Builder().get();
                    JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

                    // Parse request để lấy danh sách các FileItem
                    List<FileItem> formItems = upload.parseRequest(request);

                    for (FileItem item : formItems) {
                        if (item.isFormField()) {
                            // Nếu là trường form thông thường
                            String fieldName = item.getFieldName();
                            String fieldValue = item.getString();
                            System.out.println("Field: " + fieldName + " = " + fieldValue);
                            response.getWriter().println("Field: " + fieldName + " = " + fieldValue + "<br>");

                        } else {
                            // Nếu là file upload
                            String fileName = item.getName();
                            response.getWriter().println("File: " + fileName + "<br>");
                        }
                    }
                } catch (Exception ex) {
                    response.getWriter().println("Error: " + ex.getMessage());
                }
                break;
            default :
                break;
        }
    }
}
