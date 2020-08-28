package data;

import com.coti.tools.Esdia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    
    /**
     * Creates an Artist asking the user for the data.
     * @return The new Artist or null if there is an error
     */
    public static Artist createNewArtist(){
        Artist ar;
        try{
            String n = Esdia.readString_ne("\nIntroduzca el nombre del artista:");
            String b = Esdia.readString_ne("\nIntroduzca la biografia del artista:");
            String ins = Esdia.readString_ne("\nIntroduzca el instagram del artista:");
            String tw = Esdia.readString_ne("\nIntroduzca el twitter del artista:");
            String f = Esdia.readString_ne("\nIntroduzca el facebook del artista:");
            String w = Esdia.readString_ne("\nIntroduzca la wikipedia del artista:");
            
            List<String> alb = new ArrayList<>();              
            int nAlb = Esdia.readInt("\nIntroduzca el numero de albumes del artista(minimo 1)",1,Integer.MAX_VALUE);
            for(int i=0; i<nAlb; i++){
                String a = Esdia.readString_ne("\nIntroduzca el nombre del album:");
                if(a != null)
                    alb.add(a);
            }
                
            ar = new Artist(n,b,ins,tw,f,w,alb);
        }catch(Exception e){
            ar = null;
        }
        return ar;
    }
    
    /**
     * Creates an Artist by modifying another album given by the user.
     * @param artist - Artist to modify
     * @return - The new Artist or null if there is an error
     */
    public static Artist modifiedCopyOfArtist(Artist artist){        
        String bio;
        if(Esdia.yesOrNo("\n¿Quiere modificar la biografia del artista?")){
            bio = Esdia.readString_ne("\nIntroduzca la nueva biografía:");
        }else{
            bio = artist.getBiography();
        }
        
        String ins;
        if(Esdia.yesOrNo("\n¿Quiere modificar el instagram del artista?")){
            ins = Esdia.readString_ne("\nIntroduzca el nuevo instagram:");
        }else{
            ins = artist.getInstagram();
        }
        
        String tw;
        if(Esdia.yesOrNo("\n¿Quiere modificar el twitter del artista?")){
            tw = Esdia.readString_ne("\nIntroduzca el nuevo twitter:");
        }else{
            tw = artist.getTwitter();
        }
        
        String f;
        if(Esdia.yesOrNo("\n¿Quiere modificar el facebook del artista?")){
            f = Esdia.readString_ne("\nIntroduzca el nuevo facebook:");
        }else{
            f = artist.getFacebook();
        }
        
        String wiki;
        if(Esdia.yesOrNo("\n¿Quiere modificar la wikipedia del artista?")){
            wiki = Esdia.readString_ne("\nIntroduzca la nueva wikipedia:");
        }else{
            wiki = artist.getWikipedia();
        }

        return (new Artist(artist.getName(), bio, ins, tw, f, wiki, artist.getAlbums()));
    }
    
    /**
     * Returns a column format string representation of the Artist. 
     * @return 
     */
    public String toColString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-300s%-25s%-25s%-25s%-50s",this.name,this.biography,this.instagram,this.twitter,this.facebook,this.wikipedia));
        
        if(this.albums != null){
            for(String a: this.albums){
                sb.append(String.format("%-20s",a));
            }
            
        }else{
            sb.append(String.format("%-20s"," "));
        }
        
        sb.append("\n");
        
        return sb.toString(); 
    }
    
    /**
     * Returns a string representation of the Artist
     * @return 
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s", this.name)).append(" | ").append(String.format("%-300s", this.biography)).append(" | ");
        sb.append(String.format("%-25s", this.instagram)).append(" | ").append(String.format("%-25s", this.twitter)).append(" | ");
        sb.append(String.format("%-25s", this.facebook)).append(" | ").append(String.format("%-50s", this.wikipedia)).append(" | ");
        
        if(this.albums != null && !this.albums.isEmpty()){
            sb.append(String.format("%-20s", this.albums.get(0)));
            for(String a: this.albums.subList(1, this.albums.size())){
                sb.append(" , ").append(String.format("%-20s", a));
            }
        }else{
            sb.append(" ");
        }
        
        return sb.toString();        
    }
}
