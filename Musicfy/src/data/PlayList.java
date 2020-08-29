package data;

import com.coti.tools.Esdia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author David
 */
public class PlayList implements Serializable {
    /**
     * PlayList name.
     */
    private String name;
    
    /**
     * PlayList song list.
     */
    private List<Song> songs;
    
    /*======================================================*/
    /*                   CONSTRUCTORS                       */
    /*======================================================*/
    /**
     * Constructor: creates new PlayList with all given parameters.
     * @param name
     * @param songs 
     */ 
    public PlayList(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }
    
    
    /*======================================================*/
    /*                GETTERS AND SETTERS                   */
    /*======================================================*/
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
   
    /*======================================================*/
    /*                      METHODS                         */
    /*======================================================*/
    /**
     * Creates a new PlayList with "random" registered songs.
     * Asks the user the name of the PlayList and how many songs
     * does he wants in it.
     * @param disponibleSongs - List of registered songs
     * @return - The new PlayList
     */
    public static PlayList createNewPlayList(List<Song> disponibleSongs){
        Random r = new Random();
        PlayList pl;
        try{
            String n = Esdia.readString_ne("\nIntroduzca el titulo de la PlayList:");
            
            List<Song> canc = new ArrayList<>();  
            int nCanc = Esdia.readInt("\nIntroduzca el numero de canciones que tendrá la PlayList", 1, disponibleSongs.size());
           
            for(int i=0; i<nCanc; i++){
                Song s = disponibleSongs.get(r.nextInt(disponibleSongs.size()));
                if(s != null)
                    canc.add(s);
            }
                
            pl = new PlayList(n,canc);
        }catch(Exception e){
            pl = null;
        }
        return pl;
    }
    
    /**
     * Creates a new PlayList with random values.
     * To select songs, it needs the full song list already generated in 
     * randomValues Album method and randomValues Song method.
     * @param allSongs
     * @return 
     */
    public static PlayList randomValues(List<Song> allSongs){
        Random r = new Random();
        
        PlayList pl;
        try{
            Resources resources = new Resources();
            List<String> playListsNames = resources.getPlayListNames();
            String name;
            if(playListsNames!=null && !playListsNames.isEmpty()){
                name = playListsNames.get(r.nextInt(playListsNames.size()));
            }else{
                name = "error";
            }
            
            //1-10 Songs in each playlist
            int nCanc = r.nextInt(10)+1;
            List<Song> songs = new ArrayList<>(); 
            List<Song> totalSongs = new ArrayList(allSongs);
            
            if(totalSongs!=null && !totalSongs.isEmpty()){
                int pos;
                for(int i=0; i<nCanc; i++){
                    pos = r.nextInt(totalSongs.size());
                    songs.add(totalSongs.get(pos));
                    totalSongs.remove(pos);
                }
            }
            
            pl = new PlayList(name,songs);
            
        }catch(Exception e){
            pl=null;
        }
     
        return pl;    
    }
    
    /**
     * Returns a string representation of the PlayList (separates name and songs). 
     * @return 
     */
    @Override
    public String toString(){
             
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s",this.name)).append(" | ");
        
        if(this.songs!=null && !this.songs.isEmpty()){
            sb.append(this.songs.get(0).toString());
            for(Song s: this.songs.subList(1, this.songs.size())){
                sb.append("\n").append(String.format("%33s | ","")).append(s.toString());
            }
        }
        return sb.toString();
    }
}
