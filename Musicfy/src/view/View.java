/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.coti.tools.Esdia;
import controller.Controller;
import data.Song;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author David
 */
public class View implements Serializable {
    /**
     * Controller used to comunicate with Musicfy.
     */
    Controller controller = new Controller();
    
    /*=================================================================================*/
    /*                                  BINARY FILE                                    */
    /*=================================================================================*/
    /**
     * Loads the Musicfy´s binary file as an instance.
     * @param location
     * @return 
     */
    public boolean loadMusicfy(String location){
        return controller.load(location);
    }
    
    /**
     * Saves the Musicfy´s object instance as a binary file.
     * @param location
     * @return 
     */
    public boolean saveMusicfy(String location){
        return controller.save(location);
    }
    
    
    /*=================================================================================*/
    /*                                   TEXT FILES                                    */
    /*=================================================================================*/
    /**
     * Loads texts files to create Musicfy´s new instance.
     * @param location
     * @return 
     */
    public boolean importTextFiles(String location){
        return controller.importTextFiles(location);
    }
    
    
    /*=================================================================================*/
    /*                                      MENU                                       */
    /*=================================================================================*/
    /**
     * Runs the application with a given menu as String.
     * @param menu 
     */
    public void runMenu(String menu){
        String opc;
        String[] options = {"1","2","3","4","5","6","s"};
        boolean salir = false;
        
        do{
            System.out.println(menu);
            opc = Esdia.readString("Introduzca una opción", options);
            switch(opc){
                case "1":  break;
                
                case "2":  break;
                 
                case "3":  break;
                
                case "4":  break;
                
                case "5":  break;
                
                case "6": songsOption(); break;
                
                case "s": salir = Esdia.yesOrNo("¿Seguro que quiere salir del programa?");
                          break;
            }
        }while(!salir);
        System.out.println("\nFinalizando programa....");
    }
    
    
    /*=================================================================================*/
    /*                                    OPTIONS                                      */
    /*=================================================================================*/
    /**
     * Prints all Musicfy songs ordered by a given order (with a Comparator).
     */
    private void songsOption(){
        Comparator comparator = Comparator.comparing(Song::getYear).thenComparing(Song::getTitle);
        
        List<Song> songs = controller.sortSongs(comparator);
        
        if(songs!=null){
            System.out.println("\n============================================================================================================================================================================================================================================");
            System.out.printf("%-40s|%-5s|%-15s|%s","TITULO","AÑO","DURACION","INTERPRETES"); 
            System.out.println("\n============================================================================================================================================================================================================================================");
                
            if(songs.isEmpty()){
                System.out.println("No hay canciones disponibles");
            
            }else{
                for(Song s: songs){
                    System.out.println(s.toDelimitedString("|"));
                }
            }

            System.out.println("\n============================================================================================================================================================================================================================================");
        
        }else
            System.out.println("No se han podido obtener las canciones");
        
    }
            
    
    
}
