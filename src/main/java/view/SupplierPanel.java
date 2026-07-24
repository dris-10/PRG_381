package view;

import controller.SupplierController;
import model.Supplier;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SupplierPanel extends JFrame {
    private SupplierController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtContact, txtPhone, txtEmail, txtAddress;
    private User currentUser;

    public SupplierPanel() {
        controller = new SupplierController();
        setLayout(new BorderLayout());
        setVisible(true);
        // Top Form Area
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Manage Suppliers"));

        formPanel.add(new JLabel("Company Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Contact Person:"));
        txtContact = new JTextField();
        formPanel.add(txtContact);

        formPanel.add(new JLabel("Phone:"));
        txtPhone = new JTextField();
        formPanel.add(txtPhone);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Address:"));
        txtAddress = new JTextField();
        formPanel.add(txtAddress);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add Supplier");
        JButton btnUpdate = new JButton("Update Selected");
        JButton btnDelete = new JButton("Delete Selected");
        JButton btnBack = new JButton("Back to Dashboard");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);
        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.NORTH);

        // Center Table Area
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Contact", "Phone", "Email", "Address"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Event Listeners
        btnAdd.addActionListener(e -> addSupplier());
        btnUpdate.addActionListener(e -> updateSupplier());
        btnDelete.addActionListener(e -> deleteSupplier());
        btnBack.addActionListener(e -> {
            new DashboardForm(currentUser).setVisible(true);
        });

        // This makes the txt fields populate when you click a row in the table
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                txtName.setText(tableModel.getValueAt(row, 1) != null ? tableModel.getValueAt(row, 1).toString() : "");
                txtContact.setText(tableModel.getValueAt(row, 2) != null ? tableModel.getValueAt(row, 2).toString() : "");
                txtPhone.setText(tableModel.getValueAt(row, 3) != null ? tableModel.getValueAt(row, 3).toString() : "");
                txtEmail.setText(tableModel.getValueAt(row, 4) != null ? tableModel.getValueAt(row, 4).toString() : "");
                txtAddress.setText(tableModel.getValueAt(row, 5) != null ? tableModel.getValueAt(row, 5).toString() : "");
            }
        });

        loadTableData();
    }

    private void addSupplier() {
        String msg = controller.addSupplier(
                txtName.getText(), txtContact.getText(), txtPhone.getText(),
                txtEmail.getText(), txtAddress.getText()
        );
        JOptionPane.showMessageDialog(this, msg);
        if(msg.contains("successfully")) { clearFields(); loadTableData(); }
    }

    private void updateSupplier() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);

            String msg = controller.updateSupplier(
                    id, txtName.getText(), txtContact.getText(),
                    txtPhone.getText(), txtEmail.getText(), txtAddress.getText()
            );

            JOptionPane.showMessageDialog(this, msg);
            if(msg.contains("successfully")) {
                clearFields();
                loadTableData();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a supplier from the table to update.");
        }
    }

    private void deleteSupplier() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String msg = controller.deleteSupplier(id);
            JOptionPane.showMessageDialog(this, msg);
            loadTableData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a supplier to delete.");
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        try {
            List<Supplier> suppliers = controller.getSuppliers();
            for (Supplier s : suppliers) {
                tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getContactPerson(), s.getPhone(), s.getEmail(), s.getAddress()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtName.setText(""); txtContact.setText(""); txtPhone.setText("");
        txtEmail.setText(""); txtAddress.setText("");
    }
}
