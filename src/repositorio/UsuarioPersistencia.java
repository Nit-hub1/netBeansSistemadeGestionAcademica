/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;

import logica.entidades.*;
import logica.servicios.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioPersistencia {

    public Usuario buscarCi(String ci) {
        String sqlBase = "SELECT Cédula, pwHash, Nombre, Apellido FROM Usuario WHERE Cédula = ?";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement stmt = con.prepareStatement(sqlBase)) {
            
            stmt.setString(1, ci);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("Nombre");
                    String apellido = rs.getString("Apellido");
                    String hashPw = rs.getString("pwHash");

                    if (existeEnTabla(con, "Estudiante", "CedulaEstudiante", ci)) {
                        return new Estudiante(nombre, apellido, ci, hashPw);
                    } else if (existeEnTabla(con, "Docente", "CedulaDocente", ci)) {
                        return new Docente(nombre, apellido, ci, hashPw);
                    } //else if (existeEnTabla(con, "Administrativo", "CedulaAdmin", ci)) {
                        //return new Administrador(nombre, apellido, ci, hashPw);
                    //}
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }
        
        return null;
    }

    private boolean existeEnTabla(Connection con, String tabla, String columna, String ci) throws SQLException {
        String sql = "SELECT 1 FROM " + tabla + " WHERE " + columna + " = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, ci);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void guardarUser(Usuario usuario) {
        String sqlUsuario = "INSERT INTO Usuario (Cédula, pwHash, Nombre, Apellido) VALUES (?, ?, ?, ?)";
        
        Connection con = null;
        try {
            con = ConexionBD.getConexion();
            con.setAutoCommit(false); 

            try (PreparedStatement stmtBase = con.prepareStatement(sqlUsuario)) {
                stmtBase.setString(1, usuario.getCi());
                stmtBase.setString(2, usuario.getPwHash());
                stmtBase.setString(3, usuario.getNombre());
                stmtBase.setString(4, usuario.getApellido());
                stmtBase.executeUpdate();
            }

            
            String sqlSubtabla = "";
            if (usuario instanceof Estudiante) {
                sqlSubtabla = "INSERT INTO Estudiante (CedulaEstudiante) VALUES (?)";
            } else if (usuario instanceof Docente) {
                sqlSubtabla = "INSERT INTO Docente (CedulaDocente) VALUES (?)";
            } //else if (usuario instanceof Administrador) {
                //sqlSubtabla = "INSERT INTO Administrativo (CedulaAdmin) VALUES (?)";
            //}

            if (!sqlSubtabla.isEmpty()) {
                try (PreparedStatement stmtSub = con.prepareStatement(sqlSubtabla)) {
                    stmtSub.setString(1, usuario.getCi());
                    stmtSub.executeUpdate();
                }
            }

            con.commit();
            
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Error al guardar usuario: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}