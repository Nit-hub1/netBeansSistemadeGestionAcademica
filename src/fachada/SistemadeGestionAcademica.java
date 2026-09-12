/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fachada;

// importo las clases, el repo, el jar q hace lo de la contra y las excepciones
import logica.entidades.Usuario; 
import repositorio.UsuarioRepositorio;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;

public class SistemadeGestionAcademica {
    
    public static final String LOGIN_OK = "OK";
    public static final String LOGIN_USUARIO_INEXISTENTE = "USUARIO_INEXISTENTE";
    // este es por si "lo borre", ej. un docente que renuncio.
    public static final String LOGIN_USUARIO_INACTIVO = "USUARIO_INACTIVO";
    public static final String LOGIN_PASSWORD_INCORRECTA = "PASSWORD_INCORRECTA";

    private final UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
    private Usuario usuarioLogueado;

    public String validarLogin(String cedula, String passwordPlano) throws SQLException {
        Usuario usuario = usuarioRepositorio.buscarPorCedula(cedula);

        if (usuario == null) {
            return LOGIN_USUARIO_INEXISTENTE;
        }
        if (!usuario.isActivo()) {
            return LOGIN_USUARIO_INACTIVO;
        }
        if (!BCrypt.checkpw(passwordPlano, usuario.getPwHash())) {
            return LOGIN_PASSWORD_INCORRECTA;
        }

        this.usuarioLogueado = usuario;
        return LOGIN_OK;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }
}