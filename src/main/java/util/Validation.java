package util;

import model.Material;
import model.User;


public class Validation {

    // General Validation
    // Checks if a text field is empty
    public static boolean isEmpty(String value) {

        return value == null || value.trim().isEmpty();
    }

    // Checks if ID is valid
    public static boolean validId(int id) {

        return id > 0;

    }

    // Material Validation
    // Material name:
    // Required
    // Maximum 100 characters
    public static boolean validMaterialName(String name) {

        return !isEmpty(name)
                && name.length() <= 100;
    }

    // Category:
    // Optional
    // Maximum 50 characters
    public static boolean validCategory(String category) {

        return category == null
                || category.length() <= 50;
    }

    // Quantity cannot be negative
    public static boolean validQuantity(int quantity) {

        return quantity >= 0;
    }

    // Reorder level cannot be negative
    public static boolean validReorderLevel(int reorderLevel) {

        return reorderLevel >= 0;
    }

    // Unit:
    // Required
    // Maximum 20 characters
    public static boolean validUnit(String unit) {

        return !isEmpty(unit)
                && unit.length() <= 20;

    }

    // Supplier ID validation
    public static boolean validSupplierId(int supplierId) {

        return supplierId > 0;
    }

    // CRUD Operation Validation
    // Used before adding/updating a material
    public static boolean validMaterial(Material material) {

        return material != null
                && validMaterialName(
                material.getMaterialName()
        )
                && validCategory(
                material.getCategory()
        )
                && validQuantity(
                material.getQuantity()
        )
                && validReorderLevel(
                material.getReorderLevel()
        )
                && validUnit(
                material.getUnit()
        )
                && validSupplierId(
                material.getSupplierId()
        );
    }

    // Used for Find By ID
    public static boolean validFindId(int id) {

        return validId(id);
    }

    // Used for Delete
    public static boolean validDeleteId(int id) {

        return validId(id);
    }

    // Inventory Validation
    // Checks if material needs restocking
    public static boolean isLowStock(Material material) {

        return material != null

                && material.getQuantity()
                <= material.getReorderLevel();

    }

    // Returns stock status message
    public static String getStockStatus(Material material) {

        if(isLowStock(material)) {

            return "LOW STOCK";
        }

        return "Stock Available";
    }

    // User Validation
    // Full name:
    // Required
    // Maximum 100 characters
    public static boolean validFullName(String fullName) {

        return !isEmpty(fullName)
                && fullName.length() <= 100;
    }

    // Username:
    // Required
    // Maximum 50 characters
    public static boolean validUsername(String username) {

        return !isEmpty(username)
                && username.length() <= 50;
    }

    // Email:
    // Required
    // Maximum 50 characters
    // Basic format check
    public static boolean validEmail(String email) {

        return !isEmpty(email)
                && email.length() <= 50
                && email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    // Password:
    // Required
    // Minimum 8 characters
    public static boolean validPassword(String password) {

        return password != null
                && password.length() >= 8;
    }

    // Role:
    // Must match one of the allowed roles
    public static boolean validRole(String role) {

        return "Storekeeper".equals(role)
                || "Supervisor".equals(role);
    }

    // Used before registering a new user
    public static boolean validUser(User user) {

        return user != null
                && validFullName(user.getFullName())
                && validUsername(user.getUsername())
                && validEmail(user.getEmail())
                && validRole(user.getRole());
    }

}