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
import org.exam.final_exam.bo.FoodsBO;
import org.exam.final_exam.bo.OrderDetailsBO;


import org.exam.final_exam.entity.Foods;

import org.exam.final_exam.entity.foodOrderDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "TrangChuController", urlPatterns = {"/","/Order"})
public class TrangChuController extends HttpServlet {
    private final FoodsBO foodsBO ;
    private final OrderDetailsBO orderDetailsBO ;

    public TrangChuController() {
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
            default:
                response.sendRedirect(request.getContextPath() + "/404");
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

    // Hàm kiểm tra xem URL có phải file tĩnh hay không
    private boolean isStaticFile(String uri) {
        return uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") ||
                uri.endsWith(".jpg") || uri.endsWith(".jpeg") || uri.endsWith(".gif");
    }
}
