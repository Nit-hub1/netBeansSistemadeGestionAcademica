/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;
import logica.entidades.Usuario;
/**
 *
 * @author iamx2
 */
public class GuardarUsuario {
   public void registroUser (String cedula, String pwHash, String rol, String nombre, String apellido, boolean activo){
       Usuario u = new Usuario(cedula, pwHash, rol, nombre, apellido, activo);    
    }
}
