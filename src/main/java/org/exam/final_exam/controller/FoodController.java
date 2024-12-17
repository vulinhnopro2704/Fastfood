package org.exam.final_exam.controller;

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

@WebServlet(name = "FoodController", urlPatterns = {"/settings", "/settings/user", "/food"})
public class FoodController extends HttpServlet {
    private final FoodsBO foodsBO;

    public FoodController() {
        foodsBO = new FoodsBO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");

        switch (path) {
            case "/settings":

                List<Foods> listFoods = foodsBO.getAllFoods();
                request.setAttribute("listFoods", listFoods);

                request.getRequestDispatcher("/jsp/settings/food-manager.jsp").forward(request, response);
            default:
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        System.out.println("FoodController - request.getContextPath() : " + request.getContextPath());
        System.out.println("FoodController - path : " + path);

        switch (path) {
            case "/food":
                System.out.println("SAVE FOOD");
                handleAddFood(request, response);
                break;
        }
    }

    public void handleAddFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ensure the request is multipart
        if (!JakartaServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }

        // Use the builder to create a DiskFileItemFactory
        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();

        // Create a new file upload handler
        JakartaServletFileUpload<DiskFileItem, DiskFileItemFactory> upload = new JakartaServletFileUpload<>(factory);

        try {
            // Parse the request
            List<DiskFileItem> items = upload.parseRequest(request);

            String name = null;
            String description = null;
            double price = 0;
            String imageLink = null;
            int categoryId = 0;

            for (DiskFileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString(StandardCharsets.UTF_8); // Specify UTF-8 for proper encoding
                    switch (fieldName) {
                        case "name":
                            name = fieldValue;
                            System.out.println("name: " + name);
                            break;
                        case "description":
                            description = fieldValue;
                            System.out.println("description: " + description);
                            break;
                        case "price":
                            price = Double.parseDouble(fieldValue);
                            System.out.println("price: " + price);
                            break;
                        case "categoryId":
                            categoryId = Integer.parseInt(fieldValue);
                            System.out.println("categoryId: " + categoryId);
                            break;
                        default:
                            break;
                    }
                } else {
                    // Process form file field (input type="file")
                    if (item.getSize() > 0) { // Check if a file is uploaded
                        String fileName = new File(item.getName()).getName();
                        File uploadedFileBuildPath = getFile(fileName, true);
                        File uploadedFile = getFile(fileName, false);
                        item.write(uploadedFile.toPath()); // Ghi lần đầu, file tạm bị xóa sau lệnh này
                        Files.copy(uploadedFile.toPath(), uploadedFileBuildPath.toPath(), StandardCopyOption.REPLACE_EXISTING); // Sao chép sang vị trí thứ hai

                        // Sử dụng '/' để đảm bảo tương thích với hệ điều hành
                        // Thay thế dấu "\" bằng "/"

                        // Cập nhật image link với đường dẫn tương đối
                        imageLink = uploadedFileBuildPath.getAbsolutePath()
                                .replace(this.getServletConfig().getServletContext().getRealPath("/"), "")
                                .replace("\\", "/");

                        // Log đường dẫn ảnh
                        System.out.println("Relative image link: " + imageLink);
                    }
                }
            }

            // Validate required fields
            if (name == null || description == null || imageLink == null || price <= 0 || categoryId <= 0) {
                throw new IllegalArgumentException("Missing or invalid input fields");
            }

            // Create a new food item
            Foods food = new Foods(name, description, price, imageLink, categoryId);

            // Save the food item using the service
            foodsBO.saveFood(food);

            // Redirect to a success page
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error processing file upload", e);
        }
    }

    private File getFile(String fileName, Boolean isBuildPath) {
        ServletContext servletContext = this.getServletConfig().getServletContext();

        String baseDir;
        if (isBuildPath) {
            // Base directory in webapp/uploads/images
            baseDir = servletContext.getRealPath("/") + "/assets/uploads/images/";
        } else {
            baseDir = servletContext.getRealPath("/").replace("target\\final_exam-1.0-SNAPSHOT", "src/main/webapp") + "/assets/uploads/images/";
        }

        // Replace any occurrences of '//' with '/'
        baseDir = baseDir.replace("//", "/");

        // Generate directory structure based on current timestamp (e.g., "id/food")
        String timestampDir = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date());
        String uploadDir = baseDir + timestampDir + "/";

        // Ensure directories exist
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // Generate a unique file name using timestamp
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

        // Full file path
        String filePath = uploadDir + uniqueFileName;
        return new File(filePath);
    }
}
