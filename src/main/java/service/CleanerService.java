package service;

import dao.CleanerDAO;
import model.Cleaner;
import util.Validation;

import java.util.List;

public class CleanerService {
    //show dependency
    private CleanerDAO cleanerDAO;
    public CleanerService(){
        cleanerDAO = new CleanerDAO();
    }
    //adding a cleaner
    public boolean addCleaner(Cleaner cleaner){
        if(!Validation.validCleaner(cleaner)){
            return false;
        }
        return cleanerDAO.addCleaner(cleaner);
    }
    //get all cleaners
    public List<Cleaner> getAllCleaners(){
       return cleanerDAO.getAllCleaners();
    }
    //find cleaner by using their id
    public Cleaner findCleaner(int cleanerId){
        if(!Validation.validFindId(cleanerId)){
            return null;
        }
        return cleanerDAO.findCleaner(cleanerId);
    }
    //update cleaner
    public boolean updateCleaner(Cleaner cleaner){
        if(!Validation.validCleaner(cleaner)){
            return false;
        }
        if(!Validation.validId(cleaner.getCleanerId())){
            return false;
        }
        return cleanerDAO.updateCleaner(cleaner);
    }
    //delete cleaner
    public boolean deleteCleaner(int cleanerId){
        if(!Validation.validDeleteId(cleanerId)) {
            return false;
        }
        return cleanerDAO.deleteCleaner(cleanerId);
    }

}
