/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    @Resource(name="wokntandoor")
    private DataSource source;
    /**
     * Creates a new instance of WokNTandoorDB
     */
    public WokNTandoorDB() {
    }
    
    public void getMenuItems(String subMenu){
        
    }
    
}
