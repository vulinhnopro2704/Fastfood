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

@WebServlet(name = "TrangChuController", urlPatterns = {"/TrangChu","/TrangChu/OrderDetail/addFood","/TrangChu/OrderDetail/deleteFood"})
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
             HttpSession session = request.getSession();  // Lấy đối tượng session
             String email = (String)  session.getAttribute("email");
             String role = (String)  session.getAttribute("role");
             Integer  id = (Integer)  session.getAttribute("id");
             Integer  idOrder = (Integer)  session.getAttribute("orderId");

        switch (path) {
            case "/TrangChu":
                if(email == null && role == null && id == null && idOrder == null) {
                    System.out.println("login lai ");
                    response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
                }
                else{
                    Users user = null;
                    user = usersBO.getUserById(id);

                    // get all food from database
                    List<Foods> listFoods = foodsBO.getAllFoods();
                    request.setAttribute("listFoods", listFoods);

//                   response.sendRedirect(request.getContextPath() + "/");

                    // get all foodOrderDetail from cart user login

                    List<foodOrderDetails> listFoodOrderDetails = orderDetailsBO.getFoodOrderDetailsByOrderId(idOrder);
                    request.setAttribute("listFoodOrderDetails", listFoodOrderDetails);

                    response.setContentType("text/plain");
                    request.getRequestDispatcher("/").forward(request, response);
                }
                break;

            case "/TrangChu/OrderDetail/addFood":
                System.out.println("addFood");
                System.out.println("idFood : " + request.getParameter("id"));
                int idFood = Integer.parseInt(request.getParameter("id"));
                System.out.println("idOrder : " + idOrder);

                Foods foods = foodsBO.getFoodById(idFood);

                int addFood = orderDetailsBO.addOrderDetails(idOrder,idFood,1,foods.getPrice(),"");
                if(addFood >0 ){
                    System.out.println("add food thanh cong");
                }
//                request.getRequestDispatcher("/TrangChu").forward(request, response);
                response.sendRedirect(response.encodeRedirectURL("/final_exam_war_exploded/TrangChu"));
                break;

                case "/TrangChu/OrderDetail/deleteFood":

                    System.out.println("deleteOrderDetail");
                    System.out.println("idOder : " + request.getParameter("id"));
                    int idOrderDetail = Integer.parseInt(request.getParameter("id"));
                    int deleteOrderDetail = orderDetailsBO.deleteOrderDetails(idOrderDetail);
                    if(deleteOrderDetail > 0){
                        System.out.println("deleteOrderDetail thanh cong");
                    }
                    response.sendRedirect(response.encodeRedirectURL("/final_exam_war_exploded/TrangChu"));
                    break;


            default:
                break;
        }
    }
}
