package service;

import dao.UserDAO;
import model.User;
import util.PasswordUtil;
import util.Validation;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    // Register a new user
    public String register(User user, String plainPassword, String confirmPassword) {

        if (!Validation.validUser(user)) {
            return "Please fill in all fields correctly.";
        }

        if (!Validation.validPassword(plainPassword)) {
            return "Password must be at least 8 characters long.";
        }

        if (!plainPassword.equals(confirmPassword)) {
            return "Passwords do not match.";
        }

        if (userDAO.usernameExists(user.getUsername())) {
            return "Username is already taken.";
        }

        if (userDAO.emailExists(user.getEmail())) {
            return "Email is already registered.";
        }

        user.setPassword(PasswordUtil.hashPassword(plainPassword));

        return userDAO.insertUser(user)
                ? "Registration successful! You can now log in."
                : "Registration failed. Please try again.";
    }

    // Authenticate a user
    public User login(String username, String password) {

        if (Validation.isEmpty(username) || Validation.isEmpty(password)) {
            return null;
        }

        User user = userDAO.getUserByUsername(username);

        if (user != null && PasswordUtil.verifyPassword(password, user.getPassword())) {
            return user;
        }

        return null;
    }
}
