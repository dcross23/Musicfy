package data;

import com.coti.tools.Esdia;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 *
 * @author David
 */
public class Song implements Serializable {
    /**
     * Songs title.
     */
    private String title;
    
    /**
     * Year when the song was released.
     */
    private int year;
    
    /**
     * Song´s duration.
     */
    private String duration;
    
    /**
     * List of the names of the artists that created/perform the song. 
     */
    private List<String> interpreters;
    

    /*======================================================*/
    /*                   CONSTRUCTORS                       */
    /*======================================================*/
    /**
     * Constructor: creates new song with all given parameters.
     * @param title
     * @param year
     * @param duration
     * @param interpreters 
     */
    public Song(String title, int year, String duration, List<String> interpreters){
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.interpreters = interpreters;
    }
    
    /**
     * Constructor: creates new song with just only his title and year.
     * @param title
     * @param year 
     */
    public Song(String title, int year){
        this.title = title;
        this.year = year;
        this.duration = " ";
    }
    
    
    /*======================================================*/
    /*                GETTERS AND SETTERS                   */
    /*======================================================*/    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public List<String> getInterpreters() {
        return interpreters;
    }
    public void setInterpreters(List<String> interpreters) {
        this.interpreters = interpreters;
    }
    
   
    
    /*======================================================*/
    /*                      METHODS                         */
    /*======================================================*/
    /**
     * Creates a Song by asking the user for the data.
     * @param interp - List of the song interpreters
     * @return The new Song or null if there is an error
     */
    public static Song createNewSong(List<String> interp){
        Song song;
        try{
            String t = Esdia.readString_ne("\nIntroduzca el titulo de la cancion:");
            int a = Esdia.readInt("\nIntroduzca el año de la cancion:",1,Integer.MAX_VALUE); 
            
            System.out.println("\nIntroduzca la duración de la cancion:");
            int min = Esdia.readInt("Introduzca los minutos:",0,59);
            int seg = Esdia.readInt("Introduzca los segundos:",0,59);      
            String d =min+" min "+seg+" seg";
            
            song = new Song(t,a,d,interp);
            
        }catch(Exception e){
            song = null;
            System.err.println("[ERROR] No se ha podido crear la cancion");
            System.err.println("Exception:"+e);
        }
        
        return song;
    }
    
    /**
     * Creates a Song with random values.
     * Some of the parameters from the album data are needed to know the number 
     * of songs to create for that album, its duration or its interpreters, all
     * already generate randomly in randomValues Album method.
     * @param interp
     * @param nc
     * @param albumDuration
     * @return 
     */
     public static Song randomValues(List<String> interp, int nc, int albumDuration){
        if(interp == null || nc<=0 || albumDuration <=0) return null;
        
        Random r = new Random();
        Song so;
        try{
            Resources resources = new Resources();
            List<String> songTitles = resources.getSongTitles();
            String title;
            if(songTitles!=null && !songTitles.isEmpty()){
                title = songTitles.get(r.nextInt(songTitles.size()));
            }else{
                title = "error";
            }
            
            //Random year between 1990 and 2020
            //Año entre 1990 y 2019
            int ye = r.nextInt(120)+1900;

            //Song duration is being calculated with the album duration divided
            // by the number of songs in the album, so each song will have the 
            // same duration
            int durCancion = albumDuration / nc;
            int min = durCancion / 60;
            int sec = durCancion % 60;  
            String d = min+" min "+sec+" sec";
                        
            so = new Song(title,ye,d,interp);
            
        }catch(Exception e){
            so=null;
        }
        
        return so;
    }
    
    
    /**
     * Returns a string representation of the Song.
     * @return  String representing the song
     */
    @Override
    public String toString(){
        return this.toDelimitedString(" ");
    }
    
    /**
     * Returns a delimited string representation of the Song.
     * @param del Delimiter used 
     * @return 
     */
    public String toDelimitedString(String del){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-40s" + del, this.title) ).append(String.format("%-5s"+del,this.year) ).append(String.format("%-15s"+del,this.duration) );
        
        if(this.interpreters!=null && !this.interpreters.isEmpty()){  
            sb.append(String.format("%-30s",this.interpreters.get(0)));
            
            for(String i: this.interpreters.subList(1, this.interpreters.size())){
                sb.append(", ").append(String.format("%-30s",i));
            }
        
        }else{
            sb.append(" ");
        }
        return sb.toString();  
    }
    
    
}
