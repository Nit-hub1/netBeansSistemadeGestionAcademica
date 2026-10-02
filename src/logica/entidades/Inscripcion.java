/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidades;

/**
 *
 * @author iamx2
 */
public class Inscripcion {
    private Estudiante estudiante;
    private int idInscripcion;
    private String estado;
    private String fechaInscrip;
    
    public Inscripcion (Estudiante estu, int idInscrip, String est, String fecInscrip){
        estudiante = estu;
        idInscripcion = idInscrip;
        estado = est;
        fechaInscrip = fecInscrip;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaInscrip() {
        return fechaInscrip;
    }

    public void setFechaInscrip(String fechaInscrip) {
        this.fechaInscrip = fechaInscrip;
    }
    
    
}
