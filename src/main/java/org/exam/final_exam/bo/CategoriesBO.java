package org.exam.final_exam.bo;


import org.exam.final_exam.dao.CategoriesDAO;
import org.exam.final_exam.entity.Categories;

import java.util.List;

public class CategoriesBO {
    private CategoriesDAO categoriesDAO;
    public CategoriesBO() { categoriesDAO = new CategoriesDAO();}

    public List<Categories> getAllCategories() {
        return categoriesDAO.getAllCategories();
    }

    public Categories getCategoriesById(int id) {
        return categoriesDAO.getCategoriesById(id);
    }

    public int addCategories(String name, String description) {
        Categories categories = new Categories(name, description);
        return categoriesDAO.addCategories(categories);
    }
    public int updateCategories(Categories categories) {
        return categoriesDAO.updateCategories(categories);
    }
    public int deleteCategories(int id) {
        return categoriesDAO.deleteCategories(id);
    }

}
