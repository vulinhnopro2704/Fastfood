package org.exam.final_exam.bo;

import org.exam.final_exam.dao.UsersDAO;
import org.exam.final_exam.entity.Users;

import java.util.List;

public class UsersBO {
    private UsersDAO usersDAO;

    public UsersBO(){ usersDAO = new UsersDAO(); }

    public List<Users> getAllUsers() {
        return usersDAO.getAllUsers();
    }
    public Users getUserById(int id) {
        return usersDAO.getUserById(id);
    }
    public Users getUserEmailPassword(String email, String password) {
        return usersDAO.getUserByEmailPassword(email, password);
    }
    public int addUser(String fullname, String email, String password, String phonenumber, String address, String role) {
        Users user = new Users(fullname, email, password, phonenumber, address, role);
        return usersDAO.addUser(user);
    }

    public Users findUserSignIn(String email, String fullname,String phone,String address) {
        return usersDAO.findUserSignIn(email, fullname, phone, address);
    }

    public int updateUser(Users user) {
        return usersDAO.updateUser(user);
    }
    public int deleteUser(int id) {
        return usersDAO.deleteUser(id);
    }

}
