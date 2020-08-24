/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class View implements Serializable {
    Controller c = new Controller();
    
    /* Load/save musicfy */
    /**
     * Loads the Musicfy´s binary file as an instance
     * @param location
     * @return 
     */
    public boolean loadMusicfy(String location){
        return c.load(location);
    }
    
    /**
     * Saves the Musicfy´s object instance as a binary file
     * @param location
     * @return 
     */
    public boolean saveMusicfy(String location){
        return c.save(location);
    }
    
    /**
     * Loads texts files to create Musicfy´s new instance
     * @param location
     * @return 
     */
    public boolean importTextFiles(String location){
        return c.importTextFiles(location);
    }
    
    
}
