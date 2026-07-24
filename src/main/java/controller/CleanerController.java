package controller;

import model.Cleaner;
import service.CleanerService;

import java.util.List;

public class CleanerController {
    //show dependency
    private CleanerService cleanerService;
    //constructor
    public CleanerController(){
        cleanerService = new CleanerService();
    }
    //adding a new cleaner
    public boolean addCleaner(Cleaner cleaner){
        return cleanerService.addCleaner(cleaner);
    }
    //displaying all cleaners
    public List<Cleaner> getAllCleaners(){
        return cleanerService.getAllCleaners(); // No parameter passed inside, returns a List
    }
    //finding a cleaner using an id
    public Cleaner findCleaner(int cleanerId){
        return cleanerService.findCleaner(cleanerId);
    }
    //updating cleaner info
    public boolean updateCleaner(Cleaner cleaner){
        return cleanerService.updateCleaner(cleaner);
    }
    //deleting a cleaner from the list
    public boolean deleteCleaner(int cleanerId){
        return cleanerService.deleteCleaner(cleanerId);
    }
}
