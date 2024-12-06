package org.exam.final_exam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.exam.final_exam.bo.VacXinBO;
import org.exam.final_exam.entity.VacXin;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "VacXinController", urlPatterns = {"/vacxin", "/vacxin/add", "/vacxin/update", "/vacxin/delete"})
public class VacXinController extends HttpServlet {
    private final VacXinBO vacXinBO;
    public VacXinController() {
        vacXinBO = new VacXinBO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        System.out.println(path);
        path = path.trim();
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        String action;

        switch (path) {
            case "/vacxin":
                List<VacXin> vacXins = vacXinBO.getAllVacXin();
                request.setAttribute("vacXins", vacXins);
                request.getRequestDispatcher("/jsp/vacxin/list.jsp").forward(request, response);
                break;
            case "/vacxin/add":
                action = "add";
                request.setAttribute("action", action);
                request.getRequestDispatcher("/jsp/vacxin/add.jsp").forward(request, response);
                break;
            case "/vacxin/update":
                VacXin vacXin = vacXinBO.getVacXinByMaVacXin(request.getParameter("maVacXin"));
                request.setAttribute("vacXin", vacXin);
                action = "update";
                request.setAttribute("action", action);
                request.getRequestDispatcher("/jsp/vacxin/add.jsp").forward(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        response.setContentType("text/html");

        switch (path) {
            case "/vacxin":
                response.sendRedirect(request.getContextPath() + "/vacxin");
                break;
            case "/vacxin/add":
                VacXin vacXin = VacXin.builder()
                        .maVacXin(request.getParameter("maVacXin"))
                        .tenVacXin(request.getParameter("tenVacXin"))
                        .soMui(Integer.parseInt(request.getParameter("soMui")))
                        .moTa(request.getParameter("moTa"))
                        .giaVacXin(Double.parseDouble(request.getParameter("giaVacXin")))
                        .tenHangSX(request.getParameter("tenHangSX"))
                        .build();
                vacXinBO.addVacXin(vacXin);
                response.sendRedirect(request.getContextPath() + "/vacxin");
                break;
            case "/vacxin/update":
                VacXin vacXinUpdate = VacXin.builder()
                        .maVacXin(request.getParameter("maVacXin"))
                        .tenVacXin(request.getParameter("tenVacXin"))
                        .soMui(Integer.parseInt(request.getParameter("soMui")))
                        .moTa(request.getParameter("moTa"))
                        .giaVacXin(Double.parseDouble(request.getParameter("giaVacXin")))
                        .tenHangSX(request.getParameter("tenHangSX"))
                        .build();
                vacXinBO.updateVacXin(vacXinUpdate);
                response.sendRedirect(request.getContextPath() + "/vacxin");
                break;
            case "/vacxin/delete":
                String MaVacXin = request.getParameter("maVacXin");
                vacXinBO.deleteVacXin(MaVacXin);
                response.sendRedirect(request.getContextPath() + "/vacxin");
                break;
        }
    }
}
