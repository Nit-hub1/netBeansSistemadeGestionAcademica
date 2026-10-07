/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.entidades.*;

/**
 *
 * @author Nit
 */
public class CursoPersistencia {
    
    public ColeccionCursos buscarCursoDocente(String ciDocente) {
        ColeccionCursos coleccion = new ColeccionCursos();
        String sql = "Select c.idCurso, c.Estado, c.Periodo, c.CuposMax, a.Nombre AS NombreAsignatura, a.Creditos " + "FROM Curso c " +
                     "INNER JOIN Dicta d ON c.idCurso = d.idCurso " +
                     "INNER JOIN Asignatura a ON c.NombreAsignatura = a.Nombre " +
                     "WHERE d.CedulaDocente = ?";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, ciDocente);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idCurso = rs.getInt("idCurso");
                    String estado = rs.getString("Estado");
                    String periodo = rs.getString("Periodo");
                    int cuposMax = rs.getInt("CuposMax");
                    
                    String nomAsig = rs.getString("NombreAsignatura");
                    int creditos = rs.getInt("Creditos");
                    Asignatura asignatura = new Asignatura(nomAsig, creditos);
                    
                    Curso curso = new Curso(idCurso, estado, periodo, cuposMax, asignatura);
                    
                    coleccion.agregarCurso(curso);                  
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cursos: " + e.getMessage());
        }
        
        return coleccion;
    }
    
    public ColeccionInscripciones buscarEstudiantesPorCurso(int idCurso) {
        ColeccionInscripciones inscripciones = new ColeccionInscripciones();
        String sql = "SELECT u.Cédula, u.Nombre, u.Apellido, c.idInscripción, c.Estado, c.FechaInscripción " +
                     "FROM Cursa c " +
                     "JOIN Usuario u ON c.CedulaEstudiante = u.Cédula " +
                     "WHERE c.idCurso = ? AND c.Estado = 'Activo'";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, idCurso);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Estudiante est = new Estudiante(                                                            
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("Cédula"),
                        ""
                    );
                    
                    Inscripcion ins = new Inscripcion(
                        est,
                        rs.getInt("idInscripción"),
                        rs.getString("Estado"),
                        rs.getString("FechaInscripción")
                    );
                    
                    inscripciones.agregarInscripcion(ins);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar estudiantes por curso: " + e.getMessage());
        }
        
        return inscripciones;
    }
    
    // GUARDAR CALIFICACIONES 
    
    public int obtenerIdInscripcion (String cedulaEstudiante, int idCurso){
        int idInscripcion = -1;
        String sql = "SELECT idInscripción FROM Cursa WHERE CedulaEstudiante = ? AND idCurso = ? ";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, cedulaEstudiante);
            stmt.setInt(2, idCurso);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idInscripcion = rs.getInt("idInscripción");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ID Inscripción: " + e.getMessage());
        }
        
        return idInscripcion;
    }
    
    // Predefensa - el comentario del docente se guaqrda en la columna "TareaCalificada"
    public boolean guardarCalificacion (int idInscripcion, double nota, String comentarioTarea){
        String sql = "INSERT INTO Calificacion (idCalificación, Nota, FechaNota, TareaCalificada, idInscripción) " + 
                     "VALUES ((SELECT COALESCE(MAX(c.idCalificación), 0) + 1 FROM Calificacion c), ?, CURDATE(), ?, ?)";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setDouble(1, nota);
            stmt.setString(2, comentarioTarea);
            stmt.setInt(3, idInscripcion);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar calificación: " + e.getMessage());
            return false;
        }
    }
    
    public ColeccionCalificaciones obtenerHistorialCalificaciones(String cedula, int idCurso){
        ColeccionCalificaciones calificaciones = new ColeccionCalificaciones();
        String sql = "SELECT cal.idCalificación, cal.FechaNota, cal.TareaCalificada, cal.Nota " +
                     "FROM Calificacion cal " +
                     "JOIN Cursa c ON cal.idInscripción = c.idInscripción " +
                     "WHERE c.CedulaEstudiante = ? AND c.idCurso = ? " +
                     "ORDER BY cal.FechaNota DESC, cal.idCalificación DESC";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, cedula);
            stmt.setInt(2, idCurso);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idCalificacion = rs.getInt("idCalificación");
                    String fechaNota = rs.getString("FechaNota");
                    double nota = rs.getDouble("Nota");
                    String tarea = rs.getString("TareaCalificada");
                         
                    Calificacion cal = new Calificacion(idCalificacion, fechaNota, nota, tarea, null);
                    calificaciones.agregarCalificacion(cal); 
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar historial: " + e.getMessage());
        }
        return calificaciones;
    }
    
    // cada consulta devuelve una fila por estudiante en cada curso, con el promedio de sus notas en ese curso (nota final)

    public ColeccionResultados obtenerResultadosEstudiante(String cedula){
        String sql = "SELECT u.Cédula, u.Nombre, u.Apellido, c.idCurso, a.Nombre AS NombreAsignatura, a.Creditos, cu.Estado, " +
                     "AVG(cal.Nota) AS NotaFinal, COUNT(cal.idCalificación) AS CantidadNotas " +
                     "FROM Calificacion cal " +
                     "INNER JOIN Cursa cu ON cal.idInscripción = cu.idInscripción " +
                     "INNER JOIN Curso c ON cu.idCurso = c.idCurso " +
                     "INNER JOIN Asignatura a ON c.NombreAsignatura = a.Nombre " +
                     "INNER JOIN Usuario u ON cu.CedulaEstudiante = u.Cédula " +
                     "WHERE u.Cédula = ? " +
                     "GROUP BY u.Cédula, u.Nombre, u.Apellido, c.idCurso, a.Nombre, a.Creditos, cu.Estado";
        return consultarResultados(sql, cedula);
    }

    public ColeccionResultados obtenerResultadosAsignatura(String nombreAsignatura){
        String sql = "SELECT u.Cédula, u.Nombre, u.Apellido, c.idCurso, a.Nombre AS NombreAsignatura, a.Creditos, cu.Estado, " +
                     "AVG(cal.Nota) AS NotaFinal, COUNT(cal.idCalificación) AS CantidadNotas " +
                     "FROM Calificacion cal " +
                     "INNER JOIN Cursa cu ON cal.idInscripción = cu.idInscripción " +
                     "INNER JOIN Curso c ON cu.idCurso = c.idCurso " +
                     "INNER JOIN Asignatura a ON c.NombreAsignatura = a.Nombre " +
                     "INNER JOIN Usuario u ON cu.CedulaEstudiante = u.Cédula " +
                     "WHERE a.Nombre = ? " +
                     "GROUP BY u.Cédula, u.Nombre, u.Apellido, c.idCurso, a.Nombre, a.Creditos, cu.Estado";
        return consultarResultados(sql, nombreAsignatura);
    }

    public ColeccionResultados obtenerTodosLosResultados(){
        String sql = "SELECT u.Cédula, u.Nombre, u.Apellido, c.idCurso, a.Nombre AS NombreAsignatura, a.Creditos, cu.Estado, " +
                     "AVG(cal.Nota) AS NotaFinal, COUNT(cal.idCalificación) AS CantidadNotas " +
                     "FROM Calificacion cal " +
                     "INNER JOIN Cursa cu ON cal.idInscripción = cu.idInscripción " +
                     "INNER JOIN Curso c ON cu.idCurso = c.idCurso " +
                     "INNER JOIN Asignatura a ON c.NombreAsignatura = a.Nombre " +
                     "INNER JOIN Usuario u ON cu.CedulaEstudiante = u.Cédula " +
                     "GROUP BY u.Cédula, u.Nombre, u.Apellido, c.idCurso, a.Nombre, a.Creditos, cu.Estado";
        return consultarResultados(sql, null);            
    }

    
    private ColeccionResultados consultarResultados(String sql, String filtro){
        ColeccionResultados resultados = new ColeccionResultados();

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            // filtro = null significa que la consulta no tiene "?" (trae todo, es decir no solo 1 est)
            if(filtro != null){
                stmt.setString(1, filtro);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Asignatura asignatura = new Asignatura(rs.getString("NombreAsignatura"), rs.getInt("Creditos"));

                    ResultadoCurso res = new ResultadoCurso(
                        rs.getString("Cédula"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getInt("idCurso"),
                        asignatura,
                        rs.getString("Estado"),
                        rs.getDouble("NotaFinal"),
                        rs.getInt("CantidadNotas")
                    );
                    resultados.agregarResultado(res);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar resultados: " + e.getMessage());
        }
        return resultados;
    }
}
