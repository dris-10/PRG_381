package controller;

import model.User;
import service.UserService;

public class UserController {

    private UserService userService;

    public UserController() {
        userService = new UserService();
    }

    // Register a new user, returns a status message for the view
    public String register(String fullName, String username, String email,
                            String password, String confirmPassword, String role) {

        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);

        return userService.register(user, password, confirmPassword);
    }

    // Attempt to log in, returns the User on success or null on failure
    public User login(String username, String password) {

        return userService.login(username, password);
    }
}
