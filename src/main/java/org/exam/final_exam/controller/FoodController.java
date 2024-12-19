package org.exam.final_exam.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
import org.exam.final_exam.bo.FoodsBO;
import org.exam.final_exam.entity.Foods;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

@WebServlet(name = "FoodController", urlPatterns = {"/settings", "/settings/user", "/food", "/food/update"})
public class FoodController extends HttpServlet {
    private final FoodsBO foodsBO;

    public FoodController() {
        foodsBO = new FoodsBO();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");

        switch (path) {
            case "/settings":
                handleGetAllFoods(request, response);
                break;
            case "/food":
                handleGetFoodById(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        switch (path) {
            case "/food":
                handleAddFood(request, response);
                break;
            case "/food/update":
                System.out.println("update food");
                handleUpdateFood(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    // GET handlers
    private void handleGetAllFoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Foods> listFoods = foodsBO.getAllFoods();
        request.setAttribute("listFoods", listFoods);
        request.getRequestDispatcher("/jsp/settings/food-manager.jsp").forward(request, response);
    }

    private void handleGetFoodById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (id == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Food ID is missing.");
            return;
        }

        Foods food = foodsBO.getFoodById(Integer.parseInt(id));
        if (food == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Food not found.");
            return;
        }

        Gson gson = new Gson();
        String json = gson.toJson(food);
        response.getWriter().write(json);
    }

    // POST handlers
    private void handleAddFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Foods food = processFoodForm(request);
        if (food != null) {
            foodsBO.saveFood(food);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to add food. Check input data.");
        }
    }

    private void handleUpdateFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Foods food = processFoodForm(request);
        foodsBO.updateFood(food);
        System.out.println("update food success");
        response.sendRedirect(request.getContextPath() + "/settings");
    }

    // Helper method to process the form (used for both add and update)
    private Foods processFoodForm(HttpServletRequest request) throws ServletException, IOException {
        if (!JakartaServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload<DiskFileItem, DiskFileItemFactory> upload = new JakartaServletFileUpload<>(factory);
        String foodId = null;
        String name = null;
        String description = null;
        Long price = 0L;
        String imageLink = null;
        int categoryId = 0;

        try {
            List<DiskFileItem> items = upload.parseRequest(request);

            for (DiskFileItem item : items) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString(StandardCharsets.UTF_8);

                    switch (fieldName) {
                        case "id":
                            if (fieldValue != null && !fieldValue.trim().isEmpty() && !fieldValue.equals("undefined")) {
                                foodId = fieldValue;
                            }
                            break;
                        case "name":
                            name = fieldValue;
                            break;
                        case "description":
                            description = fieldValue;
                            break;
                        case "price":
                            price = Long.parseLong(fieldValue);
                            break;
                        case "categoryId":
                            categoryId = Integer.parseInt(fieldValue);
                            break;
                        default:
                            break;
                    }
                } else {
                    if (item.getSize() > 0) {
                        String fileName = new File(item.getName()).getName();
                        File uploadedFileBuildPath = getFile(fileName, true);
                        File uploadedFile = getFile(fileName, false);

                        item.write(uploadedFile.toPath());
                        Files.copy(uploadedFile.toPath(), uploadedFileBuildPath.toPath(), StandardCopyOption.REPLACE_EXISTING);

                        imageLink = uploadedFileBuildPath.getAbsolutePath()
                                .replace(this.getServletConfig().getServletContext().getRealPath("/"), "")
                                .replace("\\", "/");
                    }
                }
            }

            if (name == null || description == null || price <= 0 || categoryId <= 0) {
                throw new IllegalArgumentException("Missing or invalid input fields");
            }
            if (imageLink == null && (foodId == null || foodId.trim().isEmpty())) {
                imageLink = "/assets/default/food.jpeg";
            }
            else if (imageLink == null && foodId != null && !foodId.trim().isEmpty()) {
                Foods oldFood = foodsBO.getFoodById(Integer.parseInt(foodId));
                imageLink = oldFood.getImageLink();
            }
            return Foods.builder()
                    .id(foodId == null || foodId.trim().isEmpty() ? 0 : Integer.parseInt(foodId))
                    .name(name)
                    .description(description)
                    .price(price)
                    .imageLink(imageLink)
                    .categoryId(categoryId)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error processing food form", e);
        }
    }

    // Helper method to get file path
    private File getFile(String fileName, Boolean isBuildPath) {
        ServletContext servletContext = this.getServletConfig().getServletContext();

        String baseDir = isBuildPath
                ? servletContext.getRealPath("/") + "/assets/uploads/images/"
                : servletContext.getRealPath("/").replace("target\\final_exam-1.0-SNAPSHOT", "src/main/webapp") + "/assets/uploads/images/";

        baseDir = baseDir.replace("//", "/");

        String timestampDir = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date());
        String uploadDir = baseDir + timestampDir + "/";

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        return new File(uploadDir + uniqueFileName);
    }
}
