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
import org.exam.final_exam.bo.OrdersBO;
import org.exam.final_exam.entity.OrderDetails;
import org.exam.final_exam.entity.Orders;
import org.exam.final_exam.entity.foodOrderDetails;
import org.exam.final_exam.enums.Status;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckoutController", urlPatterns = {"/checkout","/confirmOrder"})
public class CheckoutController extends HttpServlet {
    private final OrderDetailsBO orderDetailsBO;
    private final OrdersBO ordersBO;
    public CheckoutController() {
        orderDetailsBO = new OrderDetailsBO();
        ordersBO = new OrdersBO();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        System.out.println("CheckoutController - request.getContextPath() : " + request.getContextPath());
        System.out.println("CheckoutController - path : " + path);

        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
        if (session == null) {
            // Nếu không có session, chuyển hướng về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }
        Integer id = (Integer) session.getAttribute("id");
        Integer idOrder = (Integer) session.getAttribute("orderId");

        // Nếu các giá trị cần thiết không tồn tại hoặc không hợp lệ
        if (id == null || idOrder == null ) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        switch (path) {
            case "/checkout":

                // lay order nguoi dung
                Orders orders = ordersBO.getOrderById(idOrder);
                // Lấy danh sách foodOrderDetail từ giỏ hàng người dùng
                List<foodOrderDetails> listFoodOrderDetails = orderDetailsBO.getFoodOrderDetailsByOrderId(idOrder);
                request.setAttribute("listFoodOrderDetails", listFoodOrderDetails);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("/jsp/checkout/checkout.jsp").forward(request, response);
                break;

            case "/confirmOrder":
                // uodate status order = 1, updatedate -> đơn hàng đã được đặt
                ordersBO.updateOrderStatus(idOrder, Status.PROCESSING.name());
                // tao moi order cho user
                 ordersBO.addOrder(id,0, Status.PENDING.name(), "TAKEAWAY");
                 // set session orderid cho user
                session.setAttribute("orderId", ordersBO.getOrdersByUserId(id).getId());
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/order"));
                break;
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        System.out.println("path dpo: " + path);

        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
        if (session == null) {
            // Nếu không có session, chuyển hướng về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }
        Integer idOrder = (Integer) session.getAttribute("orderId");

        // Nếu các giá trị cần thiết không tồn tại hoặc không hợp lệ
        if (idOrder == null ) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }
        switch (path){
            case "/checkout":
                System.out.println("checkout");
//                System.out.println("type : " + request.getParameter("typeOrder"));
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
                    double total = 0;
                    OrderDetails orderDetails = new OrderDetails();
                    // cap nhap message cua orderDetail
                    for (FileItem item : formItems) {
                        if (item.isFormField()) {
                            // Nếu là trường form thông thường
                            String fieldName = item.getFieldName();
                            String fieldValue = item.getString();

                            switch (fieldName){
                                case "idOrderdt":
                                    int idOrderdt = Integer.parseInt(fieldValue);
                                    orderDetails = orderDetailsBO.getOrderDetails(idOrderdt);
                                    break;
                                case "subtotal":
                                    total += Double.parseDouble(fieldValue);
                                    break;
                                case "message":
                                    // update message orderDetail
                                    int update = orderDetailsBO.updateMessage(orderDetails.getId(), fieldValue);
                                    break;
                                case "quantity":
                                    System.out.println("quantity : " + fieldValue + " id : " + orderDetails.getId());
                                    int udquan = orderDetailsBO.updateQuantityNumber(orderDetails.getId(),Integer.parseInt(fieldValue));
                                    if(udquan>0){
                                        System.out.println("update quantity");
                                    }
                                case "typeOrder":
                                    // update type order
                                    ordersBO.updateOrderType(idOrder,fieldValue);
                                    break;
                            }

                            System.out.println("Field: " + fieldName + " = " + fieldValue);

                        }

                    }
                    // cap nhap total cua order

                    System.out.println("Total :  " + total + " id : " + idOrder);
                    ordersBO.updateTotalAmount(idOrder,total);

                    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/checkout"));
//                    request.getRequestDispatcher("/jsp/checkout/checkout.jsp").forward(request, response);
                } catch (Exception ex) {

                    response.getWriter().println("Error: " + ex.getMessage());
                }


                break;
            default :
                break;
        }
    }
}
