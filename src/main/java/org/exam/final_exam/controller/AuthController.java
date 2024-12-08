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
import org.exam.final_exam.entity.OrderDetails;
import org.exam.final_exam.entity.foodOrderDetails;
import org.exam.final_exam.entity.Orders;
import org.exam.final_exam.entity.Users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AuthController", urlPatterns = {"/auth", "/auth/login", "/auth/logout", "/auth/signup",})
public class AuthController extends HttpServlet {

    private final UsersBO usersBO ;
    private final OrdersBO ordersBO ;


    public AuthController() {
        usersBO = new UsersBO();
        ordersBO = new OrdersBO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");

        switch (path) {
            case "/auth":
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>Login</h1>");
                out.println("</body></html>");
                break;
            case "/auth/login":
                request.getRequestDispatcher("/jsp/auth/login.jsp").forward(request, response);
                break;
            case "/auth/signup":
                request.getRequestDispatcher("/jsp/auth/sign-up.jsp").forward(request, response);
                break;
            case "/auth/logout":
//                response.sendRedirect(request.getContextPath() + "/auth/login");
                PrintWriter out1 = response.getWriter();
                out1.println("<html><body>");
                out1.println("<h1>Logout</h1>");
                out1.println("</body></html>");
                break;

            default: break;
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String path = request.getServletPath();
        response.setContentType("text/html");
        System.out.println("request.getContextPath() : " + request.getContextPath());
        switch (path) {
            case "/auth/login":
                System.out.println("email is " + request.getParameter("email") + " password is " + request.getParameter("password"));
                Users user = null;
                user = usersBO.getUserEmailPassword(request.getParameter("email"), request.getParameter("password"));
               if (user == null  ) {
                   response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
               }
               else{
                   // lay order cua user login
                   Orders orderUser = ordersBO.getOrdersByUserId(user.getId());
                    System.out.println("orderid : " + orderUser.getId());

                   HttpSession session = request.getSession();  // Lấy đối tượng session
                   session.setAttribute("email", user.getEmail());
                   session.setAttribute("role", user.getRole());
                   session.setAttribute("id", user.getId());
                   session.setAttribute("orderId", orderUser.getId());

//                   request.getRequestDispatcher("index.jsp").forward(request, response);
//                   // get all food from database
//                    List<Foods> listFoods = foodsBO.getAllFoods();
//                   request.setAttribute("listFoods", listFoods);
////                   response.sendRedirect(request.getContextPath() + "/");
//                   // get all foodOrderDetail from cart user login

//                    List<foodOrderDetails> listFoodOrderDetails = orderDetailsBO.getFoodOrderDetailsByOrderId(orderUser.getId());
//                    request.setAttribute("listFoodOrderDetails", listFoodOrderDetails);
//                   request.getRequestDispatcher("/").forward(request, response);
                   response.sendRedirect(request.getContextPath() + "/TrangChu");

               }
                  break;
               case "/auth/signup":
                   String email = request.getParameter("email");
                   String phone = request.getParameter("phone");
                   String fullname = request.getParameter("fullname");
                   String address = request.getParameter("address");
                   String password = request.getParameter("password");
                   int adduser = usersBO.addUser(fullname,email,password,phone,address,"USER");
                   if (adduser > 0) {
                       // tim user signin
                       Users userSignin = usersBO.findUserSignIn(email,fullname,phone,address);
                       // tao moi order cho user signin
                       int addNewOrder = ordersBO.addOrder(userSignin.getId(),0,0,"TAKEAWAY");
                        if(addNewOrder > 0){
                            response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
                        }
                   }
                   else{
                       response.sendRedirect(request.getContextPath() + "/jsp/auth/sign-up.jsp");
                   }
                   break;


        }

    }
}
