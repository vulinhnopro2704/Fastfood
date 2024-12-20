package org.exam.final_exam.dao;

import java.util.List;

import org.exam.final_exam.entity.Foods;
public class FoodsDAO {
    private GenericDAO genericDAO;

    public FoodsDAO() {genericDAO = new GenericDAO();}

    public List<Foods> getAllFoods() {
        String sql = "SELECT * FROM foods";
        return genericDAO.executeQuery(sql, rs -> Foods.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getLong("price"))
                .imageLink(rs.getString("imageLink"))
                .categoryId(rs.getInt("categoryId"))
                .createAt(rs.getDate("createdat"))
                .build());
    }

    public List<Foods> getAllFoodsBySearch(String search) {
        String sql = "SELECT * FROM foods where name like ?";
        return genericDAO.executeQuery(sql, rs -> Foods.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getLong("price"))
                .imageLink(rs.getString("imageLink"))
                .categoryId(rs.getInt("categoryId"))
                .createAt(rs.getDate("createdat"))
                .build(), search);
    }

    public Foods getFoodById(int id) {
        String sql = "SELECT * FROM foods WHERE id = ?";
        return genericDAO.find(sql, rs -> Foods.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getLong("price"))
                .imageLink(rs.getString("imageLink"))
                .categoryId(rs.getInt("categoryId"))
                .createAt(rs.getDate("createdat"))
                .build(), id);
    }

    public int addFood(Foods food) {
        String sql = "INSERT INTO foods(name, description, price, imageLink, categoryId,createdat) VALUES(?, ?, ?, ?, ?, ?)";
        return genericDAO.executeUpdate(sql, food.getName(), food.getDescription(), food.getPrice(), food.getImageLink(), food.getCategoryId(), food.getCreateAt());
    }

    public void updateFood(Foods food) {
        String sql = "UPDATE foods SET name = ?, description = ?, price = ?, imageLink = ?, categoryId = ? WHERE id = ?";
        System.out.println(food.getId());
        System.out.println(food.getName());
        System.out.println(food.getDescription());
        System.out.println(food.getPrice());
        System.out.println(food.getImageLink());
        System.out.println(food.getCategoryId());
        genericDAO.executeUpdate(sql, food.getName(), food.getDescription(), food.getPrice(), food.getImageLink(), food.getCategoryId(), food.getId());
    }

    public int deleteFood(int id) {
        String sql = "DELETE FROM foods WHERE id = ?";
        return genericDAO.executeUpdate(sql, id);
    }

    public boolean isExistFood(int id) {
        String sql = "SELECT * FROM foods WHERE id = ?";
        return genericDAO.isExist(sql, id);
    }

    public void saveFood(Foods food) {
        if (isExistFood(food.getId())) {
            updateFood(food);
        } else {
            addFood(food);
        }
    }
}
