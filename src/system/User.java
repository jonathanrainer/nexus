/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 * Encapsulates functionality related to the user of this system.
 * @author jonathanrainer
 */
public class User {
    
    /**
     * A string that describes the team the user belongs to. 
     */
    private String team;
    private String name;
    
    /**
     * Construct the user object
     * @param team The team the user belongs to
     */
    public User(String team)
    {
        this.team = team;
    }
    
    /**
     * Return the team the user belongs to
     * @return The team the user belongs to
     */
    public String getTeam()
    {
        return team;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
            
}
