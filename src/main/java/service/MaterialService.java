package service;

import model.Material;
import dao.MaterialDAO;
import util.Validation;

import java.util.List;


public class MaterialService {

    private MaterialDAO materialDAO;

    public MaterialService(){
        materialDAO = new MaterialDAO();
    }

    // Add Material
    public boolean addMaterial(Material material){

        if(!Validation.validMaterial(material)){
            return false;
        }

        return materialDAO.addMaterial(material);
    }

    // Get All Materials
    public List<Material> getAllMaterials(){
        return materialDAO.getAllMaterials();
    }

    // Find Material By ID
    public Material findMaterial(String name){

        if(!Validation.validMaterialName(name)){
            return null;
        }

        return materialDAO.getMaterialById(name);
    }

    // Update Material
    public boolean updateMaterial(Material material){

        if(!Validation.validMaterial(material)){

            return false;
        }

        if(!Validation.validId(
                material.getMaterialId())){

            return false;
        }

        return materialDAO.updateMaterial(material);

    }

    // Delete Material
    public boolean deleteMaterial(int id){

        if(!Validation.validDeleteId(id)){

            return false;

        }

        return materialDAO.deleteMaterial(id);

    }

    // Low Stock Materials

    public List<Material> getLowStockMaterials(){


        List<Material> materials =
                materialDAO.getLowStockMaterials();


        return materials;

    }

    public int getMaterialCount() {
        return materialDAO.getMaterialCount();
    }

    public int getLowStockCount() {
        return materialDAO.getLowStockCount();
    }
}