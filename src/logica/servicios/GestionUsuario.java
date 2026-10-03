/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.servicios;
import repositorio.UsuarioPersistencia;
import logica.entidades.*;
/**
 *
 * @author iamx2
 */
public class GestionUsuario {
    private UsuarioPersistencia usuarioPersistencia;
    
    public GestionUsuario(){
        usuarioPersistencia = new UsuarioPersistencia();
    }
    
    public Usuario verificarUsuario(String ci, String pw){
        String cedula = validarCedula(ci);
        Usuario user = usuarioPersistencia.buscarCi(cedula);
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
        String cedula = validarCedula(ci);
        if(usuarioExistente(cedula)){
            return false;
        }
        Usuario nuevoUsuario;
        if(tipoPerfil==0){
            nuevoUsuario = new Docente(nom, ape, cedula, pw);
        }else{
            nuevoUsuario = new Estudiante(nom, ape, cedula, pw);
        }
        usuarioPersistencia.guardarUser(nuevoUsuario);
        return true;
    }
    
    // 8 digitos, no acepta . o -
    private String validarCedula(String cedula){
        if(cedula == null || cedula.trim().isEmpty()){
            throw new IllegalArgumentException("Debe ingresar una cédula.");
        }
        cedula = cedula.trim();

        for(int i = 0; i < cedula.length(); i++){
            char letra = cedula.charAt(i);
            if(letra < '0' || letra > '9'){
                throw new IllegalArgumentException("La cédula solo puede tener números, sin puntos ni guiones (ej: 54461593).");
            }
        }
        if(cedula.length() != 8){
            throw new IllegalArgumentException("La cédula debe tener 8 dígitos (tiene " + cedula.length() + ").");
        }
        return cedula;
    }
    
}
