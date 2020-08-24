/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Musicfy;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class Controller implements Serializable{
    /**
     * Musicfy`s data object 
     */
    Musicfy m;
    
    
    /**
     * Loads binary file
     * @param location
     * @return true if binary file is loaded successfully
     */
    public boolean load(String location){
        File binaryMusicfy = new File(location + File.separator + "musicfy.bin");
        if(!binaryMusicfy.exists()){
            m = new Musicfy(); 
            return false;
        
        }else{  
            try{
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream( new FileInputStream(binaryMusicfy) ));
                m = (Musicfy) ois.readObject();
                ois.close(); 
                
            }catch(IOException | ClassNotFoundException e) {
                System.err.println("ERROR: no se ha podido cargar el fichero binario");
                System.err.println("Exception:" + e);
                m = new Musicfy(); 
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Saves binary file
     * @param location
     * @return true if binary file is saved successfully
     */
    public boolean save(String location){
        File binaryMusicfy = new File(location + File.separator + "musicfy.bin");
        
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(binaryMusicfy)));
            oos.writeObject(m);
            oos.close();

        }catch(IOException e) {
            System.err.println("ERROR: no se ha podido guardar el fichero");
            System.err.println("Exception:"+e);
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Imports text files in case binary file doesnt exits
     * @param location
     * @return true if text files are loaded successfully 
     */
    public boolean importTextFiles(String location){
        boolean b = m.importTextFiles(location);
        return b;
    }
    
}
