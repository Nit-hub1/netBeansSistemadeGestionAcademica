/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidades;

/**
 *
 * @author iamx2
 */
public class Calificacion {
    private int idCalificacion;
    private String fechaNota;
    private double nota;
    private String tareaCalificacion;
    private Inscripcion inscripciones;

    public Calificacion(int idCal, String fechaNta, double nta, String tareaCal, Inscripcion inscrip) {
        idCalificacion = idCal;
        fechaNota = fechaNta;
        nota = nta;
        tareaCalificacion = tareaCal;
        inscripciones = inscrip;
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCal) {
        idCalificacion = idCal;
    }

    public String getFechaNota() {
        return fechaNota;
    }

    public void setFechaNota(String fechaNta) {
        fechaNota = fechaNta;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nta) {
        nota = nta;
    }

    public String getTareaCalificacion() {
        return tareaCalificacion;
    }

    public void setTareaCalificacion(String tareaCal) {
        tareaCalificacion = tareaCal;
    }

    public Inscripcion getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(Inscripcion inscrip) {
        inscripciones = inscrip;
    }
    
    
}
