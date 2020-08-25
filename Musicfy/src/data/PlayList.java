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
}
