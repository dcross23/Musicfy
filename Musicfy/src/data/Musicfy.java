/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author David
 */
public class Musicfy implements Serializable {
    /**
     * Lists of songs, albums, artists and playlists the application will save.
     */
    private List<Song> songs;
    private List<Album> albums;
    private List<Artist> artists;
    private List<PlayList> playLists;
    
    /*======================================================*/
    /*                   CONSTRUCTORS                       */
    /*======================================================*/
    
    /**
     * Constructor: initializes Musicfy in case it can´t be loaded from binary file.
     */
    public Musicfy(){
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.playLists = new ArrayList<>();
    }
    
    /*======================================================*/
    /*                GETTERS AND SETTERS                   */
    /*======================================================*/
    
    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    public List<Album> getAlbums() {
        return albums;
    }
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
    public List<Artist> getArtists() {
        return artists;
    }
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    public List<PlayList> getPlayLists() {
        return playLists;
    }
    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
    }
    
    
    /*======================================================*/
    /*                      METHODS                         */
    /*======================================================*/
    
    
    /*=================================================================================*/
    /*                                IMPORT TEXT FILES                                */
    /*=================================================================================*/
    
    /**
     * Imports all text files from the data folder called musicfy.
     * @param location
     * @return true if text files are loaded successfully 
     */
    public boolean importTextFiles(String location){
        return ( this.importAlbumTextFile(location) && this.importArtistTextFile(location) );
    }
        
    /**
     * Imports album text file from the data folder called musicfy.
     * @param location
     * @return true if album text file is loaded successfully 
     */
    private boolean importAlbumTextFile(String location){
        File albumFile = new File(location + File.separator + "albumes.txt");
         List<String> importedAlbums;
        if(!albumFile.exists()){ 
            return false;
            
        }else{
            try{
                importedAlbums = Files.readAllLines(albumFile.toPath());
                
            }catch(IOException e){
                importedAlbums=null;

                System.err.println("ERROR: no se han podido importar los artistas : artistas.txt");
                System.err.println("Exception:"+e);
                return false;
            }
        }
        
        if(importedAlbums != null){
            for(String s: importedAlbums){
                if(s != null){
                    Album newAlbum = Album.instanceFromString(s);
                    if(newAlbum != null){
                       this.albums.add(newAlbum);
                       this.songs.addAll(newAlbum.getSongs());
                    }                     
                }
            }
        }
        
        return true;
    }
    
    /**
     * Imports artist text file from the data folder called musicfy.
     * @param location
     * @return true if artist text file is loaded successfully 
     */
    private boolean importArtistTextFile(String location){
        File artistFile = new File(location + File.separator + "artistas.txt");
        
        List<String> importedArtists;
        if(!artistFile.exists()){ 
            return false;
            
        }else{
            try{
                importedArtists = Files.readAllLines(artistFile.toPath());
                
            }catch(IOException e){
                importedArtists=null;

                System.err.println("ERROR: no se han podido importar los artistas : artistas.txt");
                System.err.println("Exception:"+e);
                return false;
            }
        }
        
        if(importedArtists != null){
            for(String s: importedArtists){
                if(s != null){
                    Artist newArtist = Artist.instanceFromString(s);
                    
                    if(newArtist != null)
                        this.artists.add(newArtist);
                }
            }
        }   
        return true;
    }
    
    
    /*=================================================================================*/
    /*                                  SONGS OPTION                                   */
    /*=================================================================================*/
    
    /**
     * Returns a copy of the song´s list, sorted using a comparator. 
     * @param c - Comparator used to sort the list
     * @return  - Returns a new sorted array list with all songs
     */
    public List<Song> sortSongs(Comparator c){
        List<Song> sortedSongs = new ArrayList<>(this.songs);
        sortedSongs.sort(c);
        return sortedSongs;
    }
    
}
