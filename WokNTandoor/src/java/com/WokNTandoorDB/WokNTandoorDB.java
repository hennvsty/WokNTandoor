/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.WokNTandoorDB;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;

/**
 *
 * @author Adonias
 */
@ManagedBean(name = "wokNTandoorDB", eager = true)
@SessionScoped
public class WokNTandoorDB implements Serializable {
    //@Resource(name="jdbc/wokntandoordb")
    //private DataSource source;
    private String dishName;
    private String dishPrice;
    private String dishSpecialPrice;
    private String dishDescription;
    private String subMenu;
    private String dishPicture;
    private int orderID;
    private double subtotal;
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://aau3z4pq3psz62.cx2uxgwhz5kj.us-east-1.rds.amazonaws.com:3306/ebdb?zeroDateTimeBehavior=convertToNull";
    private final String USER = "wokntandoor";
    private final String PASS = "kickme531";
    
    /**
     * Creates a new instance of WokNTandoorDB
     */
    public WokNTandoorDB() {
        subtotal = 0;
        orderID = 0;
    }
    
    public String getMenuItems(String subMenu) throws SQLException{
        try {
            /*if (source == null) {
            Logger.global.info("No database connection");
            return "no data source connection";
            }*/
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WokNTandoorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
      if(conn == null){
          return "no database connection";
      }
      String name, description;
      double price;
      try{
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
         result.close();
         stat.close();
         conn.close();
         return allItemswHTML;
      }
      catch(Exception e){return e.getMessage();}
    }
    
    public String getOrderItems(String subMenu, String catID) throws SQLException{
      try {
            /*if (source == null) {
            Logger.global.info("No database connection");
            return "no data source connection";
            }*/
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WokNTandoorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
      String name, description, picPath;
      double price;
      try{
         PreparedStatement stat = conn.prepareStatement(
            "SELECT DishName, DishPrice, DishDescription, DishPicture FROM Dishes WHERE SubMenu = ?");
         stat.setString(1, subMenu);
         ResultSet result = stat.executeQuery();
         String allItemswHTML = "";
         while (result.next()) {
             name = result.getString("DishName");
             price = result.getDouble("DishPrice");
             picPath = result.getString("DishPicture");
             if(picPath == null){
                 picPath = "";
             }
             description = result.getString("DishDescription");
             allItemswHTML += "<tr>" +
"                        <td class=\"menu-item-thumb\"><img src=\""+picPath+"\" alt=\"\" style=\"width:100px;height:100px;\" id=\"i"+ orderID + "\"/></td>" +
"                        <td class=\"menu-item-info\">" +
"                            <div class=\"w3-large w3-padding-4\" id=\"n"+orderID+"\">" + name + "</div>"; 
             if(description!= null)
                 allItemswHTML += "<div class=\"w3-small w3-padding-4\">" + description + "</div>";
             else
                 allItemswHTML += "</td>";
             allItemswHTML += "<td class=\"menu-item-price\">$<span id=\"p"+ orderID + "\">" + String.format("%.2f", price) + "</span></td>";
             allItemswHTML += "<td class=\"w3-large w3-center\">" +
"                            <button class=\"w3-button w3-transparent w3-text-khaki\" onclick= 'addSubtotal("+orderID+", \""+catID+"\")'>+</button>" +
"                            <div id=\"q"+orderID+"\" class=\"w3-text-white\">0</div>" +
"                            <button class=\"w3-button w3-transparent w3-text-khaki\" onclick= 'subtractSubtotal("+orderID+", \""+catID+"\")'>-</button>" +
"                        </td></tr>";  
             orderID++;
         }
         conn.close();
         return allItemswHTML;
      }
      catch(Exception e){return e.getMessage();}
    }
    
    public void addSubtotal(String dish) throws SQLException{
        try {
            /*if (source == null) {
            Logger.global.info("No database connection");
            return "no data source connection";
            }*/
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WokNTandoorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        double price;
        try{
            PreparedStatement stat = conn.prepareStatement(
                "SELECT DishPrice FROM Dishes WHERE DishName = ?");
            stat.setString(1, dish);
            ResultSet result = stat.executeQuery();
            result.next();
            price = result.getDouble("DishPrice");
            subtotal += price; 
            conn.close();
        }    
        catch(Exception e){}      
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
