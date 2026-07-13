package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.User;
import model.Material;
import controller.MaterialController;
import controller.SupplierController;
import controller.CleanerController;
import controller.StockIssuanceController;
import controller.UserController;

public class DashboardForm extends JFrame {

    //Controller objects
    private MaterialController materialController;
    private SupplierController supplierController;
    private CleanerController cleanerController;
    private StockIssuanceController stockController;

    // Main panels
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel sidebarPanel;
    private JPanel centerPanel;
    private JLabel lblMaterials;
    private JLabel lblSuppliers;
    private JLabel lblCleaners;
    private JLabel lblLowStock;

    private JTable lowStockTable;
    private DefaultTableModel tableModel;

    public DashboardForm(User loggedInUser){

        // Create controller objects
        materialController = new MaterialController();
        supplierController = new SupplierController();
        cleanerController = new CleanerController();
        stockController = new StockIssuanceController();

        //setup form
        setupForm();

        //header
        createHeader();

        //sidebar
        createSideBar();

        //center panel
        createCenterPanel();

        //Stats
        loadStatistics();

        //low stock
        loadLowStockTable();

        setVisible(true);
    }//end of DashboardForm

    public void setupForm(){
        //setup form
        setTitle("Cleaning Inventory System");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //create main panel
        JPanel mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());
    }//end of setupForm

    public  void createHeader(){
        //header
        headerPanel = new JPanel();

        headerPanel.setPreferredSize(new Dimension(1000, 70));

        JLabel lblTitle = new JLabel("Cleaning Inventory Management System");

        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        headerPanel.add(lblTitle);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

    }//end of createHeader

    public void createSideBar(){
        //sidebar with buttons to navigate
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 600));
        sidebar.setLayout(new GridLayout(7, 1, 10, 10));
        //creating buttons
        JButton btnMaterials = new JButton("Manage Materials");
        JButton btnSuppliers = new JButton("Manage Suppliers");
        JButton btnCleaners = new JButton("Manage Cleanerss");
        JButton btnStock = new JButton("Stock Issuance");
        JButton btnUsers = new JButton("Manage Users");
        JButton btnLowStock = new JButton("Low Stock");
        JButton btnLogOut = new JButton("LogOut");
        //adding buttons to sidebar
        sidebar.add(btnMaterials);
        sidebar.add(btnSuppliers);
        sidebar.add(btnCleaners);
        sidebar.add(btnStock);
        sidebar.add(btnUsers);
        sidebar.add(btnLowStock);
        sidebar.add(btnLogOut);

        mainPanel.add(sidebar, BorderLayout.WEST);
    }//end of createSideBar

    public void createCenterPanel(){
        //Center panel

        centerPanel = new JPanel();

        centerPanel.setLayout(new GridLayout(2,2,15,15));

        JPanel card1 = new JPanel();
        JPanel card2 = new JPanel();
        JPanel card3 = new JPanel();
        JPanel card4 = new JPanel();

        card1.setBorder(BorderFactory.createTitledBorder("Materials"));
        card2.setBorder(BorderFactory.createTitledBorder("Suppliers"));
        card3.setBorder(BorderFactory.createTitledBorder("Cleaners"));
        card4.setBorder(BorderFactory.createTitledBorder("Low Stock"));

        lblMaterials = new JLabel("0");
        card1.add(lblMaterials);

        lblSuppliers = new JLabel("0");
        card2.add(lblSuppliers);

        lblCleaners = new JLabel("0");
        card3.add(lblCleaners);

        lblLowStock = new JLabel("0");
        card4.add(lblLowStock);

        centerPanel.add(card1);
        centerPanel.add(card2);
        centerPanel.add(card3);
        centerPanel.add(card4);

        String[] columns = {
                "Material",
                "Quantity",
                "Reorder Level"
        };

        tableModel = new DefaultTableModel(columns, 0);

        lowStockTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(lowStockTable);

        centerPanel.add(scrollPane);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }//end of createCenterPanel

    public void loadStatistics(){
        lblMaterials.setText(
                String.valueOf(materialController.getMaterialCount()));

        lblSuppliers.setText(
                String.valueOf(supplierController.getSupplierCount()));

        lblCleaners.setText(
                String.valueOf(cleanerController.getCleanerCount()));

        lblLowStock.setText(
                String.valueOf(materialController.getLowStockCount()));
    }//end of loadStatistics

    public void loadLowStockTable(){
        //low stock table
        tableModel.setRowCount(0);

        List<Material> materials =
                materialController.getLowStockMaterials();

        for (Material material : materials) {

            tableModel.addRow(new Object[]{
                    material.getMaterialName(),
                    material.getQuantity(),
                    material.getReorderLevel()
            });

        }
    }//end of loadLowStockTable



}

