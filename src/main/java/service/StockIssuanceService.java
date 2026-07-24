package service;

import dao.StockIssuanceDAO;
import model.StockIssuance;

import java.util.List;

public class StockIssuanceService {

    private StockIssuanceDAO stockIssuanceDAO;

    public StockIssuanceService() {

        stockIssuanceDAO = new StockIssuanceDAO();

    }

    // Issue Stock
    public boolean issueStock(StockIssuance issuance) {

        if (!validStockIssuance(issuance)) {

            return false;

        }

        return stockIssuanceDAO.issueStock(issuance);

    }

    // Get All Issuances
    public List<StockIssuance> getAllIssuances() {

        return stockIssuanceDAO.getAllIssuances();

    }

    // Find Issuance By ID
    public StockIssuance findIssuance(int issuanceId) {

        if (issuanceId <= 0) {

            return null;

        }

        return stockIssuanceDAO.findIssuance(issuanceId);

    }

    // Delete Issuance
    public boolean deleteIssuance(int issuanceId) {

        if (issuanceId <= 0) {

            return false;

        }

        return stockIssuanceDAO.deleteIssuance(issuanceId);

    }

    // Get Issuances For One Cleaner
    public List<StockIssuance> getCleanerIssuances(int cleanerId) {

        if (cleanerId <= 0) {

            return null;

        }

        return stockIssuanceDAO.getCleanerIssuances(cleanerId);

    }

    // Validation
    private boolean validStockIssuance(StockIssuance issuance) {

        if (issuance == null) {

            return false;

        }

        if (issuance.getCleanerId() <= 0) {

            return false;

        }

        if (issuance.getMaterialId() <= 0) {

            return false;

        }

        if (issuance.getIssuedBy() <= 0) {

            return false;

        }

        if (issuance.getQuantityIssued() <= 0) {

            return false;

        }

        return true;

    }

}