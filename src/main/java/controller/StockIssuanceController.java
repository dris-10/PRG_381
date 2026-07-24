package controller;

import model.StockIssuance;
import service.StockIssuanceService;

import java.util.List;

public class StockIssuanceController {

    private StockIssuanceService stockIssuanceService;

    public StockIssuanceController() {

        stockIssuanceService = new StockIssuanceService();

    }

    // Issue Stock
    public boolean issueStock(StockIssuance issuance) {

        return stockIssuanceService.issueStock(issuance);

    }

    // View All Issuances
    public List<StockIssuance> getAllIssuances() {

        return stockIssuanceService.getAllIssuances();

    }

    // Find Issuance By ID
    public StockIssuance findIssuance(int issuanceId) {

        return stockIssuanceService.findIssuance(issuanceId);

    }

    // Delete Issuance
    public boolean deleteIssuance(int issuanceId) {

        return stockIssuanceService.deleteIssuance(issuanceId);

    }

    // View Issuances For A Cleaner
    public List<StockIssuance> getCleanerIssuances(int cleanerId) {

        return stockIssuanceService.getCleanerIssuances(cleanerId);

    }

}