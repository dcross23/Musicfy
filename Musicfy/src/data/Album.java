/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

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
     * @return 
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
}
