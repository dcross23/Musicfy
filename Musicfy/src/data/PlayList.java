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
public class PlayList implements Serializable {
    private String nombre;
    private List<Song> canciones;
    
    /* Constructors */
    
    
    /* Getters and setters */
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Song> getCanciones() {
        return canciones;
    }
    public void setCanciones(List<Song> canciones) {
        this.canciones = canciones;
    }
    
   
}
