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
public class ColeccionInscripciones {
    private ArrayList<Inscripcion> listaInscripciones;
    
    public ColeccionInscripciones(){
        listaInscripciones = new ArrayList<Inscripcion>();
    }
    
    public void agregarInscripcion(Inscripcion ins){
        listaInscripciones.add(ins);
    }
    
    public int cantidadInscripciones(){
        return listaInscripciones.size();
    }
    
    public Inscripcion obtenerInscripciones (int i){
        return listaInscripciones.get(i);
    }
}
