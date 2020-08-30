package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    /*                             RANDOM GENERATION OPTION                            */
    /*=================================================================================*/
    
    /**
     * Gives the user the option to generate random values for Musicfy.
     * @throws java.io.IOException - If external files are not found
     */
    public void setRandomValues() throws IOException{
        Random r = new Random(System.currentTimeMillis());
        
        List<Album> albu = new ArrayList<>();
        List<Song> canc = new ArrayList<>();
        List<Artist> art = new ArrayList<>();
        List<PlayList> playl = new ArrayList<>();
        
        //1-20 albums (adding also his songs)
        int nAlbumes = r.nextInt(20)+1;  
        for(int i=0; i<nAlbumes; i++){
            Album a = Album.randomValues();
            if(a!=null){
                albu.add(a);
                canc.addAll(a.getSongs());
            }
        }
        
         
        //Get the random albums interpreters and create a new list without
        //  repeated names
        List<String> allAlbumsInterpreters = new ArrayList<>();
        if(!albu.isEmpty()){
            for(Album alb: albu){
                if(alb!=null){
                    allAlbumsInterpreters.addAll(alb.getInterpreters());
                }
            }
        }
        
        //To delete repeated names (Strings), it uses a HashSet which does not
        // allow repeated String in it.
        Set<String> noRepeatedInterpreters = new HashSet<>(allAlbumsInterpreters); 
        allAlbumsInterpreters.clear();  
        allAlbumsInterpreters.addAll(noRepeatedInterpreters);
        
        //Create the Artists with that list of no repeated names
        Artist a;
        if(!allAlbumsInterpreters.isEmpty()){
            for(String i: allAlbumsInterpreters){
                a = Artist.randomValues(i, albu);
                if(a != null)
                    art.add(a);
            }
        }
        
        //1-5 random PlayLists
        int nPlayLists = r.nextInt(5)+1;
        for(int i=0; i<nPlayLists; i++){
            PlayList pl = PlayList.randomValues(canc);
            if(pl!=null)
                playl.add(pl);
        }
        
        this.albums = albu;
        this.artists = art;
        this.songs = canc;
        this.playLists = playl;
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
    /*                                   ALBUM OPTION                                  */
    /*=================================================================================*/
    /**
     * Adds a new Album.
     * It also adds all the songs from the new Album to the general list of Songs, and it
     * adds the album interpreters to the general list of Artists if the artist is not
     * registered yet.
     * @param a - Album to add
     * @return - True if the Album was added successfully
     */
    public boolean registerAlbum(Album a){
        if(a!=null){
            Artist artist = null;
            List<String> albumInterpreters = a.getInterpreters();
            
            if(albumInterpreters != null && !albumInterpreters.isEmpty()){
                //For each artist of the album, we look if it is or not already in the list
                // and we add to the list if its not in it.
                for(String i: a.getInterpreters()){
                    if(i!=null && !i.isEmpty()){
                        for(Artist ar: this.artists){
                            if(i.compareTo(ar.getName()) == 0){
                                artist = ar;
                                break;
                            }
                        }

                        //If the artist its not registered, it registers it with the album
                        if(artist == null){
                            List<String> lAlbum = new ArrayList<>();
                            lAlbum.add(a.getTitle());
                            this.artists.add(new Artist(i,lAlbum));

                        //If the artist is already registered, it just adds the album (if it
                        // wasnt already registered as an album of the artist)
                        }else{
                            List<String> artAlb = artist.getAlbums();
                            boolean add = true;
                            if(artAlb!=null && !artAlb.isEmpty()){
                                for(String s: artAlb){
                                    if(s.compareTo(a.getTitle()) == 0){
                                        add = false;
                                        break;
                                    }
                                }
                                
                                if(add) artAlb.add(a.getTitle());           
                            }
                        }
                    }
                }
            }

            //Adds the album
            this.albums.add(a);
            
            //Adds all the album songs
            this.songs.addAll(a.getSongs());
            return true;
            
        }else
            return false;
    }
    
    /**
     * Removes a registered Album.
     * It removes the album from the general list of Albums, but also deletes it
     * from the album lists of its interpreters and the album songs are also deleted
     * from the general list of songs.
     * @param album - Album to be removed
     * @return - true if it is removed successfully
     */
    public boolean removeAlbum(Album album){
        if(album != null && !this.albums.isEmpty() && this.albums.contains(album)){
            //Deletes the album from the general list
            this.albums.remove(album);

            //Deletes the album from the list of albums of every artist where the album was registerd
            // It uses indexes instead of removing the String "albName" because there can be
            // 2 or more albums with the same name
            int i;
            List<String> artistAlbums;
            for(Artist ar: this.artists){
                artistAlbums = ar.getAlbums();
                i=0;
                for(String albName: artistAlbums){
                    if(albName.compareTo(album.getTitle()) == 0){
                        artistAlbums.remove(i);
                        break;
                    }
                    i++;
                }
                
            }

            //Deletes the album songs from the songs general list 
            List<Song> albSongs = album.getSongs();
            if(album.getNumSongs() > 0 && albSongs!=null && !albSongs.isEmpty()){
                for(Song s: albSongs){
                    this.songs.remove(s);
                }
            }
            return true;
            
        }else{
            return false;
        }

    }
    
    /**
     * Modifies an Album.
     * Only alows some parameters to be modified (no title,interpreters or songs).
     * @param album - Album to modify
     * @return - Modified Album
     */
    public Album modifyAlbum(Album album){
        return Album.modifiedCopyOfAlbum(album);
    }
    
    /**
     * Returns a list with the Albums with the title "title" to be displayed.
     * @param title - Title of the album to be searched
     * @return - List of Albums with that title
     */
    public List<Album> checkAlbum(String title){
        if(this.albums!=null && !this.albums.isEmpty()){
            List <Album> albumesEncontrados = new ArrayList<>();
            for(Album a: this.albums){
                if(title.compareTo(a.getTitle())==0){
                    albumesEncontrados.add(a);
                }
            }
            return albumesEncontrados;
        
        }else
            return null;
    }
    
    
    /*=================================================================================*/
    /*                                  ARTIST OPTION                                  */
    /*=================================================================================*/
    /**
     * Adds a new Artist to the list.
     * @param artist - Artist to add
     * @return - true if it is added successfully
     */
    public boolean registerArtist(Artist artist){
        if(artist != null){
            this.artists.add(artist);
            return true;
        }else
            return false;       
    }
    
    /**
     * Removes an Artist.
     * It removes it only if all the artist albums are unregistered from 
     * the general list of Albums.
     * @param artist - Artist to remove
     * @return - List of the artist albums that are still registered
     */
    public List<String> removeArtist(Artist artist){
        if(artist!=null){
            List<String> artAlbums = artist.getAlbums();
            if(artAlbums!=null){
                //If the artist album list is empty, it just deletes the artist
                // Otherwise, it iterates through every album searching for any
                // coincidence with any album of the general list
                if(!artAlbums.isEmpty()){
                    for(String albArt: artAlbums){
                        if(albArt!=null && !albArt.isEmpty()){
                            for(Album album : this.albums){
                                if(albArt.compareTo(album.getTitle())==0){
                                    return artAlbums;
                                }
                            }
                        }
                    }
                }

                this.artists.remove(artist);
                return (new ArrayList<>());
            }
        }
        return null; 
    }
    
    /**
     * Modifies an Artist.
     * Only alows some parameters to be modified (no name or albums).
     * @param artist - Artist to modify
     * @return - Modified Artist
     */
    public Artist modifyArtist(Artist artist){
        return Artist.modifiedCopyOfArtist(artist);
    }
    
    /**
     * Returns the artist album list sorted by year and title
     * @param artist - Artist to get his/her album list
     * @return - Sorted artist album list
     */
    public List<Album> getSortArtistAlbumsList(Artist artist){
        List<Album> orderedAlbums =null;
        
        if(artist != null){
            orderedAlbums = new ArrayList<>();
            for(String artAlbum: artist.getAlbums()){
                if(!artAlbum.isEmpty()){
                    for(Album a: this.albums){
                        if(artAlbum.compareTo(a.getTitle())==0){
                            orderedAlbums.add(a);
                            break;
                        }
                    }
                }else{
                    return null;
                }
            }
            
            if(!orderedAlbums.isEmpty())
                orderedAlbums.sort(Comparator.comparing(Album::getYear).thenComparing(Album::getTitle));
        
        }
        
        return orderedAlbums;  
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
