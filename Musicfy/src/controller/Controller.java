/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Album;
import data.Artist;
import data.Musicfy;
import data.PlayList;
import data.Song;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author David
 */
public class Controller implements Serializable{
    /**
     * Musicfy`s data object. 
     */
    Musicfy m;
    
    /**
     * Says when Musicfy´s object is initialized to use it.
     */
    boolean musicfyReady;
    
    /*======================================================*/
    /*                   CONSTRUCTORS                       */
    /*======================================================*/
    /**
     * Constructor: Initializes Controller to load/create Musicfy´s object.
     */
    public Controller(){
        this.m = null;
        this.musicfyReady = false;
    }
    
    /*=================================================================================*/
    /*                                  BINARY FILE                                    */
    /*=================================================================================*/
    /**
     * Loads binary file.
     * @param location
     * @return true if binary file is loaded successfully
     */
    public boolean load(String location){
        File binaryMusicfy = new File(location + File.separator + "musicfy.bin");
        if(!binaryMusicfy.exists()){
            m = new Musicfy(); 
            musicfyReady = true;
            return false;
        
        }else{  
            try{
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream( new FileInputStream(binaryMusicfy) ));
                m = (Musicfy) ois.readObject();
                ois.close(); 
                
            }catch(IOException | ClassNotFoundException e) {
                System.err.println("ERROR: no se ha podido cargar el fichero binario");
                System.err.println("Exception:" + e);
                m = new Musicfy(); 
                musicfyReady = true;
                return false;
            }
        }
        
        musicfyReady = true;
        return true;
    }
    
    /**
     * Saves binary file.
     * @param location
     * @return true if binary file is saved successfully
     */
    public boolean save(String location){
        File binaryMusicfy = new File(location + File.separator + "musicfy.bin");

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(binaryMusicfy)));
            oos.writeObject(m);
            oos.close();

        }catch(IOException e) {
            System.err.println("[ERROR] No se ha podido guardar Musicfy");
            System.err.println("Exception:"+e);
            return false;
        }

        return true;
    }
    
    
    
    /*=================================================================================*/
    /*                                   TEXT FILES                                    */
    /*=================================================================================*/
    /**
     * Imports text files in case binary file doesnt exits.
     * @param location
     * @return true if text files are loaded successfully 
     */
    public boolean importTextFiles(String location){
        if(!this.musicfyReady || this.m == null){
            System.err.println("[ERROR] Musicfy is not ready, please load/create Musicfy");
            return false;
        }
        return m.importTextFiles(location);
    }
 
    
    
    /*=================================================================================*/
    /*                                  SONGS OPTION                                   */
    /*=================================================================================*/
    /**
     * Returns a copy of the song´s list, sorted using a comparator. 
     * @param c Comparator used to sort the list
     * @return  Returns a new sorted array list with all songs
     */
    public List<Song> sortSongs(Comparator c){
        if(!this.musicfyReady || this.m == null){
            System.err.println("[ERROR] Musicfy is not ready, please load/create Musicfy");
            return null;
        }
        
        return m.sortSongs(c);
    }
    
    

    /*======================================================*/
    /*                GETTERS AND SETTERS                   */
    /*======================================================*/
    public List<Song> getCanciones() {
        return m.getSongs();
    }
    public void setCanciones(List<Song> canciones) {
        m.setSongs(canciones);
    }
    public List<Album> getAlbumes() {
        return m.getAlbums();
    }
    public void setAlbumes(List<Album> albumes) {
        m.setAlbums(albumes);
    }
    public List<Artist> getArtistas() {
        return m.getArtists();
    }
    public void setArtistas(List<Artist> artistas) {
        m.setArtists(artistas);
    }
    public List<PlayList> getPlayLists() {
        return m.getPlayLists();
    }
    public void setPlayLists(List<PlayList> playlists) {
        m.setPlayLists(playlists);
    }
    
}
