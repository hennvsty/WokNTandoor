/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private String action;
    
    /**
     * Creates a new instance of adminControls
     */
    
    public adminControls() {
        outputMessage = "";   
        action = "";
    }
    public String getOutputMessage() {
        return outputMessage;
    }
    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }
    
    public void changeDishPrice(String dishName, double dishPrice) throws SQLException {
        if (source == null) {
            Logger.global.info("No database source");
            return;
        }
        Connection conn = source.getConnection();
        if(conn == null){
            Logger.global.info("No database connection");
            return;
        }
        
        try {
            PreparedStatement stmt =
                    conn.prepareStatement("UPDATE Dishes SET DishPrice = ? WHERE DishName = ?");
            stmt.setDouble(1, dishPrice);
            stmt.setString(1, dishName);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void removeDish(String dishName) throws SQLException {
        if (source == null) {
            Logger.global.info("No database source");
            return;
        }
        Connection conn = source.getConnection();
        if(conn == null){
            Logger.global.info("No database connection");
            return;
        }
        
        try {
            PreparedStatement stmt =
                    conn.prepareStatement("DELETE FROM Dishes WHERE DishName = ?");
            stmt.setString(1, dishName);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addDish(String dishName, double dishPrice, String dishDescription) throws SQLException {
        if (source == null) {
            Logger.global.info("No database source");
            return;
        }
        Connection conn = source.getConnection();
        if(conn == null){
            Logger.global.info("No database connection");
            return;
        }
        
        int result = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Dishes "
                    + "(DishName, DishPrice, DishDescription) "
                    + "SELECT ?, ?, ? FROM Dishes "
                    + "WHERE (DishName = ?) HAVING count(*) = 0");
            stmt.setString(1, dishName);
            stmt.setDouble(2, dishPrice);
            stmt.setString(3, dishDescription);
            result = stmt.executeUpdate(); // not 0 = success
            stmt.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public String login(){
        FacesContext face = FacesContext.getCurrentInstance();
        Map<String, String> map = face.getExternalContext().getRequestParameterMap();
        String username = map.get("username");
        String password = map.get("password");
        if(username != null && password != null){
        if(username.equals("NatashaB") && password.equals("brahmbhatt")){
            try {
                return "Admin";
            } catch (Exception ex) {
                Logger.getLogger(adminControls.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(username.equals("") || password.equals("")) {
            action = "";
            return "Must fill in empty fields";
        } 
        else {
            if (!username.equals("NatashaB")){
                action = "";
                outputMessage = "<br/>Invalid UserID";
            }
            if (!password.equals("brahmbhatt")){  
                action = "";
                outputMessage = "<br/>Invalid Password";
            }
            if(!username.equals("NatashaB") && !password.equals("brahmbhatt")){
                action = "";
                outputMessage = "<br/>Invalid UserID<br/>Invalid Password";
            }        
        }
        }
        return outputMessage;
    }
    
}
