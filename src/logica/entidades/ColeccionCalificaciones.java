/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidades;

import java.util.ArrayList;

/**
 *
 * @author Nit
 */
public class ColeccionCalificaciones {
    private ArrayList<Calificacion> listaCalificaciones;
    
    public ColeccionCalificaciones(){
        listaCalificaciones = new ArrayList<Calificacion>();
    }
    
    public void agregarCalificacion(Calificacion cal){
        listaCalificaciones.add(cal);
    }

    public ArrayList<Calificacion> getListaCalificaciones() {
        return listaCalificaciones;
    }
    
    public int cantidadCalificaciones(){
        return listaCalificaciones.size();
    }
    
    public Calificacion obtenerCalificacion (int i){
        return listaCalificaciones.get(i);
    }
}
