package model;

public class Cleaner {
    //create variables
    private int cleanerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String department;
    //create constructor
    public Cleaner(){}

    //create a full constructor
    public Cleaner(int cleanerId,String firstName, String lastName,String phone, String department){
       this.cleanerId = cleanerId;
       this.firstName = firstName;
       this.lastName = lastName;
       this.phone = phone;
       this.department = department;
    }

    //create getters and setters
    public int getCleanerId()
    {
        return cleanerId;
    }
    public void setCleanerId(int cleanerId){
        this.cleanerId = cleanerId;
    }
    public String getFirstName(){
     return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department=department;
    }
    @Override
    public String toString(){
        return "Name: "+ firstName + " Surname: "+ lastName+ " Department: " + department;
    }

}
