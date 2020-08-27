package view;

import com.coti.tools.Esdia;
import controller.Controller;
import data.Album;
import data.PlayList;
import data.Song;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import main.MainMusicfy;

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
        boolean exit = false;
        
        do{
            System.out.println(menu);
            opc = Esdia.readString("Introduzca una opción", options);
            switch(opc){
                case "1":  break;
                
                case "2": archivesOption(); break;
                 
                case "3": albumOption();    break;
                
                case "4":  break;
                
                case "5": playListOption(); break;
                
                case "6": songsOption();    break;
                
                case "s": exit = Esdia.yesOrNo("¿Seguro que quiere salir del programa?");
                          break;
            }
        }while(!exit);
        System.out.println("\nFinalizando programa....");
    }
    
    
    /*=================================================================================*/
    /*                                    OPTIONS                                      */
    /*=================================================================================*/
    
    /*=================    ARCHIVES OPTION     ==================*/
    /**
     * New menu that gives the option to export artist (as a column format document) or albums (as a HTML table). 
     */
    private void archivesOption(){
        System.out.println("\nHas elegido la opcion \"Archivos\" ");
        System.out.println("\n=========================================="
                         + "\n                ARCHIVOS"
                         + "\n=========================================="
                         + "\n1)Exportar artistas (formato en columnas)"
                         + "\n2)Exportar albumes  (formato tabla HTML)"
                         + "\n3)Volver"
                         + "\n==========================================");
        
        String opc;
        String[] options = {"1","2","3"};
        opc = Esdia.readString("Introduzca una opción", options);
        switch(opc){
            case "1": 
                    try{
                        controller.exportArtistsColumnFormat(MainMusicfy.exitFilesFolder);
                    }catch(FileNotFoundException e){
                        System.err.println("[ERROR] no se ha podido exportar el archivo");
                        System.err.println("[ERROR] Exception:"+e);
                        break;
                    }
                    System.out.println("\n[INFO] El archivo se ha exportado correctamente");
                    break;   
                    
            case "2": 
                    try{
                        controller.exportAlbumsHTMLTable(MainMusicfy.exitFilesFolder);
                    }catch(FileNotFoundException e){
                        System.err.println("[ERROR] no se ha podido exportar el archivo");
                        System.err.println("[ERROR] Exception:"+e);
                        break;
                    }
                    System.out.println("\n[INFO] El archivo se ha exportado correctamente");
                    break;  

            case "3": break;
        }
    } 
    
    
    
    /*=================    ARCHIVES OPTION     ==================*/
    /**
     * New menu that gives several options to work with Albums.
     */
    private void albumOption(){
        System.out.println("\nHas elegido la opcion Album");
        System.out.println("\n=========================================="
                         + "\n                 ALBUM"
                         + "\n=========================================="
                         + "\n1)Altas"
                         + "\n2)Bajas"
                         + "\n3)Modificaciones"
                         + "\n4)Consulta"
                         + "\n5)Volver"
                         + "\n==========================================");

        String[] options = {"1","2","3","4","5"};
        String opc = Esdia.readString("Introduzca una opción", options);
        switch(opc){
            case "1": this.addAlbum(); break;

            case "2": this.removeAlbum(); break;

            case "3": this.modifyAlbum(); break;
            
            case "4": this.checkAlbum(); break;
            
            case "5": break;
        }
    }
    
    /**
     * Adds a new Album with the data given by the user.
     */
    private void addAlbum(){
        Album a = Album.createNewAlbum();
        if(controller.registerAlbum(a))
            System.out.println("\nEl álbum se ha registrado correctamente\n");
        else
            System.out.println("\nNo se ha podido registrar el álbum\n");
    }
    
    /**
     * Removes an Album from the registered Album list.
     */
    private void removeAlbum(){
        if(this.printAlbums()){
            String title = Esdia.readString_ne("Introduzca el nombre del álbum a eliminar:"); 
            List<Album> findedAlbums = new ArrayList<>();
            for(Album a: controller.getAlbums()){
                if(title.compareTo(a.getTitle())==0){
                    findedAlbums.add(a);
                }
            }
            
            if(findedAlbums.isEmpty()){
                System.out.println("\nEl álbum introducido no existe");
            
            }else if(findedAlbums.size() == 1){
                if(controller.removeAlbum(findedAlbums.get(0)))
                    System.out.println("\nEl album se ha eliminado correctamente\n");
                else
                    System.out.println("\nNo ha sido posible eliminar el album\n");
            
            }else{
                System.out.println("\n====================================================================================================================================================================================================================================================================");
                System.out.printf("%-40s | %-40s | %-5s | %-15s | %-13s | %-10s | %s","TITULO ALBUM","INTERPRETES ALBUM","AÑO","DURACION","Nº CANCIONES","TIPO","CANCIONES ALBUM");
                System.out.println("\n====================================================================================================================================================================================================================================================================");
                int i=1;
                for(Album a: findedAlbums){
                    System.out.println(i+")");
                    System.out.println(a.toString());
                    if(i<findedAlbums.size())
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    i++;
                }     
                System.out.println("====================================================================================================================================================================================================================================================================");
            
                System.out.println("\nSe encontraron varios albumes con el mismo nombre");
                int albumIndex = Esdia.readInt("\n¿Qué album quiere borrar?",1,findedAlbums.size()+1)-1;
                if(albumIndex < findedAlbums.size()){
                    if(controller.removeAlbum(findedAlbums.get(albumIndex)))
                        System.out.println("\nEl album se ha eliminado correctamente\n");
                    else
                        System.out.println("\nNo ha sido posible eliminar el album\n");
                }
            }
            
        }
    }
    
    /**
     * Modifies an Album from the registered Album list.
     */
    private void modifyAlbum(){
        if(this.printAlbums()){
            String tit = Esdia.readString_ne("Introduzca el nombre del álbum a modificar:");
            
            List<Album> allAlbums = controller.getAlbums();
            if(allAlbums!=null){
                if(!allAlbums.isEmpty()){
                    List<Album> albumesEncontrados = new ArrayList<>();
                    List<Integer> posiciones = new ArrayList<>();

                    int pos = 0;
                    for(Album a: allAlbums){
                        if(tit.compareTo(a.getTitle())==0){
                            albumesEncontrados.add(a);
                            posiciones.add(pos);
                        }
                        pos++;
                    }

                    if(albumesEncontrados.isEmpty()){
                        System.out.println("\nEl álbum introducido no existe");

                    }else if(albumesEncontrados.size() == 1){
                        Album a = controller.modifyAlbum(albumesEncontrados.get(0));
                        if(a!=null){
                            System.out.println("\nEl album se ha modificado correctamente\n");
                            allAlbums.set(posiciones.get(0), a);
                            
                        }else
                            System.out.println("\nNo ha sido posible modificar el album\n");

                    }else{
                        System.out.println("\n====================================================================================================================================================================================================================================================================");
                        System.out.printf("%-40s | %-40s | %-5s | %-15s | %-13s | %-10s | %s","TITULO ALBUM","INTERPRETES ALBUM","AÑO","DURACION","Nº CANCIONES","TIPO","CANCIONES ALBUM");
                        System.out.println("\n====================================================================================================================================================================================================================================================================");
                        int i=1;
                        for(Album a: albumesEncontrados){
                            System.out.println(i+")");
                            System.out.println(a.toString());
                            if(i<albumesEncontrados.size())
                                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            i++;
                        }     
                        System.out.println("====================================================================================================================================================================================================================================================================");

                        System.out.println("\nSe encontraron varios albumes con el mismo nombre");
                        int albumIndex = Esdia.readInt("\n¿Qué album quiere modificar?",1,albumesEncontrados.size());

                        Album a = controller.modifyAlbum( albumesEncontrados.get(albumIndex - 1) );
                        if(a!=null){
                            System.out.println("\nEl album se ha modificado correctamente\n");
                            allAlbums.set(posiciones.get(albumIndex - 1), a);
                            controller.setAlbums(allAlbums);
                            
                        }else
                            System.out.println("\nNo ha sido posible modificar el album\n");
                    }

                }else{
                    System.out.println("\nNo hay albumes registrados");
                }
            }else{
                System.out.println("\nNo se han podido obtener los Albums");
            }
            
        }
    }
    
    /**
     * Prints one selected Album from the general registered Album list.
     */
    private void checkAlbum(){
        System.out.println("\n=========================================");
        System.out.println("\tALBUMES DISPONIBLES");
        System.out.println("=========================================");
        
        List<Album> allAlbums = new ArrayList<>(controller.getAlbums());
        if(!allAlbums.isEmpty()){
            allAlbums.sort(Comparator.comparing(Album::getTitle));
            for(Album a : allAlbums){
                System.out.println("  "+a.getTitle());
            }
            System.out.println("=========================================");
            
            String tit = Esdia.readString_ne("\nIntroduzca el nombre del álbum a mostrar:");    
            List<Album> al = controller.checkAlbum(tit);
            if(al != null && !al.isEmpty()){
                System.out.println("\n====================================================================================================================================================================================================================================================================");
                System.out.printf("%-40s | %-40s | %-5s | %-15s | %-13s | %-10s | %s","TITULO ALBUM","INTERPRETES ALBUM","AÑO","DURACION","Nº CANCIONES","TIPO","CANCIONES ALBUM");
                System.out.println("\n====================================================================================================================================================================================================================================================================");
                int i=1;
                for(Album a: al){
                    System.out.println(a.toString());
                    if(i<al.size())
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    i++;
                } 
                    
                System.out.println("====================================================================================================================================================================================================================================================================");
                
            }else{
                System.out.println("\nNo se ha encontrado ningún álbum con ese nombre\n");
               
            }    
        
        }else
            System.out.println("\nNo hay albumes registrados");
    }
    
    /**
     * Prints all registered Albums
     * @return - true if there is no error
     */
    private boolean printAlbums(){
        List<Album> al = controller.getAlbums();
        if(al!=null){
            System.out.println("\n===============================================================================================================================================================================================================================================");
            System.out.printf("%-40s | %-40s | %-5s | %-15s | %-13s | %-10s | %s","TITULO ALBUM","INTERPRETES ALBUM","AÑO","DURACION","Nº CANCIONES","TIPO","CANCIONES ALBUM");
            System.out.println("\n===============================================================================================================================================================================================================================================");
            
            int i=1;
            if(!al.isEmpty()){
                for(Album a: al){
                    if(a!=null){
                        System.out.println(a.toString());
                        if(i < al.size())
                            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    }
                    i++;
                }     
                System.out.println("\n===============================================================================================================================================================================================================================================");
                
            }else{
                System.out.println("\nNo hay Albums registrados");
            }
            return true;
        }else{
            System.out.println("\nNo se han podido obtener los Albums");
            return false;
        }
    }
    
    
    /*=================    PLAYLIST OPTION     ==================*/
    /**
     * New menu that gives several options to work with PlayLists.
     */
    private void playListOption(){
        System.out.println("\nHas elegido la opcion PlayList");
        System.out.println("\n=========================================="
                         + "\n               PLAYLIST"
                         + "\n=========================================="
                         + "\n1)Añadir playlist"
                         + "\n2)Eliminar playlist"
                         + "\n3)Eliminar cancion"
                         + "\n4)Añadir cancion"
                         + "\n5)Volver"
                         + "\n==========================================");
                
        String[] opciones = {"1","2","3","4","5"};
        String opc = Esdia.readString("Introduzca una opción", opciones);
        switch(opc){
            case "1": this.addPlayList(); break;
            
            case "2": this.removePlayList(); break;

            case "3": this.removeSongFromPlayList(); break;

            case "4": this.addSongToPlayList(); break;
            
            case "5": break;
        }
    }
    
    /**
     * Adds a new playlist with a given name and "random" registered songs.
     */
    private void addPlayList(){
        PlayList p = PlayList.createNewPlayList(controller.getSongs());
        if(controller.registerPlayList(p))
            System.out.println("\nLa PlayList se ha generado correctamente\n");
        else
            System.out.println("\nNo ha sido posible generar la PlayList\n");
    }
    
    /**
     * Removes a playlist from the registered PlayLists list.
     */
    private void removePlayList(){
        int numPlayLists = this.printPlayLists() - 1;
        
        if(numPlayLists > 0){
            int playListIndex = Esdia.readInt("\n¿Qué playlist desea modificar? ("+(numPlayLists+1)+" para cancelar)", 1, numPlayLists+1) - 1;
            if(playListIndex < numPlayLists){
                if(controller.removePlayList(controller.getPlayLists().get(playListIndex)))
                    System.out.println("\nLa PlayList se ha eliminado correctamente\n");
                else
                    System.out.println("\nNo ha sido posible eliminar la PlayList\n");
            }
        
        }else{
            System.out.println("\nNo hay playlists que eliminar");
        }
    }
    
    /**
     * Removes a song from a registered playlist.
     */
    private void removeSongFromPlayList(){
        int numPlayLists = this.printPlayLists() - 1;
        
        if(numPlayLists > 0){
            int playListIndex = Esdia.readInt("\n¿Qué playlist desea modificar?", 1, numPlayLists) - 1;
            if(controller.getPlayLists().get(playListIndex).getSongs().isEmpty()){
                System.out.println("\nLa PlayList esta vacia");
            
            }else{
                String titleToFind = Esdia.readString_ne("\n¿Qué canción desea eliminar?");

                List<Song> allSongs = controller.getSongs();
                if(allSongs!=null && !allSongs.isEmpty()){

                    //Find all songs with the given title from all registered songs
                    List<Song> findedSongs = new ArrayList<>();
                    for(Song s: allSongs){
                        if(titleToFind.compareTo(s.getTitle()) == 0){
                            findedSongs.add(s);
                        }
                    }

                    if(findedSongs.isEmpty()){
                        System.out.println("\nLa canción introducida no existe");

                    }else{
                        //If there is only 1 song, just removes it 
                        if(findedSongs.size() == 1){
                            if(controller.removeSong(playListIndex, findedSongs.get(0)))
                                System.out.println("\nLa cancion ha sido eliminada correctamente\n");
                            else
                                System.out.println("\nNo ha sido posible eliminar la cancion\n"); 

                        //If there is more than one song, it will display them and gives the user the option to decide
                        // which one he wants to remove
                        }else{
                            System.out.println("\nSe encontraron varias canciones con el mismo nombre");
                            System.out.println("\n====================================================================================================================================================================================================================================================================");
                            System.out.println("    CANCIONES ENCONTRADAS");
                            System.out.println("\n====================================================================================================================================================================================================================================================================");

                            int songIndex, i = 1;
                            for(Song s: findedSongs){
                                System.out.println((i++) + ") "+s.toString());
                            }
                            System.out.println("\n====================================================================================================================================================================================================================================================================");

                            songIndex = Esdia.readInt("\n¿Qué canción desea eliminar? ("+(findedSongs.size()+1)+" to cancel)", 1, findedSongs.size()+1);
                            if(songIndex != findedSongs.size()+1){
                                Song s = findedSongs.get(songIndex - 1);

                                if(controller.removeSong(playListIndex, s))
                                    System.out.println("\nLa cancion ha sido eliminada correctamente\n");
                                else
                                    System.out.println("\nNo ha sido posible eliminar la canción\n");
                            }


                            //If any playList where the song was registered remains empty, it gives the option to the user
                            // to remove or not the playlist 
                            PlayList pl = controller.getPlayLists().get(playListIndex);  
                            if(pl!=null && pl.getSongs().isEmpty()){
                                System.out.println("\nLa PlayList "+pl.getName()+" no contiene canciones");
                                if(Esdia.yesOrNo("¿Desea eliminarla?")){
                                    if(controller.removePlayList(pl))
                                        System.out.println("\nLa PlayList se ha eliminado correctamente\n");
                                    else
                                        System.out.println("\nNo ha sido posible eliminar la PlayList\n");
                                }
                            }
                        }
                    }
                }
            } 
        }
    }
    
    /**
     * Adds a song to a registered playlist.
     */
    private void addSongToPlayList(){
        int numPlayLists = this.printPlayLists() - 1;
        
        if(numPlayLists > 0){
            int playListIndex = Esdia.readInt("\n¿Qué playlist desea modificar?", 1, numPlayLists) - 1;
            System.out.println("\n==================================================================================================================================================================================================================================================");
            System.out.println("    CANCIONES DISPONIBLES");
            System.out.println("==================================================================================================================================================================================================================================================");

            List<Song> allSongs = controller.getSongs();
            if(allSongs!=null){
                if(!allSongs.isEmpty()){
                    
                    //Shows all registered songs to give the user the option to select which ones he wants
                    // to add to his playlist
                    for(Song s: allSongs){
                        System.out.println("    "+s.toString());
                    }
                    String titleToFind = Esdia.readString_ne("\n¿Qué canción desea añadir? Introduzca el titulo:");
                    
                    List<Song> cancEncontradas = new ArrayList<>();
                    for(Song s: allSongs){
                        if(titleToFind.compareTo(s.getTitle()) == 0){
                            cancEncontradas.add(s);
                        }
                    } 
                    
                    if(cancEncontradas.isEmpty()){
                        System.out.println("\nLa canción introducida no existe");
                        
                        //If there is only 1 song, just adds it
                    }else if(cancEncontradas.size() == 1){
                        if(controller.addSong(playListIndex, cancEncontradas.get(0)))
                            System.out.println("\nLa cancion ha sido añadida correctamente\n");
                        else
                            System.out.println("\nNo ha sido posible añadir la cancion(puede estar repetida)\n");
                        
                        
                        //If there is more than one song, it will display them and gives the user the option to decide
                        // which one he wants to add
                    }else{
                        System.out.println("\nSe encontraron varias canciones con el mismo nombre");
                        System.out.println("\n====================================================================================================================================================================================================================================================================");
                        System.out.println("    CANCIONES ENCONTRADAS");
                        System.out.println("====================================================================================================================================================================================================================================================================");
                        
                        int i = 1;
                        for(Song s: cancEncontradas){
                            System.out.println((i++) +") "+s.toString());
                        }
                        System.out.println("\n====================================================================================================================================================================================================================================================================");
                        
                        int songIndex = Esdia.readInt("\n¿Qué canción desea añadir?",1,cancEncontradas.size());
                        Song s = cancEncontradas.get(songIndex - 1);
                        if(controller.addSong(playListIndex, s))
                            System.out.println("\nLa cancion ha sido añadida correctamente\n");
                        else
                            System.out.println("\nNo ha sido posible añadir la cancion(puede estar repetida)\n");
                    }
                
                }else{
                    System.out.println("\nNo hay canciones registradas para añadir");
                }
            }
        }
    }
    
    /**
     * Prints all registered PlayLists
     * @return   0: playLists can not be obtained from Musicfy
     * @return   1: there are no registered playLists
     * @return  >1: number of registered playLists
     */
    private int printPlayLists(){
        List<PlayList> pl = controller.getPlayLists();
        if(pl != null){
            System.out.println("\n===============================================================================================================================================================================================================================================");
            System.out.printf("%s| %-30s| %-41s%-7s%-15s%s","Nº","TITULO PLAYLIST","TITULOS CANCIONES","AÑO","DURACION","INTERPRETES");
            System.out.println("\n===============================================================================================================================================================================================================================================");            
            
            int i=1;
            if(!pl.isEmpty()){
                for(PlayList p: pl){
                    System.out.print(i+") ");
                    System.out.println(p.toString());
                    if(i<pl.size())
                        System.out.println("  ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    i++;
                }
                System.out.println("\n===============================================================================================================================================================================================================================================");
           
            }else{
                System.out.println("\nNo hay PlayLists registradas");
            }
            
            return i;
             
        }else{
            System.out.println("No se han podido obtener las PlayLists");       
            return 0;
        }
    }
    
    
    /*===================    SONG OPTION     ====================*/
    /**
     * Prints all Musicfy songs ordered by a given order (with a Comparator).
     */
    private void songsOption(){
        System.out.println("\nHas elegido la opcion \"Canciones\" ");
        
        Comparator comparator = Comparator.comparing(Song::getYear).thenComparing(Song::getTitle);
        List<Song> songs = controller.sortSongs(comparator);
        
        if(songs!=null){
            System.out.println("\n============================================================================================================================================================================================================================================");
            System.out.printf("%-40s|%-5s|%-15s|%s","TITULO","AÑO","DURACION","INTERPRETES"); 
            System.out.println("\n============================================================================================================================================================================================================================================");
                
            if(songs.isEmpty()){
                System.out.println("No hay canciones registradas");
            
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
