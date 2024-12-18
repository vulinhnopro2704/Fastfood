package org.exam.final_exam.bo;

import org.exam.final_exam.dao.FoodsDAO;
import org.exam.final_exam.entity.Foods;

import java.util.List;

public class FoodsBO {
    private FoodsDAO foodsDAO;
    public FoodsBO(){
        foodsDAO = new FoodsDAO();
    }

    public List<Foods> getAllFoods(){
        return foodsDAO.getAllFoods();
    }

    public List<Foods> getAllFoodsBySearch(String search){
        return foodsDAO.getAllFoodsBySearch("%" + search + "%");
    }

    public Foods getFoodById(int id){
        return foodsDAO.getFoodById(id);
    }

    public int addFood(String name, String description, double price, String imageLink, int categoryId){
        Foods food = new Foods(name, description, price, imageLink, categoryId);
        return foodsDAO.addFood(food);
    }
    public void updateFood(Foods food){
        foodsDAO.updateFood(food);
    }
    public int deleteFood(int id){
        return foodsDAO.deleteFood(id);
    }

    public void saveFood(Foods food) {
        foodsDAO.saveFood(food);
    }
}
