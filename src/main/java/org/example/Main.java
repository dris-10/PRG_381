package org.example;
//importing
import model.Material;
import service.MaterialService;
import dao.DBConnection;
import view.MaterialForm;
import view.CleanerDashboard;
import javax.swing.SwingUtilities;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        try (Connection connection = DBConnection.getConnection()) {

            System.out.println("✅ Connected to PostgreSQL successfully!");

        } catch (Exception e) {

            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }

        
        SwingUtilities.invokeLater(() -> new CleanerDashboard().setVisible(true));
    }
}