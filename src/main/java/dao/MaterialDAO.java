package dao;
// importing
import model.Material;


import java.sql.*;
import java.util.*;

/*
 * Data Access Object for the Material table.
 * Handles all database operations related to materials.
 */
public class MaterialDAO {
    //add new material to database
    public boolean addMaterial(Material material){
        String sql = """
                INSERT INTO materials
                (material_name, category, quantity, reorder_level, unit, supplier_id)
                VALUES (?, ?, ?, ?,? ,?)
                """;
        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, material.getMaterialName());
            statement.setString(2, material.getCategory());
            statement.setInt(3, material.getQuantity());
            statement.setInt(4, material.getReorderLevel());
            statement.setString(5, material.getUnit());

            //NULL handeling in supplier_id
            if(material.getSupplierId() == 0){
                statement.setNull(6, Types.INTEGER);
            }else{
                statement.setInt(6, material.getSupplierId());
            }

            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }//end of add material

    //Get all materials
    public List<Material> getAllMaterials(){
        List<Material> materials = new ArrayList<>();

        String sql = """
                SELECT *
                FROM materials
                ORDER BY material_name
                """;
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while(resultSet.next()) {
                Material material = new Material();

                material.setMaterialId(resultSet.getInt("material_id"));
                material.setMaterialName(resultSet.getString("material_name"));
                material.setCategory(resultSet.getString("category"));
                material.setQuantity(resultSet.getInt("quantity"));
                material.setReorderLevel(resultSet.getInt("reorder_level"));
                material.setUnit(resultSet.getString("unit"));
                material.setSupplierId(resultSet.getInt("supplier_id"));

                materials.add(material);
            }

        }catch(SQLException e){
                e.printStackTrace();
            }
        return  materials;
    }//end of get all material

    //search by id
    public  Material getMaterialById(int materialId){
        String sql = "SELECT * FROM materials WHERE material_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, materialId);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                return new Material(
                        resultSet.getInt("material_id"),
                        resultSet.getString("material_name"),
                        resultSet.getString("category"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("reorder_level"),
                        resultSet.getString("unit"),
                        resultSet.getInt("supplier_id")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return  null;
    }//end of search by ID

    //update material
    public boolean updateMaterial(Material material){

        String sql = """
                UPDATE materials
                SET material_name = ?,
                category = ?,
                quantity = ?,
                reorder_level = ?,
                unit = ?,
                supplier_id = ?
                WHERE material_id = ?
                """;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, material.getMaterialName());
            statement.setString(2, material.getCategory());
            statement.setInt(3, material.getQuantity());
            statement.setInt(4, material.getReorderLevel());
            statement.setString(5, material.getUnit());

            if (material.getSupplierId() == 0) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, material.getSupplierId());
            }

            statement.setInt(7, material.getMaterialId());

            return statement.executeUpdate() > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }//end of update material

    //delete material
    public boolean deleteMaterial(int materialId) {
        String sql = "DELETE FROM materials WHERE material_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, materialId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }//end of delete

    //low stock
    public List<Material> getLowStockMaterials() {

        List<Material> materials = new ArrayList<>();

        String sql = """
            SELECT *
            FROM materials
            WHERE quantity <= reorder_level
            ORDER BY material_name
            """;

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Material material = new Material();

                material.setMaterialId(resultSet.getInt("material_id"));
                material.setMaterialName(resultSet.getString("material_name"));
                material.setCategory(resultSet.getString("category"));
                material.setQuantity(resultSet.getInt("quantity"));
                material.setReorderLevel(resultSet.getInt("reorder_level"));
                material.setUnit(resultSet.getString("unit"));
                material.setSupplierId(resultSet.getInt("supplier_id"));

                materials.add(material);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }//end of low stock
}
