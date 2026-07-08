package model;

public class Material {

    private int materialId;
    private String materialName;
    private String category;
    private int quantity;
    private int reorderLevel;
    private String unit;
    private int supplierId;


    // Default constructor
    public Material() {

    }


    // Full constructor
    public Material(int materialId, String materialName, String category,
                    int quantity, int reorderLevel,
                    String unit, int supplierId) {

        this.materialId = materialId;
        this.materialName = materialName;
        this.category = category;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.unit = unit;
        this.supplierId = supplierId;
    }


    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }


    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }


    @Override
    public String toString() {
        return materialName + " - Quantity: " + quantity;
    }
}