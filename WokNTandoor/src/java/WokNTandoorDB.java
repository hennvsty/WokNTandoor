/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Adonias
 */
@Named(value = "wokNTandoorDB")
@SessionScoped
public class WokNTandoorDB implements Serializable {
    /**
     * Creates a new instance of WokNTandoorDB
     */
    public WokNTandoorDB() {
    }
    
}