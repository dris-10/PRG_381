package view;
//importing
import controller.UserController;

import javax.swing.*;
import java.awt.*;

public class RegisterForm extends JFrame {

    // Fields
    private JTextField fullNameField;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> roleBox;

    // Controller
    private UserController controller;

    public RegisterForm() {

        controller = new UserController();

        setupFrame();
        createUI();
    }

    private void setupFrame() {

        setTitle("Cleaning Inventory System - Register");

        setSize(400, 380);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setResizable(false);
    }

    private void createUI() {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        fullNameField = new JTextField(15);
        usernameField = new JTextField(15);
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);
        confirmPasswordField = new JPasswordField(15);
        roleBox = new JComboBox<>(new String[]{"Storekeeper", "Supervisor"});

        addField(gbc, 0, "Full Name:", fullNameField);
        addField(gbc, 1, "Username:", usernameField);
        addField(gbc, 2, "Email:", emailField);
        addField(gbc, 3, "Password:", passwordField);
        addField(gbc, 4, "Confirm Password:", confirmPasswordField);
        addField(gbc, 5, "Role:", roleBox);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        registerButton.addActionListener(e -> register());
        backButton.addActionListener(e -> backToLogin());

        setVisible(true);
    }

    private void addField(GridBagConstraints gbc, int row, String label, JComponent field) {

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        add(field, gbc);
    }

    private void register() {

        String fullName = fullNameField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String role = (String) roleBox.getSelectedItem();

        String message = controller.register(
                fullName, username, email, password, confirmPassword, role
        );

        JOptionPane.showMessageDialog(this, message);

        if (message.contains("successful")) {
            backToLogin();
        }

    }//end of register

    private void backToLogin() {

        dispose();
        new LoginForm();
    }

}
