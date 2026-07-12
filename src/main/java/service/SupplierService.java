package service;
import dao.SupplierDAO;
import model.Supplier;
import java.sql.SQLException;
import java.util.List;

public class SupplierService {
    private SupplierDAO supplierDAO;

    public SupplierService() {
        this.supplierDAO = new SupplierDAO();
    }

    public void addSupplier(Supplier supplier) throws IllegalArgumentException, SQLException {
        validateSupplierParams(supplier);
        supplierDAO.addSupplier(supplier);
    }

    public List<Supplier> getAllSuppliers() throws SQLException {
        return supplierDAO.getAllSuppliers();
    }

    public void updateSupplier(Supplier supplier) throws IllegalArgumentException, SQLException {
        validateSupplierParams(supplier);
        supplierDAO.updateSupplier(supplier);
    }

    public void deleteSupplier(int id) throws SQLException {
        supplierDAO.deleteSupplier(id);
    }

    //validation and login
    private void validateSupplierParams(Supplier supplier) {
        if (supplier.getName() == null || supplier.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier name cannot be empty.");
        }
        if (supplier.getEmail() != null && !supplier.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (supplier.getPhone() == null || supplier.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required.");
        }
    }
}
