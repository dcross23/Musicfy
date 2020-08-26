/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.coti.tools.Rutas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
    /*                                  ARCHIVES OPTION                                */
    /*=================================================================================*/
    /**
     * Exports artists as a column format document, where each "line" represents an artist.
     * Each artist is represented as a formated string with specific length, differentiating
     * each other with "\n" (new line).
     * @param location
     * @throws FileNotFoundException - In case the file can not be created by the PrintWriter
     */
    public void exportArtistsColumnFormat(String location) throws FileNotFoundException{        
        File exitArtistsFile = new File(location + File.separator + "artistas.col"); 

        PrintWriter pw = new PrintWriter(exitArtistsFile);
        if(this.artists!=null && !this.artists.isEmpty()){
            for(Artist a: this.artists){
                if(a!=null)
                    pw.append(a.toColString());
            }
        }
        
        pw.close();
    }
    
    /**
     * Exports albums as a HTML table, where each row represents an album.
     * @param location
     * @throws FileNotFoundException - In case the file can not be created by the PrintWriter
     */
    public void exportAlbumsHTMLTable(String location) throws FileNotFoundException{
        File exitAlbumsFile = new File(location + File.separator + "albumes.html"); 
                
        PrintWriter pw = new PrintWriter(exitAlbumsFile);
            
        pw.printf("<!DOCTYPE html>%n<HTML>%n<HEAD><meta charset=\"UTF-8\"><H1>ALBUMES</H1></HEAD>%n<BODY>%n");
        pw.printf("<TABLE BORDER=5>\n");
        pw.printf("<TBODY STYLE=\"background: rgba(164,171,255,10);\">\n");
        pw.printf("<TR>"+"<TH>TÍTULO</TH>"+"<TH>INTÉRPRETES</TH>"+"<TH>AÑO</TH>"+"<TH>DURACIÓN</TH>"+"<TH>Nº CANCIONES</TH>"+"<TH>TIPO</TH>"+"</TR></TBODY>");
        
        if(this.albums!=null && !this.albums.isEmpty()){
            for(Album a: this.albums){
                if(a!=null)
                    pw.printf("%s\n", a.asHTMLTableRow());
            }
        }
       
        pw.printf("</TABLE>");
        pw.printf("</BODY>\n</HTML>");
        pw.close();
    }
    
    
    
    /*=================================================================================*/
    /*                                 PLAYLIST OPTION                                 */
    /*=================================================================================*/
    /**
     * Adds a new playlist to the list.
     * @param p - PlayList to add
     * @return - true if it is added successfully
     */
    public boolean registerPlayList(PlayList p){
        if(p != null){
            this.playLists.add(p);
            return true;
            
        }else
            return false;
    }
    
    /**
     * Removes a playlist from the list.
     * @param p - PlayList to remove
     * @return - true if it is removed successfully
     */
    public boolean removePlayList(PlayList p){
        if(p != null && this.playLists.contains(p)){
            this.playLists.remove(p);
            return true;
        
        }else{
            return false;
        }
    }
    
    /**
     * Removes the song "s" from the playList number "playListIndex".
     * @param playListIndex - Index of the playlist
     * @param s - Song that will be removed
     * @return - true if it was removed successfully
     */
    public boolean removeSong(int playListIndex, Song s){
        if(this.playLists!=null && !this.playLists.isEmpty() && playListIndex < this.playLists.size()){
            PlayList p = this.playLists.get(playListIndex);
            
            if(p != null){
                List<Song> playListSongs = p.getSongs();
                if(playListSongs!=null && playListSongs.contains(s)){
                    playListSongs.remove(s);
                    return true;
                }
            }
        }
        return false;
    }
    
    
    /**
     * Adds the song "s" to the playList number "playListIndex".
     * @param playListIndex - Index of the playlist
     * @param s - Song that will be removed
     * @return - true if it was added successfully
     */
    public boolean addSong(int playListIndex, Song s){
        if(this.playLists!=null && !this.playLists.isEmpty() && playListIndex < this.playLists.size()){
            PlayList p = this.playLists.get(playListIndex);
            if(p!=null){
                List<Song> playListSongs = p.getSongs();
                
                //If the song is not in the playlist, just adds it
                // Otherwise, the song will not be added 
                if(playListSongs!=null && !playListSongs.contains(s)){               
                    //If the song is not in the playlist, just adds it 
                    playListSongs.add(s);
                    return true;  
                }
            }
        }     
        return false;
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
