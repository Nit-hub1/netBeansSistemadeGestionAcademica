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
public class ColeccionCursos {
    private ArrayList<Curso> listaCursos;
    
    public ColeccionCursos(){
        listaCursos = new ArrayList<Curso>();
    }
    
    public void agregarCurso(Curso c){
        listaCursos.add(c);
    }

    public ArrayList<Curso> getListaCursos() {
        return listaCursos;
    }
    
    public int cantidadCursos(){
        return listaCursos.size();
    }
    
    public Curso obtenerCurso (int i){
        return listaCursos.get(i);
    }
    
    
}
