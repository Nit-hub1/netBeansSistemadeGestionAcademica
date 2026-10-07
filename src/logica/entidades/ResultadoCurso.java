/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidades;

/**
 *
 * @author Owner
 */
public class ResultadoCurso {
    private String cedulaEstudiante;
    private String nombre;
    private String apellido;
    private int idCurso;
    private Asignatura asignatura;
    private String estado;
    private double notaFinal;
    private int cantidadNotas;
    //

    public ResultadoCurso(String cedulaEstudiante, String nombre, String apellido, int idCurso, Asignatura asignatura, String estado, double notaFinal, int cantidadNotas) {
        this.cedulaEstudiante = cedulaEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idCurso = idCurso;
        this.asignatura = asignatura;
        this.estado = estado; 
        this.notaFinal = notaFinal;
        this.cantidadNotas = cantidadNotas;
    }

    public String getCedulaEstudiante() {
        return cedulaEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }
    
    public String getEstado(){
        return estado;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public int getCantidadNotas() {
        return cantidadNotas;
    }
}
