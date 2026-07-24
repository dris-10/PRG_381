package controller;
//importing
import model.Material;
import service.MaterialService;
import java.util.List;

public class MaterialController {
    private MaterialService materialService;


    // Constructor
    public MaterialController(){

        materialService = new MaterialService();

    }



    // Add Material
    public boolean addMaterial(Material material){

        return materialService.addMaterial(material);

    }



    // Get all materials
    public List<Material> getAllMaterials(){

        return materialService.getAllMaterials();

    }



    // Find material by ID
    public Material findMaterial(String materialName){

        return materialService.findMaterial(materialName);

    }



    // Update material
    public boolean updateMaterial(Material material){

        return materialService.updateMaterial(material);

    }



    // Delete material
    public boolean deleteMaterial(int materialId){

        return materialService.deleteMaterial(materialId);

    }



    // Get low stock materials
    public List<Material> getLowStockMaterials(){

        return materialService.getLowStockMaterials();

    }
}
