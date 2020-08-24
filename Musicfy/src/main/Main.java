/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.Serializable;
import view.View;

/**
 *
 * @author David
 */
public class Main implements Serializable {

    final static String binaryMusicfyFolder = "musicfy"+File.separator+"binarios";
    final static String textFilesFolder = "musicfy"+File.separator+"datos";
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        View v = new View();
        
        if(!v.loadMusicfy(binaryMusicfyFolder)){
            System.err.println("[INFO] Can not load binary file");
            
            if(!v.importTextFiles(textFilesFolder)){
                System.err.println("[INFO] Can not load text files");
                System.exit(1);
                
            }else System.out.println("[INFO] Text files loaded");
            
        }else System.out.println("[INFO] Binary file loaded");
        
        if(v.saveMusicfy(binaryMusicfyFolder)) System.out.println("[INFO] Musicfy saved succesfully");
        else                                   System.err.println("[INFO] Can not save Musicfy");    
        
    }
    
}
