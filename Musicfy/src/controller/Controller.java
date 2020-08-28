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
import java.io.FileNotFoundException;
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
    /*                                 ARCHIVES OPTION                                 */
    /*=================================================================================*/
    /**
     * Exports artists as a column format document, where each "line" represents an artist.
     * Each artist is represented as a formated string with specific length, differentiating
     * each other with "\n" (new line).
     * @param location
     * @throws FileNotFoundException - In case the file can not be created by the PrintWriter
     */
    public void exportArtistsColumnFormat(String location) throws FileNotFoundException{
        m.exportArtistsColumnFormat(location);
    }
    
    /**
     * Exports albums as a HTML table, where each row represents an album.
     * @param location
     * @throws FileNotFoundException - In case the file can not be created by the PrintWriter
     */
    public void exportAlbumsHTMLTable(String location) throws FileNotFoundException{
        m.exportAlbumsHTMLTable(location);
    }
    
    
    /*=================================================================================*/
    /*                                   ALBUM OPTION                                  */
    /*=================================================================================*/
    /**
     * Adds a new Album.
     * @param a - Album to add
     * @return - True if the Album was added successfully
     */
    public boolean registerAlbum(Album a){
        return m.registerAlbum(a);
    }
    
    /**
     * Removes a registered Album.
     * @param album - Album to be removed
     * @return - true if it is removed successfully
     */
    public boolean removeAlbum(Album album){
        return m.removeAlbum(album);
    }
    
    /**
     * Modifies an Album.
     * @param album - Album to modify
     * @return - Modified Album
     */
    public Album modifyAlbum(Album album){
        return m.modifyAlbum(album);
    }
    
    /**
     * Returns a list with the Albums with the title "title" to be displayed.
     * @param title - Title of the album to be searched
     * @return - List of Albums with that title
     */
    public List<Album> checkAlbum(String title){
        return m.checkAlbum(title);
    }
    
    
    
    /*=================================================================================*/
    /*                                  ARTIST OPTION                                  */
    /*=================================================================================*/
    /**
     * Adds a new Artist.
     * @param ar - Artist to add
     * @return - True if the Artist was added successfully
     */
    public boolean registerArtist(Artist ar){
        return m.registerArtist(ar);
    }
    
    /**
     * Removes a registered Artist.
     * @param ar - Artist to be removed
     * @return - true if it is removed successfully
     */
    public List<String> removeArtist(Artist ar){
        return m.removeArtist(ar);
    }
    
    /**
     * Modifies an Artist.
     * @param ar - Artist to modify
     * @return - Modified Artist
     */
    public Artist modifyArtist(Artist ar){
        return m.modifyArtist(ar);
    }
    
    /**
     * Returns a sorted list with the Albums of the Artist ar
     * @param ar - Artist from where the albums are obtained
     * @return - List of Albums of the Artist
     */
    public List<Album> getSortArtistAlbumsList(Artist ar){
        return m.getSortArtistAlbumsList(ar);
    }
    
    
    /*=================================================================================*/
    /*                                 PLAYLIST OPTION                                 */
    /*=================================================================================*/
    /**
     * Adds a new PlayList.
     * @param p - PlayList to add
     * @return - True if the playlist was added successfully
     */
    public boolean registerPlayList(PlayList p){
        return m.registerPlayList(p);
    }
    
    /**
     * Removes a registered playlist
     * @param p - PlayList to be removed
     * @return - true if it was removed successfully
     */
    public boolean removePlayList(PlayList p){
        return m.removePlayList(p);
    }
    
    /**
     * Removes the song "s" from the playList number "playListIndex".
     * @param playListIndex - Index of the playlist
     * @param s - Song that will be removed
     * @return - true if it was removed successfully
     */
    public boolean removeSong(int playListIndex, Song s){
        return m.removeSong(playListIndex, s);
    }
    
    /**
     * Adds the song "s" to the playList number "playListIndex".
     * @param playListIndex - Index of the playlist
     * @param s - Song that will be removed
     * @return - true if it was added successfully
     */
    public boolean addSong(int playListIndex, Song s){
        return m.addSong(playListIndex, s);
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
    public List<Song> getSongs() {
        return m.getSongs();
    }
    public void setSongs(List<Song> canciones) {
        m.setSongs(canciones);
    }
    public List<Album> getAlbums() {
        return m.getAlbums();
    }
    public void setAlbums(List<Album> albumes) {
        m.setAlbums(albumes);
    }
    public List<Artist> getArtists() {
        return m.getArtists();
    }
    public void setArtists(List<Artist> artistas) {
        m.setArtists(artistas);
    }
    public List<PlayList> getPlayLists() {
        return m.getPlayLists();
    }
    public void setPlayLists(List<PlayList> playlists) {
        m.setPlayLists(playlists);
    }
    
}
