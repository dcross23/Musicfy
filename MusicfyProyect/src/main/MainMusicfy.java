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
    public final static String pathMusicfyFolder = "MusicfyData";
    public final static String binaryMusicfyFolder = pathMusicfyFolder + File.separator + "binarios";
    public final static String textFilesFolder = pathMusicfyFolder + File.separator + "datos";   
    public final static String exitFilesFolder = pathMusicfyFolder + File.separator + "salida";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        View v = new View();
        
        //1rst - try to load binary Musicfy
        if(!v.loadMusicfy(binaryMusicfyFolder)){
            System.err.println("[ERROR] Can not load binary file");
            
            //2nd - try to load text files
            if(!v.importTextFiles(textFilesFolder)){
                System.err.println("[ERROR] Can not load text files");
                System.err.println("[ERROR] Musicfy can not be loaded");
                
                //3rd - ask to load or not random values
                if(!v.randomLoadingOption()){
                    System.out.println("\nFinalizando programa....");
                    System.exit(1);
                }
                
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
