/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
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
    private String outputMessage;

    
    /**
     * Creates a new instance of adminControls
     */
    
    public adminControls() {
        outputMessage = "";        
    }
    public String getOutputMessage() {
        return outputMessage;
    }
    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }
    public void changeDishPrice(String dish, double price){
        
    }
    public void removeDish(String dish){
        
    }
    public void addDish(String dishName, String dishDescription, String dishPrice){
        
    }
    public String login(){
        FacesContext face = FacesContext.getCurrentInstance();
        Map<String, String> map = face.getExternalContext().getRequestParameterMap();
        String username = map.get("username");
        String password = map.get("password");
        if(username != null && password != null){
        if(username.equals("NatashaB") && password.equals("brahmbhatt")){
            try {
                face.getExternalContext().redirect("Admin.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(adminControls.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(username.equals("") || password.equals("")) {
            return "Must fill in empty fields";
        } 
        else {
            if (!username.equals("NatashaB")){
                outputMessage = "<br/>Invalid UserID";
            }
            if (!password.equals("brahmbhatt")){  
                outputMessage = "<br/>Invalid Password";
            }
            if(!username.equals("NatashaB") && !password.equals("brahmbhatt")){
                outputMessage = "<br/>Invalid UserID<br/>Invalid Password";
            }        
        }
        }
        return outputMessage;
    }
    
}
