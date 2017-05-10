/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package placeOrder;

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
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Adonias
 */
@ManagedBean(name = "placeOrder")
@SessionScoped
public class placeOrder implements Serializable{
    @Resource(name="jdbc/wokntandoordb")
    private DataSource source;
    private String[] dishes;
    private String[] quantity;
    private String email;
    private double subtotal;
    
    public placeOrder() {       
        subtotal = 0;
    }
    public placeOrder(double mSubtotal){
        this.subtotal = mSubtotal;
    }   
    
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String[] getDishes() {
        return dishes;
    }

    public void setDishes(String[] dishes) {
        this.dishes = dishes;
    }
    
    public String getDishesOrder(){
        String dishesString = "";
        for(int i = 0; i < dishes.length; i++){
            if(!dishes[i].equals(""))
                dishesString += dishes[i] + " X " + quantity[i] + "\n";   
        }
        dishesString += "$" + String.format("%.2f", subtotal) + "\nEmail: " +email;
        return dishesString;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public void addSubtotal(String dish) throws SQLException{
        if (source == null) {
            Logger.global.info("No database connection");
        }
        Connection conn = source.getConnection();
        double price;
        try{
            PreparedStatement stat = conn.prepareStatement(
                "SELECT DishPrice FROM Dishes WHERE DishName = ?");
            stat.setString(1, dish);
            ResultSet result = stat.executeQuery();
            price = result.getDouble("DishPrice");
            subtotal += price; 
            conn.close();
        }    
        catch(Exception e){}      
    }
    public void confirmOrder(){
        FacesContext face = FacesContext.getCurrentInstance();
        Map<String, String> map = face.getExternalContext().getRequestParameterMap();
        String total = map.get("total");
        subtotal = Double.parseDouble(total);
        String dishesList = map.get("dishes").trim();
        dishes = dishesList.split("%");
        String quantities = map.get("quantities").trim();
        quantity = quantities.split("%");
        email = map.get("email");
        FacesMessage message = new FacesMessage("Order Confirmed", sendConfirmationEmail());
        FacesContext.getCurrentInstance().addMessage(null, message);
        //return "placeOrder.xhtml";
    }
    
    public String sendConfirmationEmail(){
      // Recipient's email ID needs to be mentioned.
      //Get properties object  
        String dishesString = "";
        for(int i = 0; i < dishes.length; i++){
            if(!dishes[i].equals(""))
                dishesString += dishes[i] + " X " + quantity[i] + "<br/>";   
        }
        dishesString += "$" + String.format("%.2f", subtotal) + "<br/>" + "email: " +email;    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getInstance(props,    
        new javax.mail.Authenticator() {    
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {    
                return new PasswordAuthentication("adonias.lopez2013@gmail.com","CoBrA2013");  
            }    
        });    
        //compose message    
        try {    
            MimeMessage message = new MimeMessage(session);    
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            message.addRecipient(Message.RecipientType.CC,new InternetAddress("henna.gohil@yahoo.com"));
            message.setSubject("Wok N Tandoor Order");    
            message.setContent(dishesString, "text/html");
           //send message  
            Transport.send(message);    
            return getDishesOrder();    
        } catch (MessagingException e) {
            return "Something went wrong:\n" + e.getMessage();
        }    
    }

    /**
     * Creates a new instance of placeOrder
     */
    
    
}
