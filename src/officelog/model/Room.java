package officelog.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import officelog.control.ButtonRoom;

/**
 * This class represents a Room in the office
 * 
 * @author Zooty
 */
public class Room implements Serializable{
    
    /**
     * The unique name of the Room. Serves as primary key.
     */
    final private String Name;
    
    /**
     * The name of the Room in the selected Language.
     */
    private String LanguageName;
    
    /**
     * The number of people can be in this room. Set 0 if unlimited. 
     */
    private int MaxPeople;
    
    /**
     * Collection of Rooms that can be reached from this Room.
     */
    final private Set<Room> Neighbors;
    
    /**
     * If open, everybody can enter without authorizing and visitors can go in as well 
     * without permission.
     */
    final private Boolean Open;
    
    /**
     * Refers to the button this room is represented on the GUI.
     */
    private ButtonRoom btnRoom;

    /**
     * Creates a Room.
     * 
     * @param Name Name of the Room.
     * @param MaxPeople Maximum number of people that can be in the Room.
     * @param Open True if can be entered without authorization.
     */
    public Room(String Name, int MaxPeople, Boolean Open) {
        this.Name = Name;
        this.LanguageName = Name;
        this.MaxPeople = MaxPeople;
        this.Open = Open;
        Neighbors = new HashSet<>();
        btnRoom = null;
    }
    
    /**
     * Creates a room that needs authorization to enter.
     * 
     * @param Name Name of the Room.
     * @param MaxPeople Maximum number of people that can be in the Room.
     */
    public Room(String Name, int MaxPeople) {
        this(Name, MaxPeople, false);
    }
    
    /**
     * Creates a room with "unlimited space" that needs authorization to enter.
     * 
     * @param Name Name of the Room.
     */
    public Room(String Name){
        this(Name, 0);
    }

    /**
     * Return the name of the Room.
     * 
     * @return the name of the Room.
     */
    public String getName() {
        return Name;
    }

    /**
     * Tells how many people can fit into this Room.
     * 
     * @return the maximum number of people can be in that Room.
     */
    public int getMaxPeople() {
        return MaxPeople;
    }
    /**
     * Returns The set of this room's neighbors.
     * 
     * @return The set of this room's neighbors.
     */
    public Set<Room> getNeighbors() {
        return Neighbors;
    }    

    /**
     * Tells if this Room is open for guests.
     * 
     * @return true if the room needs no authorization to enter.
     */
    public Boolean isOpen() {
        return Open;
    }

    /**
     * returns the button this room is assigned to. 
     * 
     * @return the button this room is assigned to. 
     * 
     * @throws NullPointerException if there is no button assigned to this Room.
     */
    public ButtonRoom getBtnRoom() {
        if(btnRoom == null)
            throw new NullPointerException("No button assigned to this Room");
        return btnRoom;
    }

    /**
     * Gets the name of the Room in the selected Language.
     * 
     * @return the name of the Room in the selected Language.
     */
    public String getLanguageName() {
        return LanguageName;
    }

    /**
     * Assigns a button to this Room.
     * 
     * @param btnRoom assigns this room to parameter button.
     */
    public void setBtnRoom(ButtonRoom btnRoom) {
        this.btnRoom = btnRoom;
    }

    /**
     * Sets the maximum number of people that can be in the Room. (Change public if needed.)
     * 
     * @param MaxPeople the maximum number of people that can be in the Room.
     */
     private void setMaxPeople(int MaxPeople) {
        this.MaxPeople = MaxPeople;
    }

    /**
     * Set the name of the Room in the selected Language.
     * 
     * @param LanguageName the name of the Room in the selected Language.
     */
    public void setLanguageName(String LanguageName) {
        this.LanguageName = LanguageName;
    }     
     
    /**
     * Adds a new Room that can be reached directly from this Room.
     * 
     * @param room the new neighbor.
     */
    public void addNeighbor(Room room){
        Neighbors.add(room);
    } 
    
    /**
     * Returns true if the parameter is neighbor of this Room.
     * 
     * @param room the Room we question if is a neighbor.
     * @return true if it is a neighbor.
     */
    public boolean isNeighbor(Room room){
        return Neighbors.contains(room);
    }
    
    @Override
    public String toString(){
        /*
        String r = Name+", Neighbors:";
        for (Room Neighbor : Neighbors) {
            r+=" "+Neighbor.Name;
        }        
        return r;
        */
        return LanguageName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.Name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        return true;
    }
    
}