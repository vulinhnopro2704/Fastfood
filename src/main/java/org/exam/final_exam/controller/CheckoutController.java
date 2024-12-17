package org.exam.final_exam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CheckoutController", urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        System.out.println("CheckoutController - request.getContextPath() : " + request.getContextPath());
        System.out.println("CheckoutController - path : " + path);

        switch (path) {
            case "/checkout":
                request.getRequestDispatcher("/jsp/checkout/checkout.jsp").forward(request, response);
                break;
        }
    }
}
