/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Adonias
 */
@ManagedBean(name = "adminControls")
@SessionScoped
public class adminControls implements Serializable{
    //@Resource(name="jdbc/wokntandoordb")
    private static final String ACCESS_TOKEN = "HG3Mm8N5SwAAAAAAAAAAOTIb_WJWNTQZ7WYZRdLPiEZcg1mM8t11b_eOcC4OUhBm";
    private DataSource source; 
    private String outputMessage;
    private String action;
    
    private class DishData {
        public String name = "";
        public double price = 0.0;
        public double specialPrice = 0.0;
        public String description = "";
        public String picture = "";
        public String subMenu = "";
        
        public void reset() {
            name = "";
            price = 0.0;
            specialPrice = 0.0;
            description = "";
            picture = "";
            subMenu = "";
        }
    }
    
    DishData addData = new DishData();
    DishData editData = new DishData();
    
    private UploadedFile uploadedPicture;
    private String picturePath;
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://aau3z4pq3psz62.cx2uxgwhz5kj.us-east-1.rds.amazonaws.com:3306/ebdb?zeroDateTimeBehavior=convertToNull";
    private final String USER = "wokntandoor";
    private final String PASS = "kickme531";
    
    /**
     * Creates a new instance of adminControls
     */
    
    public adminControls() {
        outputMessage = "";   
        action = "";
        picturePath = "";
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
    
    public String getAddName() { return addData.name; }
    public double getAddPrice() { return addData.price; }
    public double getAddSpecialPrice() { return addData.specialPrice; }
    public String getAddDescription() { return addData.description; }
    public String getAddPicture() { return addData.picture; }
    public String getAddSubMenu() { return addData.subMenu; }
    
    public void setAddName(String name) { addData.name = name; }
    public void setAddPrice(double price) { addData.price = price; }
    public void setAddSpecialPrice(double specialPrice) { addData.specialPrice = specialPrice; }
    public void setAddDescription(String description) { addData.description = description; }
    public void setAddPicture(String picture) { addData.picture = picture; }
    public void setAddSubMenu(String subMenu) { addData.subMenu = subMenu; }
    
    public String getEditName() { return editData.name; }
    public double getEditPrice() { return editData.price; }
    public double getEditSpecialPrice() { return editData.specialPrice; }
    public String getEditDescription() { return editData.description; }
    public String getEditPicture() { return editData.picture; }
    public String getEditSubMenu() { return editData.subMenu; }
    
    public void setEditName(String name) { editData.name = name; }
    public void setEditPrice(double price) { editData.price = price; }
    public void setEditSpecialPrice(double specialPrice) { editData.specialPrice = specialPrice; }
    public void setEditDescription(String description) { editData.description = description; }
    public void setEditPicture(String picture) { editData.picture = picture; }
    public void setEditSubMenu(String subMenu) { editData.subMenu = subMenu; }
    
    public void clearAddData() {
        addData.reset();
    }
    
    public void clearEditData() {
        editData.reset();
    }
    
    public String getOutputMessage() {
        return outputMessage;
    }
    
    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }
    
    public UploadedFile getUploadedPicture()
    {
        return uploadedPicture;
    }

    public void setUploadedPicture(UploadedFile uploadedPicture)
    {
        this.uploadedPicture = uploadedPicture;
    }
    
    public void handleFileUpload(FileUploadEvent event) throws IOException, DbxException{
        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
            Locale.getDefault().toString());

        DbxClient client = new DbxClient(config, ACCESS_TOKEN);

        InputStream inputStream = null;
        try {
            UploadedFile uploadedfile = event.getFile();
            inputStream = uploadedfile.getInputstream();
            DbxEntry.File uploadedFile = client.uploadFile("/images/test.jpg",
                DbxWriteMode.add(), uploadedfile.getSize(), inputStream);
            String sharedUrl = client.createShareableUrl("/images/test.jpg");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(adminControls.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DbxException ex) {
            Logger.getLogger(adminControls.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(adminControls.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            inputStream.close();
        }
        
        /*
        UploadedFile uploadedFile = event.getFile();
<<<<<<< HEAD
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images");
        String fileName = uploadedFile.getFileName();
        String contentTypes = uploadedFile.getContentType();
        try (InputStream input = uploadedFile.getInputstream()){
            //OutputStream out = new FileOutputStream(file);
            File tempFile = new File("/images", event.getFile().getFileName());
=======
        InputStream input = uploadedFile.getInputstream();
        OutputStream output = new FileOutputStream(new File("/images/","newPicture"));
        try{
            BufferedImage image = ImageIO.read(input);
            ImageIO.write(image, "jpg", output);
            IOUtils.copy(input, output);
>>>>>>> origin/master
            FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        catch(Exception e){
            FacesMessage message = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
<<<<<<< HEAD
=======
        finally{
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }

        
>>>>>>> origin/master
        */
    }
     
    // Used to populate the dishes menu in the admin control page
    // Work in progress
    public String getEditableItems(String subMenu, String catID) throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // admin-test: copy of dishes database to test admin queries
        final String TEST_URL = "jdbc:mysql://aau3z4pq3psz62.cx2uxgwhz5kj.us-east-1.rds.amazonaws.com:3306/admin_test?zeroDateTimeBehavior=convertToNull";
        Connection conn = DriverManager.getConnection(TEST_URL, USER, PASS);
        //Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
        String name, description, picPath;
        double price;
        try{
            PreparedStatement stat = conn.prepareStatement(
                  "SELECT DishName, DishPrice, DishDescription, DishPicture FROM Dishes WHERE SubMenu = ?");
            stat.setString(1, subMenu);
            ResultSet result = stat.executeQuery();
            String allItemswHTML = "";
            
            int orderID = 0;
            while (result.next()) {
                name = result.getString("DishName");
                price = result.getDouble("DishPrice");
                picPath = result.getString("DishPicture");
                if(picPath == null){
                    picPath = "";
                }
                description = result.getString("DishDescription");
                allItemswHTML += "<tr>" +
  "                        <td class=\"menu-item-thumb\"><img src=\""+picPath+"\" alt=\"\" style=\"width:100px;height:100px;\"/></td>" +
  "                        <td class=\"menu-item-info\">" +
  "                            <div class=\"w3-large w3-padding-4\" id=\"n"+orderID+"\">" + name + "</div>"; 
                if(description!= null) {
                    allItemswHTML += "<div class=\"w3-small w3-padding-4\">" + description + "</div>";
                } else {
                    allItemswHTML += "</td>";
                }
                allItemswHTML += "<td class=\"menu-item-price\">$<span id=\"p"+ orderID + "\">" + String.format("%.2f", price) + "</span></td>";
                allItemswHTML += "<td class=\"w3-large w3-center\">" +
  "                            <button class=\"w3-button w3-transparent w3-text-khaki\" onclick= 'addSubtotal("+orderID+", \""+catID+"\")'>+</button>" +
  "                            <div id=\"q"+orderID+"\" class=\"w3-text-white\">0</div>" +
  "                            <button class=\"w3-button w3-transparent w3-text-khaki\" onclick= 'subtractSubtotal("+orderID+", \""+catID+"\")'>-</button>" +
  "                        </td><td class=\"w3-large\">\n" +
"                            <button class=\"btn btn-default btn-sm\" onclick=\"modifyDish()\"><span class=\"glyphicon glyphicon-pencil\"></span></button>\n" +
"                            <button class=\"btn btn-danger btn-sm\" onclick=\"removeDish()\"><span class=\"glyphicon glyphicon-remove\"></span></button>\n" +
"                        </td></tr>";  
                orderID++;
            }
            conn.close();
            return allItemswHTML;
        }
        catch(Exception e) { return e.getMessage(); }
    }
    
    public void changeDishPrice(String dishName, double dishPrice) throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // admin-test: copy of dishes database to test admin queries
        final String TEST_URL = "jdbc:mysql://aau3z4pq3psz62.cx2uxgwhz5kj.us-east-1.rds.amazonaws.com:3306/admin_test?zeroDateTimeBehavior=convertToNull";
        Connection conn = DriverManager.getConnection(TEST_URL, USER, PASS);
        //Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
    
    public void addDish() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // admin-test: copy of dishes database to test admin queries
        final String TEST_URL = "jdbc:mysql://aau3z4pq3psz62.cx2uxgwhz5kj.us-east-1.rds.amazonaws.com:3306/admin_test?zeroDateTimeBehavior=convertToNull";
        Connection conn = DriverManager.getConnection(TEST_URL, USER, PASS);
        //Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        int result = 0;
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Dishes "
                    + "(DishName, DishPrice, DishSpecialPrice, DishDescription, DishPicture, SubMenu) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");
                    //+ "SELECT ?, ?, ? FROM Dishes "
                    //+ "WHERE (DishName = ?) HAVING count(*) = 0");
                    
            stmt.setString(1, getAddName());
            stmt.setDouble(2, getAddPrice());
            stmt.setDouble(3, getAddSpecialPrice());
            stmt.setString(4, getAddDescription());
            stmt.setString(5, getAddPicture());
            stmt.setString(6, getAddSubMenu());
            result = stmt.executeUpdate(); // not 0 = success
            stmt.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removeDish() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // admin-test: copy of dishes database to test admin queries
        final String TEST_URL = "jdbc:mysql://aau3z4pq3psz62.cx2uxgwhz5kj.us-east-1.rds.amazonaws.com:3306/admin_test?zeroDateTimeBehavior=convertToNull";
        Connection conn = DriverManager.getConnection(TEST_URL, USER, PASS);
        //Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        try {
            PreparedStatement stmt =
                    conn.prepareStatement("DELETE FROM Dishes WHERE DishName = ?");
            stmt.setString(1, getParam(FacesContext.getCurrentInstance(), "name") );
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Example of how to obtain a string passed from JS
    public void showDishName() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String output = params.get("name");
        
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Affected item", output));
    }
    
    private String getParam(FacesContext fc, String param) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(param);
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
                face.getExternalContext().redirect("Admin.xhtml");
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
