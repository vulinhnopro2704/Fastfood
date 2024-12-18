package org.exam.final_exam.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.exam.final_exam.enums.Status;

import java.io.IOException;

@WebServlet(name = "EnumController", urlPatterns = {"/enum/status"})
public class EnumController extends HttpServlet {
    final private Gson gson;

    public EnumController() {
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (path) {
            case "/enum/status":
                Status[] statuses = Status.values();
                String jsonResponse = gson.toJson(statuses);
                response.getWriter().write(jsonResponse);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Not found\"}");
                break;
        }
    }
}