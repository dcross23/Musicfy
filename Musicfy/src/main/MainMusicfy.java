package main;

import java.io.File;
import java.io.Serializable;
import view.View;

/**
 *
 * @author David
 */
public class MainMusicfy implements Serializable {

    /**
     * Different paths to input/output folders.
     */
    public final static String binaryMusicfyFolder = "musicfy" + File.separator + "binarios";
    public final static String textFilesFolder = "musicfy" + File.separator + "datos";   
    public final static String exitFilesFolder = "musicfy" + File.separator + "salida";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        View v = new View();
        
        if(!v.loadMusicfy(binaryMusicfyFolder)){
            System.err.println("[ERROR] Can not load binary file");
            
            if(!v.importTextFiles(textFilesFolder)){
                System.err.println("[ERROR] Can not load text files");
                System.err.println("[ERROR] Musicfy can not be loaded");
                System.exit(1);
                
            }else 
                System.out.println("[INFO] Text files loaded");
            
        }else 
            System.out.println("[INFO] Binary file loaded");
        
        
        v.runMenu("\n==========================="
                + "\n          MUSICFY"
                + "\n==========================="
                + "\n1.Generación aleatoria"
                + "\n2.Archivos"
                + "\n3.Álbum"
                + "\n4.Artista"
                + "\n5.PlayList"
                + "\n6.Canciones"
                + "\ns.Salir"
                + "\n==========================="); 
        

        
        if(v.saveMusicfy(binaryMusicfyFolder)) 
            System.out.println("[INFO] Musicfy saved succesfully");
        else                                   
            System.err.println("[ERROR] Can not save Musicfy");    
        
    }
    
}
