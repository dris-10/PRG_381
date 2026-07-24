package controller;
import model.Supplier;
import service.SupplierService;
import java.sql.SQLException;
import java.util.List;

public class SupplierController {
    private SupplierService service;

    public SupplierController() {
        this.service = new SupplierService();
    }

    public String addSupplier(String name, String contact, String phone, String email, String address) {
        try {
            Supplier s = new Supplier(0, name, contact, phone, email, address);
            service.addSupplier(s);
            return "Supplier added successfully.";
        } catch (IllegalArgumentException | SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    public List<Supplier> getSuppliers() throws SQLException {
        return service.getAllSuppliers();
    }

    public String updateSupplier(int id, String name, String contact, String phone, String email, String address) {
        try {
            Supplier s = new Supplier(id, name, contact, phone, email, address);
            service.updateSupplier(s);
            return "Supplier updated successfully.";
        } catch (IllegalArgumentException | SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String deleteSupplier(int id) {
        try {
            service.deleteSupplier(id);
            return "Supplier deleted successfully.";
        } catch (SQLException e) {
            return "Error deleting supplier: " + e.getMessage();
        }
    }

    public int getSupplierCount() {
        return service.getSupplierCount();
    }
}
