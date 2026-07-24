package model;

import java.sql.Timestamp;

public class StockIssuance {

    // Variables
    private int issuanceId;
    private int cleanerId;
    private int materialId;
    private int quantityIssued;
    private int issuedBy;
    private Timestamp issueDate;

    // Default Constructor
    public StockIssuance() {

    }

    // Full Constructor
    public StockIssuance(int issuanceId, int cleanerId, int materialId, int quantityIssued, int issuedBy, Timestamp issueDate) {

        this.issuanceId = issuanceId;
        this.cleanerId = cleanerId;
        this.materialId = materialId;
        this.quantityIssued = quantityIssued;
        this.issuedBy = issuedBy;
        this.issueDate = issueDate;
    }

    // Getters and Setters

    public int getIssuanceId() {
        return issuanceId;
    }

    public void setIssuanceId(int issuanceId) {
        this.issuanceId = issuanceId;
    }

    public int getCleanerId() {
        return cleanerId;
    }

    public void setCleanerId(int cleanerId) {
        this.cleanerId = cleanerId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getQuantityIssued() {
        return quantityIssued;
    }

    public void setQuantityIssued(int quantityIssued) {
        this.quantityIssued = quantityIssued;
    }

    public int getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(int issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Timestamp getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Issue #" + issuanceId+ " | Cleaner: " + cleanerId+ " | Material: " + materialId+ " | Quantity: " + quantityIssued+ " | Date: " + issueDate;
    }

}