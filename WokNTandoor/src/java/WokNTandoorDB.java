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
@ManagedBean(name = "wokNTandoorDB")
@SessionScoped
public class WokNTandoorDB implements Serializable {
    @Resource(name="jdbc/wokntandoordb")
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
      if (source == null) {
        Logger.global.info("No database connection");
        return "no data source connection";
      }
      Connection conn = source.getConnection();
      if(conn == null){
          return "no database connection";
      }
      String name, description;
      double price;
      try{
         String catalog = conn.getCatalog();
         PreparedStatement stat = conn.prepareStatement(
            "SELECT DishName, DishPrice, DishDescription FROM Dishes WHERE SubMenu = ?");
         stat.setString(1, subMenu);
         ResultSet result = stat.executeQuery();
         String allItemswHTML = "";
         while (result.next()) {
             name = result.getString("DishName");
             price = result.getDouble("DishPrice");
             description = result.getString("DishDescription");
             allItemswHTML += "<h1><b>" + name + "</b><span class=\"w3-right w3-tag w3-dark-grey w3-round\">$" + 
                     String.format("%.2f", price) + "</span></h1>";
             
             if(description!= null)
                 allItemswHTML += "<p class=\"w3-text-grey\">" + description + "</p><hr />";
             else
                 allItemswHTML += "<hr />";
         }
         conn.close();
         return allItemswHTML;
      }
      catch(Exception e){return e.getMessage();}
    }
    
}
