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
public class Song implements Serializable {
    private String titulo;
    private int anio;
    private String duracion;
    private List<String> interpretes;

    /* Constructors */
   public Song(String titulo, int anio, String duracion, List<String> interpretes){
        this.titulo = titulo;
        this.anio = anio;
        this.duracion = duracion;
        this.interpretes = interpretes;
    }
    
    public Song(String titulo, int anio){
        this.titulo = titulo;
        this.anio = anio;
        this.duracion = " ";
    }
    
    
    /* Getters and setters */    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
    public List<String> getInterpretes() {
        return interpretes;
    }
    public void setInterpretes(List<String> interpretes) {
        this.interpretes = interpretes;
    }
    
    /* Methods */
   
    
}
