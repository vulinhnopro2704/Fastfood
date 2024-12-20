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
import org.exam.final_exam.entity.*;
import org.exam.final_exam.enums.Status;

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
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/auth/login");
                break;
            default:
                break;
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
                       System.out.println("user null");
                       response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
                   }
                   else{
                       HttpSession session = request.getSession();
                       session.setAttribute("fullname", user.getFullName());
                       session.setAttribute("email", user.getEmail());
                       session.setAttribute("role", user.getRole());
                       session.setAttribute("id", user.getId());
                       session.setAttribute("orderId", ordersBO.getOrdersByUserId(user.getId()).getId());

                       response.sendRedirect(request.getContextPath() + "/");
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
                       int addNewOrder = ordersBO.addOrder(userSignin.getId(),0, Status.PENDING.name(),"TAKEAWAY");
                        if(addNewOrder > 0){
                            response.sendRedirect(request.getContextPath() + "/auth/login");
                        }
                   }
                   else{
                       response.sendRedirect(request.getContextPath() + "/auth/signup");
                   }
                   break;

               case "/auth/logout":
                   HttpSession session = request.getSession();
                   session.invalidate();
                   response.sendRedirect(request.getContextPath() + "/auth/login");
                   break;
            default:
                break;
        }

    }
}
