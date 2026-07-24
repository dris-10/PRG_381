package dao;

import model.StockIssuance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockIssuanceDAO {

    // Issue Stock
    public boolean issueStock(StockIssuance issuance) {

        String getMaterialSql = """
                SELECT quantity
                FROM materials
                WHERE material_id = ?
                """;

        String updateMaterialSql = """
                UPDATE materials
                SET quantity = quantity - ?
                WHERE material_id = ?
                """;

        String insertIssuanceSql = """
                INSERT INTO stock_issuance
                (cleaner_id, material_id, quantity_issued, issued_by)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection()) {

            connection.setAutoCommit(false);

            try {

                // Check available stock
                PreparedStatement checkStatement =connection.prepareStatement(getMaterialSql);

                checkStatement.setInt(1,issuance.getMaterialId()
                );

                ResultSet resultSet =checkStatement.executeQuery();

                if (!resultSet.next()) {

                    connection.rollback();

                    return false;

                }

                int currentQuantity =resultSet.getInt("quantity");

                if (currentQuantity < issuance.getQuantityIssued()) {

                    connection.rollback();

                    return false;

                }

                // Deduct stock
                PreparedStatement updateStatement =connection.prepareStatement(updateMaterialSql);

                updateStatement.setInt(1,issuance.getQuantityIssued()
                );

                updateStatement.setInt(2,issuance.getMaterialId()
                );

                updateStatement.executeUpdate();

                // Record issuance
                PreparedStatement insertStatement =connection.prepareStatement(insertIssuanceSql);

                insertStatement.setInt(1,issuance.getCleanerId()
                );

                insertStatement.setInt(2,issuance.getMaterialId()
                );

                insertStatement.setInt(3,issuance.getQuantityIssued()
                );

                insertStatement.setInt(4,issuance.getIssuedBy()
                );

                insertStatement.executeUpdate();

                connection.commit();

                return true;

            } catch (SQLException e) {

                connection.rollback();

                e.printStackTrace();

                return false;

            }

        } catch (SQLException e) {

            e.printStackTrace();

            return false;

        }

    }

    // Get All Issuances
    public List<StockIssuance> getAllIssuances() {

        List<StockIssuance> issuances = new ArrayList<>();

        String sql = """
                SELECT *
                FROM stock_issuance
                ORDER BY issue_date DESC
                """;

        try (Connection connection = DBConnection.getConnection();

             Statement statement = connection.createStatement();

             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                StockIssuance issuance = new StockIssuance();

                issuance.setIssuanceId(resultSet.getInt("issuance_id"));

                issuance.setCleanerId(resultSet.getInt("cleaner_id"));

                issuance.setMaterialId(resultSet.getInt("material_id"));

                issuance.setQuantityIssued(resultSet.getInt("quantity_issued"));

                issuance.setIssuedBy(resultSet.getInt("issued_by"));

                issuance.setIssueDate(resultSet.getTimestamp("issue_date"));

                issuances.add(issuance);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return issuances;

    }

    // Find Issuance By ID
    public StockIssuance findIssuance(int issuanceId) {

        String sql ="SELECT * FROM stock_issuance WHERE issuance_id = ?";

        try (Connection connection =DBConnection.getConnection();

             PreparedStatement statement =connection.prepareStatement(sql)) {

            statement.setInt(1, issuanceId);

            ResultSet resultSet =statement.executeQuery();

            if (resultSet.next()) {

                return new StockIssuance(
                        resultSet.getInt("issuance_id"),
                        resultSet.getInt("cleaner_id"),
                        resultSet.getInt("material_id"),
                        resultSet.getInt("quantity_issued"),
                        resultSet.getInt("issued_by"),
                        resultSet.getTimestamp("issue_date")

                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    // Delete Issuance
    public boolean deleteIssuance(int issuanceId) {

        String sql = "DELETE FROM stock_issuance WHERE issuance_id = ?";

        try (Connection connection = DBConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, issuanceId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;

        }

    }

    // Get Issuances For One Cleaner
    public List<StockIssuance> getCleanerIssuances(int cleanerId) {

        List<StockIssuance> issuances =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM stock_issuance
                WHERE cleaner_id = ?
                ORDER BY issue_date DESC
                """;

        try (Connection connection = DBConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, cleanerId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                StockIssuance issuance = new StockIssuance();

                issuance.setIssuanceId(resultSet.getInt("issuance_id"));

                issuance.setCleanerId(resultSet.getInt("cleaner_id"));

                issuance.setMaterialId(resultSet.getInt("material_id"));

                issuance.setQuantityIssued(resultSet.getInt("quantity_issued"));

                issuance.setIssuedBy(resultSet.getInt("issued_by"));

                issuance.setIssueDate(resultSet.getTimestamp("issue_date"));

                issuances.add(issuance);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return issuances;

    }

}