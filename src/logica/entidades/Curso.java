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
public class Curso {
    private int idCurso;
    private String estado;
    private String periodo;
    private int cuposMax;
    private Asignatura asignatura;
    
    private ColeccionInscripciones listaInscripciones;
    
    public Curso(int idC, String est, String per, int cupM, Asignatura asigna, ColeccionInscripciones inscrip){
        idCurso = idC;
        estado = est;
        periodo = per;
        cuposMax = cupM;
        asignatura = asigna;
        if (inscrip != null) {
            listaInscripciones = inscrip;
        } else {
            listaInscripciones = new ColeccionInscripciones();
        }
    }
    
    public Curso(int idC, String est, String per, int cupM, Asignatura asigna){
        idCurso = idC;
        estado = est;
        periodo = per;
        cuposMax = cupM;
        asignatura = asigna;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idC) {
        idCurso = idC;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String est) {
        estado = est;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String per) {
        periodo = per;
    }

    public int getCuposMax() {
        return cuposMax;
    }

    public void setCuposMax(int cupM) {
        cuposMax = cupM;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asigna) {
        asignatura = asigna;
    }

    public ColeccionInscripciones getListaInscripciones() {
        return listaInscripciones;
    }

    public void setListaInscripciones(ColeccionInscripciones listaInscrip) {
        listaInscripciones = listaInscrip;
    }

    @Override
    public String toString() {
        if (asignatura != null) {
            return asignatura.getNombre() + " (" + periodo + ")";
        }
        return "Curso #" + idCurso + " (" + periodo + ")";
    }

}
