package officelog;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.image.Image;

/**
 * Represents an employee in the office. Extends from Person. 
 * 
 * @author Zooty
 */
public class Employee extends Person{
    /**
     * Name of the job.
     */
    private String Job;
    
    /**
     * This collection contains all the Rooms this person can enter.
     */
    final private Set<Room> Permissions;
            
    /**
     * Creates and Employee with default picture.
     * 
     * @param Name name of the person.
     * @param ID unique ID of the person.
     * @param Job Job of the person.
     */
    public Employee(String Name, int ID, String Job) { 
        super(Name, ID);
        this.Job = Job;
        Permissions = new HashSet<>();
    }
     
    /**
     * Creates and Employee.
     * 
     * @param Name name of the person.
     * @param Pic picture of the person.
     * @param ID unique ID of the person.
     * @param Job Job of the person.
     */
    public Employee(String Name, Image Pic, int ID, String Job) {
        super(Name, Pic, ID);
        this.Job = Job;
        Permissions = new HashSet<>();
    }
    
    /**
     * 
     * @return the job of the person.
     */
    public String getJob() {
        return Job;
    }

    /**
     * Modifies the job of the person.
     * 
     * @param Job the new job of this person 
     */
    public void setJob(String Job) {
        this.Job = Job;
    }
    
    /**
     * Adds a new Room where this person can enter.
     * 
     * @param room the new room this person can enter.
     */
    public void addPermission(Room room){
        this.Permissions.add(room);
    }
    
    /**
     * Removes a Room from the list of Rooms where this person can enter.
     * 
     * @param room the Room this person can not enter anymore.
     */
    public void removePermission(Room room){
        this.Permissions.remove(room);
    }
    
     /**
     * Removes a Room from the list of Rooms where this person can enter.
     * 
     * @param roomname the name of the Room this person can not enter anymore.
     */
    public void removePermission(String roomname){
        //does not check if the Room is actually found or not
        for (Room p : Permissions) {
            if(p.getName().equals(roomname))
                Permissions.remove(p);
        }        
    }
    /**
     * This function tells you if the person is allowed to enter the specific Room.
     * 
     * @param room The specific room the person wants to enter.
     * @return True if the person is allowed to enter.
     */
    @Override
    public boolean isAllowed(Room room){
        return Permissions.contains(room);
    }
}
