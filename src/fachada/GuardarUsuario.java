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
   public void registroUser (String nom, String ape, String ci, String pw, String correo){
       Usuario u = new Usuario(nom, ape, ci, pw, correo);
   }
    
    
}
