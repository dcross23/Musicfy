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
    /**
     * Artist name.
     */
    private String name;
    
    /**
     * Biography of the artist.
     */
    private String biography;
    
    /**
     * Artist´s social networks.
     */
    private String instagram;
    private String twitter;
    private String facebook;
    private String wikipedia;
    
    /**
     * Artist albums.
     */
    private List<String> albums;
    
    
    /*======================================================*/
    /*                   CONSTRUCTORS                       */
    /*======================================================*/
    
    /**
     * Constructor: creates new artist with all given parameters.
     * @param name
     * @param biography
     * @param instagram
     * @param twitter
     * @param facebook
     * @param wikipedia
     * @param albums 
     */
    public Artist(String name, String biography, String instagram, String twitter, String facebook, String wikipedia, List<String> albums){
        this.name = name;
        this.biography = biography;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
        this.wikipedia = wikipedia;
        this.albums = albums;
    }

    /**
     * Constructor: creates new artist with just only name and his/her albums.
     * @param name
     * @param albums 
     */
    public Artist(String name,List<String> albums){
        this.name = name;
        this.biography = "";
        this.instagram = "";
        this.twitter = "";
        this.facebook = "";
        this.wikipedia = "";
        this.albums = albums;
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
    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
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
    public List<String> getAlbums() {
        return albums;
    }
    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }
    
    
    /*======================================================*/
    /*                      METHODS                         */
    /*======================================================*/
    
    /**
     * Creates an Artist from a given delimited String (# and ; delimiters).
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
