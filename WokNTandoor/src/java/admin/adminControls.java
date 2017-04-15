/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

/**
 *
 * @author Adonias
 */
@ManagedBean(name = "adminControls")
@SessionScoped
public class adminControls implements Serializable{
    @Resource(name="jdbc/wokntandoordb")
    private DataSource source; 
    /**
     * Creates a new instance of adminControls
     */
    
    public adminControls() {
        
    }
    public void changeDishPrice(String dish, double price){
        
    }
    public void removeDish(String dish){
        
    }
    public void addDish(String dishName, String dishDescription, String dishPrice){
        
    }
    public void login(String username, String password){
        FacesContext face = FacesContext.getCurrentInstance();
        try {
            face.getExternalContext().redirect("placeOrder.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(adminControls.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
