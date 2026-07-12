package view;

import controller.SupplierController;
import model.Supplier;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SupplierPanel extends JPanel {
    private SupplierController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtContact, txtPhone, txtEmail, txtAddress;

    public SupplierPanel() {
        controller = new SupplierController();
        setLayout(new BorderLayout());

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
        JButton btnDelete = new JButton("Delete Selected");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);
        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.NORTH);

        // Center Table Area
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Contact", "Phone", "Email", "Address"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Event Listeners
        btnAdd.addActionListener(e -> addSupplier());
        btnDelete.addActionListener(e -> deleteSupplier());

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
