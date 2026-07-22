package view;

import dao.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CleanerDashboard extends JFrame {
    private JTable cleanerTable;
    private DefaultTableModel tableModel;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtPhone;
    private JTextField txtDepartment;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnRefresh;

    public CleanerDashboard() {
        setTitle("Cleaner Management Dashboard");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Cleaner Management", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitle, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "First Name", "Last Name", "Phone", "Department"}, 0);
        cleanerTable = new JTable(tableModel);
        loadCleanerData();

        cleanerTable.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = cleanerTable.getSelectedRow();
            if (selectedRow != -1) {
                txtFirstName.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtLastName.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtPhone.setText(tableModel.getValueAt(selectedRow, 3).toString());
                txtDepartment.setText(tableModel.getValueAt(selectedRow, 4).toString());
            }
        });

        add(new JScrollPane(cleanerTable), BorderLayout.CENTER);

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtPhone = new JTextField();
        txtDepartment = new JTextField();

        panelForm.add(new JLabel("First Name:"));
        panelForm.add(txtFirstName);
        panelForm.add(new JLabel("Last Name:"));
        panelForm.add(txtLastName);
        panelForm.add(new JLabel("Phone:"));
        panelForm.add(txtPhone);
        panelForm.add(new JLabel("Department:"));
        panelForm.add(txtDepartment);

        JPanel panelButtons = new JPanel();
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");

        panelButtons.add(btnAdd);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnDelete);
        panelButtons.add(btnRefresh);

        JPanel panelSouth = new JPanel(new BorderLayout());
        panelSouth.add(panelForm, BorderLayout.CENTER);
        panelSouth.add(panelButtons, BorderLayout.SOUTH);
        add(panelSouth, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addCleaner());
        btnUpdate.addActionListener(e -> updateCleaner());
        btnDelete.addActionListener(e -> deleteCleaner());
        btnRefresh.addActionListener(e -> loadCleanerData());
    }

    private void loadCleanerData() {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM cleaners ORDER BY cleaner_id";

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("cleaner_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"),
                        rs.getString("department")
                });
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void addCleaner() {
        String sql = "INSERT INTO cleaners (first_name, last_name, phone, department) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, txtFirstName.getText());
            pstmt.setString(2, txtLastName.getText());
            pstmt.setString(3, txtPhone.getText());
            pstmt.setString(4, txtDepartment.getText());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cleaner added successfully!");
            loadCleanerData();
            clearFields();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateCleaner() {
        int selectedRow = cleanerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a cleaner to update.");
            return;
        }

        int cleanerId = (int) tableModel.getValueAt(selectedRow, 0);
        String sql = "UPDATE cleaners SET first_name = ?, last_name = ?, phone = ?, department = ? WHERE cleaner_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, txtFirstName.getText());
            pstmt.setString(2, txtLastName.getText());
            pstmt.setString(3, txtPhone.getText());
            pstmt.setString(4, txtDepartment.getText());
            pstmt.setInt(5, cleanerId);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cleaner updated successfully!");
            loadCleanerData();
            clearFields();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void deleteCleaner() {
        int selectedRow = cleanerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a cleaner to delete.");
            return;
        }

        int cleanerId = (int) tableModel.getValueAt(selectedRow, 0);
        String sql = "DELETE FROM cleaners WHERE cleaner_id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cleanerId);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cleaner deleted successfully!");
            loadCleanerData();
            clearFields();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPhone.setText("");
        txtDepartment.setText("");
        cleanerTable.clearSelection();
    }
}
