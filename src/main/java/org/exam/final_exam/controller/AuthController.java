package org.exam.final_exam.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AuthController", urlPatterns = {"/auth", "/auth/login", "/auth/logout"})
public class AuthController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        response.setContentType("text/html");

        switch (path) {
            case "/auth":
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>Auth</h1>");
                out.println("</body></html>");
                break;
            case "/auth/login":
                PrintWriter outLogin = response.getWriter();
                outLogin.println("<html><body>");
                outLogin.println("<h1>Login</h1>");
                outLogin.println("</body></html>");
                break;
            case "/auth/logout":
                response.sendRedirect(request.getContextPath() + "/auth/login");
                break;
        }
    }
}
