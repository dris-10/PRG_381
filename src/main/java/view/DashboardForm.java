package view;
//importing
import model.User;
import util.Session;

import javax.swing.*;
import java.awt.*;

public class DashboardForm extends JFrame {

    public DashboardForm() {

        setupFrame();
        createUI();
    }

    private void setupFrame() {

        setTitle("Cleaning Inventory System - Dashboard");

        setSize(500, 350);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createUI() {

        setLayout(new BorderLayout(10, 10));

        User user = Session.getCurrentUser();

        String welcomeText = user != null
                ? "Welcome, " + user.getFullName() + " (" + user.getRole() + ")"
                : "Welcome";

        JLabel welcomeLabel = new JLabel(welcomeText, SwingConstants.CENTER);
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(Font.BOLD, 16f));

        add(welcomeLabel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton materialsButton = new JButton("Manage Materials");
        JButton suppliersButton = new JButton("Manage Suppliers");
        JButton cleanersButton = new JButton("Manage Cleaners");
        JButton stockButton = new JButton("Stock Issuance");
        JButton logoutButton = new JButton("Logout");

        menuPanel.add(materialsButton);
        menuPanel.add(suppliersButton);
        menuPanel.add(cleanersButton);
        menuPanel.add(stockButton);
        menuPanel.add(logoutButton);

        add(menuPanel, BorderLayout.CENTER);

        materialsButton.addActionListener(e -> new MaterialForm());
        suppliersButton.addActionListener(e -> openSuppliers());
        cleanersButton.addActionListener(e -> notImplemented("Cleaners Management"));
        stockButton.addActionListener(e -> notImplemented("Stock Issuance"));
        logoutButton.addActionListener(e -> logout());

        setVisible(true);
    }

    private void openSuppliers() {

        JFrame supplierFrame = new JFrame("Supplier Management");

        supplierFrame.setSize(800, 600);
        supplierFrame.setLocationRelativeTo(this);
        supplierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        supplierFrame.add(new SupplierPanel());

        supplierFrame.setVisible(true);
    }

    private void notImplemented(String moduleName) {

        JOptionPane.showMessageDialog(
                this,
                moduleName + " module is not yet implemented.",
                "Coming Soon",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void logout() {

        Session.logout();
        dispose();
        new LoginForm();
    }

}
