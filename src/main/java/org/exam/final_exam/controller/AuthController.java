package org.exam.final_exam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.exam.final_exam.bo.UsersBO;
import org.exam.final_exam.entity.Users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AuthController", urlPatterns = {"/auth", "/auth/login", "/auth/logout", "/auth/signup"})
public class AuthController extends HttpServlet {

    private final UsersBO usersBO ;
    public AuthController() {
        usersBO = new UsersBO();
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


    //                PrintWriter outLogin = response.getWriter();
//                outLogin.println("<html><body>");
//                outLogin.println("<h1>Login</h1>");
//                outLogin.println("</body></html>");
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String path = request.getServletPath();
        response.setContentType("text/html");

        switch (path) {

            case "/auth/login":
                System.out.println("email is " + request.getParameter("email") + " password is " + request.getParameter("password"));
                Users user = null;
                user = usersBO.getUserEmailPassword(request.getParameter("email"), request.getParameter("password"));
               if (user == null  ) {
                   response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
               }
               else{
                   HttpSession session = request.getSession();  // Lấy đối tượng session
                   session.setAttribute("email", user.getEmail());
                   session.setAttribute("role", user.getRole());
                   session.setAttribute("id", Integer.toString(user.getId()));
                   request.getRequestDispatcher("index.jsp").forward(request, response);
               }

                break;

        }

    }
}
