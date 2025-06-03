package com.cjs.service;

import com.cjs.dao.UserDAO;
import com.cjs.model.User;

import java.util.List;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public User getUserById(int userId) throws Exception {
        User user = userDAO.findUserById(userId);
        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    public User getUserByUsername(String username) throws Exception {
        User user = userDAO.findUserByUsername(username);
        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    public List<User> getAllUsers() throws Exception {
        return userDAO.findAllUsers();
    }

    public void updateUser(int userId, String name, String username, String role, String designation,
                           String contact, String email) throws Exception {
        User user = userDAO.findUserById(userId);
        if (user == null) {
            throw new Exception("User not found");
        }

        // Validate police username
        if (role.equals("Police") && !username.toLowerCase().startsWith("plc")) {
            throw new Exception("You're doing something against legal act: Police username must start with 'plc' (e.g., plc001)");
        }

        user.setName(name);
        user.setUsername(username);
        user.setRole(role);
        user.setDesignation(designation);
        user.setContact(contact);
        user.setEmail(email);

        userDAO.updateUser(user);
    }

    public void updateUserPassword(int userId, String newPassword) throws Exception {
        User user = userDAO.findUserById(userId);
        if (user == null) {
            throw new Exception("User not found");
        }

        user.setPasswordHash(hashPassword(newPassword));
        userDAO.updateUser(user);
    }

    public void deleteUser(int userId) throws Exception {
        User user = userDAO.findUserById(userId);
        if (user == null) {
            throw new Exception("User not found");
        }
        userDAO.deleteUser(userId);
    }

    private String hashPassword(String password) {
        // Placeholder for password hashing (use BCrypt in production)
        return "hashed_" + password;
    }
}