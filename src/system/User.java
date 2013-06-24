/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author jonathanrainer
 */
public class User {
    
    private String userGroup;
    
    public User(String userGroup)
    {
        this.userGroup = userGroup;
    }
    
    public String getUserGroup()
    {
        return userGroup;
    }
            
}
