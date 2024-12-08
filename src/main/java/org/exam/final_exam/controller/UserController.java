package org.exam.final_exam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.exam.final_exam.bo.OrdersBO;
import org.exam.final_exam.bo.UsersBO;
import org.exam.final_exam.entity.Orders;
import org.exam.final_exam.entity.Users;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = {"/user", "/user/list"})
public class UserController extends HttpServlet {
    private final UsersBO usersBO ;
    public UserController() {
        usersBO = new UsersBO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        System.out.println("path : " + path);
        switch (path){
            case "/user":
                List<Users> users = usersBO.getAllUsers();
                request.setAttribute("users", users);
                request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
