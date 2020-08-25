/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.List;

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
