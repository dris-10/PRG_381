package view;
//importing
import controller.UserController;
import model.User;
import util.Session;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    // Text fields
    private JTextField usernameField;
    private JPasswordField passwordField;

    // Controller
    private UserController controller;

    public LoginForm() {

        controller = new UserController();

        setupFrame();
        createUI();
    }

    private void setupFrame() {

        setTitle("Cleaning Inventory System - Login");

        setSize(350, 220);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
    }

    private void createUI() {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> openRegisterForm());

        getRootPane().setDefaultButton(loginButton);

        setVisible(true);
    }

    private void login() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = controller.login(username, password);

        if (user != null) {

            Session.setCurrentUser(user);
            dispose();
            new DashboardForm(user);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }//end of login

    private void openRegisterForm() {

        dispose();
        new RegisterForm();
    }

}
