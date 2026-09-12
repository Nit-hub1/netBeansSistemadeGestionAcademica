/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;

import logica.entidades.Usuario;
import java.sql.*; // es pq importo todo el *

public class UsuarioRepositorio {
// el throw es pq podría haber un error en la bd durante la ejecucion y "lo tranca" sin q se rompa todo
    public Usuario buscarPorCedula(String cedula) throws SQLException {  
        String sql = "SELECT Cedula, pwHash, Rol, Nombre, Apellido, Activo " +
                     "FROM Usuario WHERE Cedula = ?"; // me devuelve filtrando solo x la cedula
        try (Connection con = ConexionBD.getConexion();
            // por lo que leí son consultas pre-compiladas a las q les llegan
            // los parametros q se pasan x el ?, trata como datos
            // y no puedo concatenar algo mal x error
             PreparedStatement ps = con.prepareStatement(sql)) {
            // 1 pq es la posicion de la cedula
            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getString("Cedula"),
                        rs.getString("pwHash"),
                        rs.getString("Rol"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getBoolean("Activo")
                    );
                }
                return null; // no existe esa cédula
            }
        }
    }
}