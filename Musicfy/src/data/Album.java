/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author David
 */
public class Album implements Serializable{
    private String titulo;
    private List<String> interpretes;
    private int anio;
    private String duracion;
    private int numCanciones;
    private String tipo;
    private List<Song> canciones;
    
    /* Constructors */
    public Album(String titulo, List<String> interpretes, int anio, String duracion, int numCanciones, String tipo, List<Song> canciones) {
        this.titulo = titulo;
        this.interpretes = interpretes;
        this.anio = anio;
        this.duracion = duracion;
        this.numCanciones = numCanciones;
        this.tipo = tipo;
        this.canciones = canciones;
    }
    
    /* Getters and setters */
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public List<String> getInterpretes() {
        return interpretes;
    }
    public void setInterpretes(List<String> interpretes) {
        this.interpretes = interpretes;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public String getDuracion() {
        return duracion;
    }
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    public int getNumCanciones() {
        return numCanciones;
    }
    public void setNumCanciones(int numCanciones) {
        this.numCanciones = numCanciones;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public List<Song> getCanciones() {
        return canciones;
    }
    public void setCanciones(List<Song> canciones) {
        this.canciones = canciones;
    }
    
    /* Methods */
    /**
     * Creates an Album from a given delimited String (\t and ; delimiters)
     * @param album
     * @return 
     */
    public static Album instanceFromString(String album){
        String[] data = album.split("\t");
        Album newAlbum;
        try{
            String t = data[0];
            List<String> i = Arrays.asList(data[1].split(";"));      
            int a = Integer.parseInt(data[2]);
            String d = data[3]; 
            int nc = Integer.parseInt(data[4]);
            String tip = data[5];
            
            List<Song> canc = new ArrayList<>();
            if(!tip.equals("sencillo") || nc>1){
                String[] so = data[6].split(";");
            
                for(String s : so){
                    if(!s.isEmpty()){
                        Song S = new Song(s,a);  
                        if(S != null)
                            canc.add(S);
                    }
                }
            }
            
            newAlbum = new Album(t,i,a,d,nc,tip,canc);     
            
        }catch(NumberFormatException e){
            System.err.println("ERROR: no se ha podido crear la instancia de Album");
            System.err.println("Exception:"+e);
            newAlbum = null;
        }
        
        return newAlbum;
    }
    
}
