package org.exam.final_exam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "OrderController", urlPatterns = {"/order"})
public class OrderController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        System.out.println("OrderController - request.getContextPath() : " + request.getContextPath());
        System.out.println("OrderController - path : " + path);

        switch (path) {
            case "/order":
                request.getRequestDispatcher("/jsp/order/order.jsp").forward(request, response);
                break;
        }
    }
}
