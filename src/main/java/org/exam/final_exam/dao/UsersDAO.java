package org.exam.final_exam.dao;


import org.exam.final_exam.entity.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UsersDAO {

    private GenericDAO genericDAO;

    public UsersDAO() {
        genericDAO = new GenericDAO();
    }

    public List<Users> getAllUsers() {
        String sql = "SELECT * FROM users";
        return genericDAO.executeQuery(sql, rs -> Users.builder()
                .id(rs.getInt("id"))
                .fullName(rs.getString("fullname"))
                .email(rs.getString("email"))
                .passWord(rs.getString("password"))
                .phoneNumber(rs.getString("phonenumber"))
                .address(rs.getString("address"))
                .role(rs.getString("role"))
                .createAt(rs.getDate("createdat"))
                .build());
    }

    public Users getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return genericDAO.find(sql, rs -> Users.builder()
                .id(rs.getInt("id"))
                .fullName(rs.getString("fullname"))
                .email(rs.getString("email"))
                .passWord(rs.getString("password"))
                .phoneNumber(rs.getString("phonenumber"))
                .address(rs.getString("address"))
                .role(rs.getString("role"))
                .createAt(rs.getDate("createdat"))
                .build(), id);
    }

    public Users findUserSignIn(String email,String fullname,String phone,String address) {
        String sql = "SELECT * FROM users WHERE email = ? and fullname = ? and phonenumber = ? and address = ? LIMIT 1";
        return genericDAO.find(sql, rs -> Users.builder()
                .id(rs.getInt("id"))
                .fullName(rs.getString("fullname"))
                .email(rs.getString("email"))
                .passWord(rs.getString("password"))
                .phoneNumber(rs.getString("phonenumber"))
                .address(rs.getString("address"))
                .role(rs.getString("role"))
                .createAt(rs.getDate("createdat"))
                .build(), email,fullname,phone,address);
    }


    public Users getUserByEmailPassword(String email, String password) {
        Users user = new Users();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encodedPassword = encoder.encode(password);
        String sql = "SELECT * FROM users WHERE email = ? LIMIT 1";

         user = genericDAO.find(sql, rs -> Users.builder()
                .id(rs.getInt("id"))
                .fullName(rs.getString("fullname"))
                .email(rs.getString("email"))
                .passWord(rs.getString("password"))
                .phoneNumber(rs.getString("phonenumber"))
                .address(rs.getString("address"))
                .role(rs.getString("role"))
                .createAt(rs.getDate("createdat"))
                .build(),email);

        if (user != null && encoder.matches(password, user.getPassWord())) {
            return user; // Trả về đối tượng Users nếu mật khẩu khớp
        }

        // Nếu không tìm thấy hoặc mật khẩu sai, trả về null
        return null;
    }
    public int addUser(Users user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassWord());
        String sql = "INSERT INTO users(fullname,email,password,phonenumber,address,role,createdat) VALUES(?, ?, ?, ?, ?, ?, ?)";

        java.sql.Date sqlDate = new java.sql.Date(user.getCreateAt().getTime());
        return genericDAO.executeUpdate(sql,user.getFullName(),user.getEmail(),encodedPassword,user.getPhoneNumber(),user.getAddress(),user.getRole(),sqlDate);
    }

    public int updateUser(Users user) {
        String sql = "UPDATE users SET fullname = ?, email = ?, phonenumber = ?, address = ?, role = ? WHERE id = ?";
        return genericDAO.executeUpdate(sql, user.getFullName(),user.getEmail(),user.getPhoneNumber(),user.getAddress(),user.getRole(),user.getId());
    }

    public int deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return genericDAO.executeUpdate(sql, id);
    }

    public boolean isExistUser(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return genericDAO.isExist(sql, id);
    }



}
