package org.exam.final_exam.dao;


import org.exam.final_exam.entity.Categories;

import java.util.List;
public class CategoriesDAO {

    private GenericDAO genericDAO;
    public CategoriesDAO() {
        genericDAO = new GenericDAO();
    }

    public List<Categories> getAllCategories() {
        String sql = "SELECT * FROM categories";
        return genericDAO.executeQuery(sql, rs -> Categories.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .build());
    }

    public Categories getCategoriesById(int id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        return genericDAO.find(sql, rs -> Categories.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .build(), id);
    }

    public int addCategories(Categories categories) {
        String sql = "INSERT INTO categories(name, description) VALUES(?, ?)";
        return genericDAO.executeUpdate(sql, categories.getName(), categories.getDescription());
    }

    public int updateCategories(Categories categories) {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE id = ?";
        return genericDAO.executeUpdate(sql, categories.getName(), categories.getDescription(), categories.getId());
    }

    public int deleteCategories(int id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        return genericDAO.executeUpdate(sql, id);
    }

    public boolean isExistCategories(int id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        return genericDAO.isExist(sql, id);
    }
}
