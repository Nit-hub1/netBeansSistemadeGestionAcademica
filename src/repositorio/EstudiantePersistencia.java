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
 * @author iamx2
 */
public class EstudiantePersistencia {
    
    public ArrayList<String[]> obtenerAsignaturasEstudiante(String cedula) {
        ArrayList<String[]> lista = new ArrayList<>();
    
        String sql = "SELECT " +
                 "  c.NombreAsignatura, " +
                 "  c.Periodo, " +
                 "  c.Estado AS EstadoCurso, " +  // 'Activa'
                 "  a.Creditos, " +
                 "  cur.idInscripción, " +
                 "  c.idCurso " +
                 "FROM Cursa cur " +
                 "JOIN Curso c ON cur.idCurso = c.idCurso " +
                 "JOIN Asignatura a ON c.NombreAsignatura = a.Nombre " +
                 "WHERE cur.CedulaEstudiante = ? AND cur.Estado != 'Inactivo'";

        try (Connection con = ConexionBD.getConexion();
            PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, cedula);
        
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String[] fila = new String[6];
                fila[0] = rs.getString("NombreAsignatura");
                fila[1] = rs.getString("Periodo");
                fila[2] = rs.getString("EstadoCurso");
                fila[3] = String.valueOf(rs.getInt("Creditos"));
                fila[4] = String.valueOf(rs.getInt("idInscripción"));
                fila[5] = String.valueOf(rs.getInt("idCurso"));
            
                lista.add(fila);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar asignaturas del estudiante: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean darseDeBajaCurso(int idInscripcion) {
        String sql = "UPDATE Cursa SET Estado = 'Inactivo' WHERE idInscripción = ?";

        try (Connection con = ConexionBD.getConexion();
            PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idInscripcion);
            return stmt.executeUpdate() > 0;

        }catch (SQLException e) {
            System.err.println("Error al dar de baja la inscripción: " + e.getMessage());
            return false;
        }
    }   
    
    public int obtenerTotalCreditosAprobados(String cedula) {
        int totalCreditos = 0;
        String sql = "SELECT SUM(a.Creditos) AS Total " +
                    "FROM Cursa cur " +
                    "JOIN Curso c ON cur.idCurso = c.idCurso " +
                    "JOIN Asignatura a ON c.NombreAsignatura = a.Nombre " +
                    "WHERE cur.CedulaEstudiante = ? AND cur.Estado = 'Aprobado'";

        try (Connection con = ConexionBD.getConexion();
            PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, cedula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalCreditos = rs.getInt("Total");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al calcular créditos: " + e.getMessage());
        }
        return totalCreditos;
    }
}
