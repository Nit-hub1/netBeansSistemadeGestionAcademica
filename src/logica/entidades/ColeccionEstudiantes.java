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
public class ColeccionEstudiantes {
    private ArrayList<Estudiante> listaEstudiantes;
    
    public ColeccionEstudiantes(){
        listaEstudiantes = new ArrayList<Estudiante>();
    }
    
    public void agregarEstudiantes(Estudiante est){
        listaEstudiantes.add(est);
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }
    
    public int cantidadEstudiantes(){
        return listaEstudiantes.size();
    }
    
    public Estudiante obtenerEstudiante (int i){
        return listaEstudiantes.get(i);
    }    
}

