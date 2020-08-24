/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author David
 */
public class Artist implements Serializable {
    private String nombre;
    private String biografia;
    private String instagram;
    private String twitter;
    private String facebook;
    private String wikipedia;
    private List<String> albumes;
    
    /* Constructors */
    
    /**
     * Constructor: creates new artist with all given parameters
     * @param nombre
     * @param biografia
     * @param instagram
     * @param twitter
     * @param facebook
     * @param wikipedia
     * @param albumes 
     */
    public Artist(String nombre, String biografia, String instagram, String twitter, String facebook, String wikipedia, List<String> albumes){
        this.nombre = nombre;
        this.biografia = biografia;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
        this.wikipedia = wikipedia;
        this.albumes = albumes;
    }

    /**
     * Constructor: creates new artist with just only name and his/her albums
     * @param nombre
     * @param albumes 
     */
    public Artist(String nombre,List<String> albumes){
        this.nombre = nombre;
        this.biografia = "";
        this.instagram = "";
        this.twitter = "";
        this.facebook = "";
        this.wikipedia = "";
        this.albumes = albumes;
    }
    
    
    /* Getters and setters */
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getBiografia() {
        return biografia;
    }
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    public String getInstagram() {
        return instagram;
    }
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
    public String getTwitter() {
        return twitter;
    }
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
    public String getFacebook() {
        return facebook;
    }
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
    public String getWikipedia() {
        return wikipedia;
    }
    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }
    public List<String> getAlbumes() {
        return albumes;
    }
    public void setAlbumes(List<String> albumes) {
        this.albumes = albumes;
    }
    
    
    /* Methods */
    /**
     * Creates an Artist from a given delimited String (# and ; delimiters)
     * @param artist
     * @return 
     */
    public static Artist instanceFromString(String artist){
        String delimiter = "#";
        
        String[] data = artist.split(delimiter);   
        Artist newArtist;
        try{
            String n = data[0];
            String b = data[1];
            String i = data[2];
            String t = data[3];
            String f = data[4];
            String w = data[5];
            List<String> a = Arrays.asList(data[6].split(";"));
            
            newArtist = new Artist(n,b,i,t,f,w,a);
            
        }catch(Exception e){
            System.err.println("ERROR: No se ha podido crear el nuevo artista");
            System.err.println("Exception:"+e);
            newArtist = null;
        }
        
        return newArtist;
    }
    
    
}
