/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.servicios;
import logica.entidades.*;
import repositorio.CursoPersistencia;

/**
 *
 * @author Nit
 */
public class GestionCurso {
    private CursoPersistencia cursoPersistencia;

    public GestionCurso() {
        cursoPersistencia = new CursoPersistencia();
    }
    
    public ColeccionCursos traerCursoPorDocente (String ciDocente){
        ColeccionCursos curso = cursoPersistencia.buscarCursoDocente(ciDocente);
        if(curso != null){
            return curso;
        }else{
            return null;
        }
    }
    
    public ColeccionInscripciones traerEstudiantesPorCurso (int idCurso){
        if(idCurso > 0){
            return cursoPersistencia.buscarEstudiantesPorCurso(idCurso);
        }
        return new ColeccionInscripciones();
    }
    
    public boolean registrarNotaEstudiante (String cedulaEstudiante, int idCurso, double nota, String comentarioTarea){
        if (!esNotaValida(nota)) {
            throw new IllegalArgumentException("La nota debe estar entre 1 y 12");
        }
        
        // validaciones asociadas al comentario
        if (comentarioTarea == null){
            comentarioTarea="";
        }
        
        comentarioTarea=comentarioTarea.trim();
        if(!esComentarioValido(comentarioTarea)){
            throw new IllegalArgumentException("El comentario no puede superar los 255 caracteres de largo");
        }
        
        int idInscripcion = cursoPersistencia.obtenerIdInscripcion(cedulaEstudiante, idCurso);
        if(idInscripcion != -1){
            return cursoPersistencia.guardarCalificacion(idInscripcion, nota, comentarioTarea);
        }
        return false;
    }
    
    public ColeccionCalificaciones obtenerHistorialCalificaciones(String cedula, int idCurso){
        if(cedula == null || cedula.trim().isEmpty() || idCurso <= 0){
            return new ColeccionCalificaciones();
        }
        return cursoPersistencia.obtenerHistorialCalificaciones(cedula, idCurso);
    }
    
    public static boolean esNotaValida(double nota) {
        return (nota > 0 && nota < 13);
    }
    
    // Predefensa - el comentario se guarda en TareaCalificada VARCHAR(255), por eso debo ver que el largo sea menor
    public static boolean esComentarioValido(String comentarioTarea) {
        return comentarioTarea.length() <= 255;
    }
        
}
