/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;

/**
 *
 * @author Adonias
 */
@ManagedBean
@Named(value = "wokNTandoorDB")
@SessionScoped
public class WokNTandoorDB implements Serializable {
    @Resource(name="jdbc/wokntandoor")
    private DataSource source;
    private String dishName;
    private String dishPrice;
    private String dishSpecialPrice;
    private String dishDescription;
    private String subMenu;
    private String dishPicture;
    
    /**
     * Creates a new instance of WokNTandoorDB
     */
    public WokNTandoorDB() {
        
    }
    
    public String getMenuItems(String subMenu) throws SQLException{
        //Connection conn = source.getConnection();
        //String schema = conn.getSchema();
        if (source == null) 
      {
         Logger.global.info("No database connection");
         //return null;
      }
      try(Connection conn = source.getConnection()){
         String catalog = conn.getCatalog();
         
         System.out.println(conn);
         PreparedStatement stat = conn.prepareStatement(
            "SELECT * FROM Dishes WHERE SubMenu = ?");
         stat.setString(1, subMenu);
         ResultSet result = stat.executeQuery();
         if (result.next()) { 
             return result.getString(1); 
         }
         else { 
             return null; 
         }
      } 
    }
    
}
