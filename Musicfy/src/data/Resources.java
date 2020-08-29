package data;

import com.coti.tools.OpMat;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import main.MainMusicfy;

/**
 *
 * @author david
 */
public class Resources implements Serializable{
    /**
     * Paths/Lists to load external resources.
     */
    private final Random r = new Random(System.currentTimeMillis());
    private final Path pathResourcesFolder; 
    
    private final Path pathSocialNetworks;
    private String[][] socialNetworks;
    
    private final Path pathAlbumNames;
    private List<String> albumNames;
    
    private final Path pathArtistsNames;
    private List<String> artistNames;
    
    private final Path pathSongTitles;
    private List<String> songTitles;
    
    private final Path pathPlayListNames;
    private List<String> playListNames;

    
    /**
     * Constructor - creates new Resources object and loads external files.
     * @throws Exception 
     */
    public Resources() throws Exception {
        File pathMusicfyFolder = new File(MainMusicfy.pathMusicfyFolder);
        
        this.pathResourcesFolder  = Path.of(pathMusicfyFolder+File.separator+"aleatorios");
        this.pathPlayListNames = Path.of(pathResourcesFolder+File.separator+"nombresPlaylists.txt");
        this.pathSongTitles = Path.of(pathResourcesFolder+File.separator+"titulosCanciones.txt");
        this.pathArtistsNames  = Path.of(pathResourcesFolder+File.separator+"nombresArtistas.txt");
        this.pathAlbumNames   = Path.of(pathResourcesFolder+File.separator+"nombresAlbumes.txt");
        this.pathSocialNetworks    = Path.of(pathResourcesFolder+File.separator+"redesSociales.txt");
        
        
        this.socialNetworks = OpMat.importFromDisk(this.pathSocialNetworks.toFile(), ";");
        this.albumNames = Files.readAllLines(pathAlbumNames);
        this.artistNames =  Files.readAllLines(pathArtistsNames);
        this.songTitles =  Files.readAllLines(pathSongTitles);
        this.playListNames =  Files.readAllLines(pathPlayListNames);
    }
    
    
    /*======================================================*/
    /*                GETTERS AND SETTERS                   */
    /*======================================================*/
    public String[][] getSocialNetworks() {
        return socialNetworks;
    }
    public void setSocialNetworks(String[][] socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public List<String> getAlbumNames() {
        return albumNames;
    }
    public void setAlbumNames(List<String> albumNames) {
        this.albumNames = albumNames;
    }

    public List<String> getArtistNames() {
        return artistNames;
    }
    public void setArtistNames(List<String> artistNames) {
        this.artistNames = artistNames;
    }

    public List<String> getSongTitles() {
        return songTitles;
    }
    public void setSongTitles(List<String> songTitles) {
        this.songTitles = songTitles;
    }

    public List<String> getPlayListNames() {
        return playListNames;
    }
    public void setPlayListNames(List<String> playListNames) {
        this.playListNames = playListNames;
    }
    
    
}
