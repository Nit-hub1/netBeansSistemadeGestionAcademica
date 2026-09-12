/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.servicios;
import javax.swing.JOptionPane;
import repositorio.UsuarioPersistenciaTESTMAP;
import logica.entidades.*;
/**
 *
 * @author iamx2
 */
public class GestionUsuario {
    private UsuarioPersistenciaTESTMAP usuarioPersistencia;
    
    public GestionUsuario(){
        usuarioPersistencia = new UsuarioPersistenciaTESTMAP();
    }
    
    public Usuario verificarUsuario(String ci, String pw){
        Usuario user = usuarioPersistencia.buscarCi(ci);
        if(user != null && user.validarPw(pw)){
            return user;
        }else{
            return null;
        }
    }
    
    public boolean usuarioExistente(String ci){
        return usuarioPersistencia.buscarCi(ci)!= null;
    }
    
    public boolean registrarUsuario(String nom, String ape, String ci, String pw, int tipoPerfil){
        if(usuarioExistente(ci)){
            return false;
        }
        Usuario nuevoUsuario;
        if(tipoPerfil==0){
            nuevoUsuario = new Docente(nom, ape, ci, pw);
        }else{
            nuevoUsuario = new Estudiante(nom, ape, ci, pw);
        }
        usuarioPersistencia.guardarUser(nuevoUsuario);
        return true;
    }
}
