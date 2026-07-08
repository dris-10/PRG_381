package view;
//importing
import model.Material;
import controller.MaterialController;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class MaterialForm extends JFrame {


    // Text fields
    private JTextField idField;
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField quantityField;
    private JTextField reorderField;
    private JTextField unitField;
    private JTextField supplierField;


    // Table
    private JTable materialTable;


    // Controller
    private MaterialController controller;



    public MaterialForm(){

        controller = new MaterialController();

        setupFrame();
        createUI();
        loadMaterials();

    }


    private void setupFrame(){

        setTitle("Cleaning Inventory System");

        setSize(900,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void createUI(){

        setLayout(new BorderLayout(10,10));


        add(createInputPanel(), BorderLayout.WEST);


        add(createTablePanel(), BorderLayout.CENTER);


        add(createButtonPanel(), BorderLayout.SOUTH);


        setVisible(true);

    }

    private JPanel createInputPanel(){

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBorder(
                BorderFactory.createTitledBorder("Material Details")
        );


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5,5,5,5);


        idField = new JTextField(15);
        nameField = new JTextField(15);
        categoryField = new JTextField(15);
        quantityField = new JTextField(15);
        reorderField = new JTextField(15);
        unitField = new JTextField(15);
        supplierField = new JTextField(15);



        addField(panel,"ID:",idField,0,gbc);

        addField(panel,"Name:",nameField,1,gbc);

        addField(panel,"Category:",categoryField,2,gbc);

        addField(panel,"Quantity:",quantityField,3,gbc);

        addField(panel,"Reorder:",reorderField,4,gbc);

        addField(panel,"Unit:",unitField,5,gbc);

        addField(panel,"Supplier:",supplierField,6,gbc);



        return panel;

    }

    private void addField(
            JPanel panel,
            String label,
            JTextField field,
            int row,
            GridBagConstraints gbc
    ){

        gbc.gridx=0;
        gbc.gridy=row;

        panel.add(new JLabel(label),gbc);


        gbc.gridx=1;

        panel.add(field,gbc);

    }

    private JPanel createTablePanel(){

        JPanel panel = new JPanel(new BorderLayout());


        panel.setBorder(
                BorderFactory.createTitledBorder("Materials")
        );


        String[] columns = {
                "ID",
                "Name",
                "Category",
                "Quantity",
                "Unit"
        };


        materialTable =
                new JTable(
                        new DefaultTableModel(columns,0)
                );


        panel.add(
                new JScrollPane(materialTable),
                BorderLayout.CENTER
        );


        return panel;

    }

    private JPanel createButtonPanel(){

        JPanel panel = new JPanel();


        JButton add =
                new JButton("Add");


        JButton viewAll =
                new JButton("View All");


        JButton update =
                new JButton("Update");


        JButton delete =
                new JButton("Delete");


        JButton find =
                new JButton("Find");


        JButton clear =
                new JButton("Clear");



        panel.add(add);
        panel.add(viewAll);
        panel.add(find);
        panel.add(update);
        panel.add(delete);
        panel.add(clear);



        add.addActionListener(e->addMaterial());

        update.addActionListener(e->updateMaterial());

        delete.addActionListener(e->deleteMaterial());

        find.addActionListener(e->findMaterial());

        clear.addActionListener(e->clearFields());

        viewAll.addActionListener(e->loadMaterials());

        return panel;

    }

    private void addMaterial(){


        try{


            Material material = new Material();


            material.setMaterialName(nameField.getText());
            material.setCategory(categoryField.getText());
            material.setQuantity(
                    Integer.parseInt(quantityField.getText())
            );

            material.setReorderLevel(
                    Integer.parseInt(reorderField.getText())
            );


            material.setUnit(unitField.getText());


            material.setSupplierId(
                    Integer.parseInt(supplierField.getText())
            );



            boolean success = controller.addMaterial(material);



            if(success){

                JOptionPane.showMessageDialog(
                        this,
                        "Material added successfully!"
                );
                loadMaterials();


            }
            else{

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add material",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

            }



        }
        catch(Exception e){


            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid information",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }


    }//end of add material

    private void loadMaterials(){

        DefaultTableModel model =
                (DefaultTableModel) materialTable.getModel();


        model.setRowCount(0);


        List<Material> materials =
                controller.getAllMaterials();


        for(Material material : materials){

            model.addRow(new Object[]{
                    material.getMaterialId(),
                    material.getMaterialName(),
                    material.getCategory(),
                    material.getQuantity(),
                    material.getUnit()
            });

        }

    }//end of view

    private void findMaterial(){

        try{

            int id = Integer.parseInt(idField.getText());


            Material material = controller.findMaterial(id);


            if(material != null){

                nameField.setText(material.getMaterialName());
                categoryField.setText(material.getCategory());
                quantityField.setText(
                        String.valueOf(material.getQuantity())
                );

                reorderField.setText(
                        String.valueOf(material.getReorderLevel())
                );

                unitField.setText(material.getUnit());

                supplierField.setText(
                        String.valueOf(material.getSupplierId())
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Material found!"
                );

            }
            else{

                JOptionPane.showMessageDialog(
                        this,
                        "Material not found",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

            }


        }
        catch(Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid ID"
            );

        }

    }//end of find by id

    private void updateMaterial(){

        try{


            Material material = new Material();


            material.setMaterialId(
                    Integer.parseInt(idField.getText())
            );


            material.setMaterialName(
                    nameField.getText()
            );


            material.setCategory(
                    categoryField.getText()
            );


            material.setQuantity(
                    Integer.parseInt(quantityField.getText())
            );


            material.setReorderLevel(
                    Integer.parseInt(reorderField.getText())
            );


            material.setUnit(
                    unitField.getText()
            );


            material.setSupplierId(
                    Integer.parseInt(supplierField.getText())
            );



            boolean success =
                    controller.updateMaterial(material);



            if(success){

                JOptionPane.showMessageDialog(
                        this,
                        "Material updated successfully!"

                );

                loadMaterials();

            }
            else{

                JOptionPane.showMessageDialog(
                        this,
                        "Update failed"
                );

            }


        }
        catch(Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid information"
            );

        }

    }//end of update

    private void deleteMaterial(){

        try{


            int id = Integer.parseInt(
                    idField.getText()
            );


            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this material?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );


            if(choice == JOptionPane.YES_OPTION){


                boolean success =
                        controller.deleteMaterial(id);



                if(success){

                    JOptionPane.showMessageDialog(
                            this,
                            "Material deleted successfully!"
                    );
                    loadMaterials();

                }
                else{

                    JOptionPane.showMessageDialog(
                            this,
                            "Delete failed"
                    );

                }

            }


        }
        catch(Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid ID"
            );

        }

    }//end of delete

    private void clearFields(){

        idField.setText("");
        nameField.setText("");
        categoryField.setText("");
        quantityField.setText("");
        reorderField.setText("");
        unitField.setText("");
        supplierField.setText("");

    }//end of clear field


}
