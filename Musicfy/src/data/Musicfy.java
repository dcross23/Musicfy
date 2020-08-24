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
import java.util.List;

/**
 *
 * @author David
 */
public class Musicfy implements Serializable {
    private List<Song> canciones;
    private List<Album> albumes;
    private List<Artist> artistas;
    private List<PlayList> playLists;
    
    /* Constructors */
    public Musicfy(){
        this.canciones = new ArrayList<>();
        this.albumes = new ArrayList<>();
        this.artistas = new ArrayList<>();
        this.playLists = new ArrayList<>();
    }
    
    /* Getters and setters */
    public List<Song> getCanciones() {
        return canciones;
    }
    public void setCanciones(List<Song> canciones) {
        this.canciones = canciones;
    }
    public List<Album> getAlbumes() {
        return albumes;
    }
    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }
    public List<Artist> getArtistas() {
        return artistas;
    }
    public void setArtistas(List<Artist> artistas) {
        this.artistas = artistas;
    }
    public List<PlayList> getPlayLists() {
        return playLists;
    }
    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
    }
    
    
    /* Methods */
    
    /**
     * Imports all text files from the data folder called musicfy
     * @param location
     * @return true if text files are loaded successfully 
     */
    public boolean importTextFiles(String location){
        return ( this.importAlbumTextFile(location) && this.importArtistTextFile(location) );
    }
    
    
    /**
     * Imports album text file from the data folder called musicfy
     * @param location
     * @return true if album text file is loaded successfully 
     */
    private boolean importAlbumTextFile(String location){
        File albumFile = new File(location + File.separator + "albumes.txt");
         List<String> albumesImportados;
        if(!albumFile.exists()){ 
            return false;
            
        }else{
            try{
                albumesImportados = Files.readAllLines(albumFile.toPath());
                
            }catch(IOException e){
                albumesImportados=null;

                System.err.println("ERROR: no se han podido importar los artistas : artistas.txt");
                System.err.println("Exception:"+e);
                return false;
            }
        }
        
        if(albumesImportados != null){
            for(String s: albumesImportados){
                if(s != null){
                    Album newAlbum = Album.instanceFromString(s);
                    if(newAlbum != null){
                       this.albumes.add(newAlbum);
                       this.canciones.addAll(newAlbum.getCanciones());
                    }                     
                }
            }
        }
        
        return true;
    }
    
    /**
     * Imports artist text file from the data folder called musicfy
     * @param location
     * @return true if artist text file is loaded successfully 
     */
    private boolean importArtistTextFile(String location){
        File artistFile = new File(location + File.separator + "artistas.txt");
        
        List<String> artistasImportados;
        if(!artistFile.exists()){ 
            return false;
            
        }else{
            try{
                artistasImportados = Files.readAllLines(artistFile.toPath());
                
            }catch(IOException e){
                artistasImportados=null;

                System.err.println("ERROR: no se han podido importar los artistas : artistas.txt");
                System.err.println("Exception:"+e);
                return false;
            }
        }
        
        if(artistasImportados != null){
            for(String s: artistasImportados){
                if(s != null){
                    Artist newArtist = Artist.instanceFromString(s);
                    
                    if(newArtist != null)
                        this.artistas.add(newArtist);
                }
            }
        }   
        return true;
    }
    
    
}
