package data;

import com.coti.tools.Esdia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author David
 */
public class Album implements Serializable{
    /**
     * Album title-
     */
    private String title;
    
    /**
     * Album artists.
     */
    private List<String> interpreters;
    
    /**
     * Year when the album was released.
     */
    private int year;
    
    /**
     * Album duration.
     */
    private String duration;
    
    /**
     * Number of songs the album contains.
     */
    private int numSongs;
    
    /**
     * Album type.
     */
    private String type;
    
    /**
     * Album song list.
     */
    private List<Song> songs;
    
    /*======================================================*/
    /*                   CONSTRUCTORS                       */
    /*======================================================*/
    
    /**
     * Constructor: creates new album with all given parameters.
     * @param title
     * @param interpreters
     * @param year
     * @param duration
     * @param numSongs
     * @param type
     * @param songs 
     */
    public Album(String title, List<String> interpreters, int year, String duration, int numSongs, String type, List<Song> songs) {
        this.title = title;
        this.interpreters = interpreters;
        this.year = year;
        this.duration = duration;
        this.numSongs = numSongs;
        this.type = type;
        this.songs = songs;
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
    public List<String> getInterpreters() {
        return interpreters;
    }
    public void setInterpreters(List<String> interpreters) {
        this.interpreters = interpreters;
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
    public int getNumSongs() {
        return numSongs;
    }
    public void setNumSongs(int numSongs) {
        this.numSongs = numSongs;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
     * Creates an Album from a given delimited String (\t and ; delimiters).
     * @param album
     * @return The new Album or null if there is an error
     */
    public static Album instanceFromString(String album){
        String[] data = album.split("\t");
        Album newAlbum;
        try{
            String t = data[0];
            List<String> i = Arrays.asList(data[1].split(";"));      
            int a = Integer.parseInt(data[2]);
            String d = data[3]; 
            int nc = Integer.parseInt(data[4]);
            String tip = data[5];
            
            List<Song> canc = new ArrayList<>();
            if(!tip.equals("sencillo") || nc>1){
                String[] so = data[6].split(";");
            
                for(String s : so){
                    if(!s.isEmpty()){
                        Song S = new Song(s,a);  
                        if(S != null)
                            canc.add(S);
                    }
                }
            }
            
            newAlbum = new Album(t,i,a,d,nc,tip,canc);     
            
        }catch(NumberFormatException e){
            System.err.println("ERROR: no se ha podido crear la instancia de Album");
            System.err.println("Exception:"+e);
            newAlbum = null;
        }
        
        return newAlbum;
    }
    
    
    /**
     * Creates an Album asking the user for the data.
     * @return The new Album or null if there is an error
     */
    public static Album createNewAlbum(){
        Album album;
        try{
            String t = Esdia.readString_ne("\n\nIntroduzca el titulo del album:");

            List<String> in = new ArrayList<>();  
            boolean seguir = false;
            do{
                String i = Esdia.readString_ne("\nIntroduzca el nombre del artista(- para continuar):");
                if(i.compareTo("-") == 0) 
                    seguir = true;
                else
                    in.add(i);
            }while(!seguir);

            int a = Esdia.readInt("\nIntroduzca el año del album:",1,Integer.MAX_VALUE);
            
            int nc = Esdia.readInt("\nIntroduzca el numero de canciones del album:",1,Integer.MAX_VALUE);
                    
            String tipo;
            if(nc == 1) tipo="sencillo";
            else        tipo="álbum";
            
            List<Song> songs = new ArrayList<>();
            String[] songDur;
            int albMin = 0, albSeg = 0;
            for(int i=0; i<nc; i++){
                Song song = Song.createNewSong(in);
                if(song != null) songs.add(song);
            
                //To calculate album total duration from the duration of the songs
                songDur = song.getDuration().split(" ");
                int songMin = Integer.parseInt(songDur[0]);
                int songSeg = Integer.parseInt(songDur[2]);
                albMin += songMin;
                albSeg += songSeg;
            }
            
            if(albSeg > 59){
                albMin += (albSeg/60);
                albSeg = albSeg % 60;
            }
            
            String d = albMin + " min " + albSeg + " seg";   
            
            album = new Album(t,in,a,d,nc,tipo,songs);
            
        }catch(Exception e){
            album = null;
            System.err.println("[ERROR] No se ha podido crear la instancia de Album: ");
            System.err.println("Exception:"+e);
        }
        
        return album;
    }
    
    
    /**
     * Creates an Album by modifying another album given by the user.
     * @param album - Album to modify
     * @return The new Album or null if there is an error
     */
    public static Album modifiedCopyOfAlbum(Album album){        
        int a;
        if(Esdia.yesOrNo("\n¿Quiere modifica el año del album?"))
            a = Esdia.readInt("\nIntroduzca el nuevo año:",1,Integer.MAX_VALUE);  
        else
            a=album.getYear();
        
        
        String d;
        if(Esdia.yesOrNo("\n¿Quiere modificar la duracion del album?")){
            System.out.println("\nIntroduzca la nueva duración:");
            int min = Esdia.readInt("Introduzca los minutos:",0,59);
            int seg = Esdia.readInt("Introduzca los segundos:",0,59);
            d = min+" min "+seg+" seg";
            
        }else{
            d = album.getDuration();
        }
        
        return (new Album(album.getTitle(), album.getInterpreters(), a, d, album.getNumSongs(), album.getType(), album.getSongs()));
    }
  
    
    /**
     * Returns a string representation of the Album representing and HTML Table Row.
     * @return 
     */
    public String asHTMLTableRow(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<TR>"+"<TD>&nbsp&nbsp%s&nbsp&nbsp</TD>"+"<TD>",this.title));
        
        if(this.interpreters!=null && !this.interpreters.isEmpty()){
            for(String i : this.interpreters){
                sb.append(String.format("&nbsp&nbsp%s&nbsp&nbsp<br/>", i));
            }
            
        }else{
            sb.append(" ");
        }
        
        sb.append(String.format("</TD>"+"<TD>&nbsp&nbsp%d&nbsp&nbsp</TD>" + "<TD>&nbsp&nbsp%s&nbsp&nbsp</TD>" + "<TD>&nbsp&nbsp%d&nbsp&nbsp</TD>" 
                         + "<TD>&nbsp&nbsp%s&nbsp&nbsp</TD>"+ "</TR>", 
                            this.year,  this.duration, this.numSongs,this.type));
          
        return sb.toString(); 
    }

    
    /**
     * Returns a string representation of the Album (including his Songs)
     * @return 
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-40s", this.title)).append(" | ");
        
        if(this.interpreters!=null && !this.interpreters.isEmpty()){
            sb.append(String.format("%-40s", this.interpreters.get(0))).append(" | ");
        }else{
            sb.append(String.format("%-40s", " ")).append(" | ");
        }
        
        sb.append(String.format("%-5s", this.year)).append(" | ").append(String.format("%-15s", this.duration)).append(" | ");
        sb.append(String.format("%-13s", this.numSongs)).append(" | ").append(String.format("%-10s",this.type)).append(" | ");
        
        if(this.songs!=null && !this.songs.isEmpty()){
            sb.append(String.format("%s", this.songs.get(0).toDelimitedString(","))).append("\n");
        }else{
            sb.append(String.format("%s", " ")).append("\n");
        }
        
        int aux;
        if(this.interpreters.size()>this.songs.size())
            aux=this.interpreters.size();
        else
            aux=this.songs.size();
        
        for(int i=1; i<aux; i++){
            if(i<this.interpreters.size()){
                sb.append(String.format("%-40s", " ")).append(" | ").append(String.format("%-40s", this.interpreters.get(i))).append(" | ");
                sb.append(String.format("%-5s", " ")).append(" | ").append(String.format("%-15s"," ")).append(" | ");
                sb.append(String.format("%-13s", " ")).append(" | ").append(String.format("%-10s"," ")).append(" | ");
                
                if(i<this.songs.size())
                    sb.append(String.format("%s\n",this.songs.get(i).toDelimitedString(",")));
                else
                    sb.append(String.format("%s\n"," "));
            
            }else{
                sb.append(String.format("%-40s", " ")).append(" | ").append(String.format("%-40s", " ")).append(" | ");
                sb.append(String.format("%-5s", " ")).append(" | ").append(String.format("%-15s"," ")).append(" | ");
                sb.append(String.format("%-13s", " ")).append(" | ").append(String.format("%-10s"," ")).append(" | ");
                
                if(i<this.songs.size())
                    sb.append(String.format("%s\n",this.songs.get(i).toDelimitedString(",")));
                else
                    sb.append(String.format("%s\n"," "));
            }
        }
        return sb.toString();
    }
}
