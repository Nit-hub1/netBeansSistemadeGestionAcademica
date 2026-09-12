/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;
import logica.servicios.*;
import logica.entidades.*;
/**
 *
 * @author iamx2
 */
public class Fachada {
    private GestionUsuario servicioUsuario;

    public Fachada(){
        servicioUsuario = new GestionUsuario();
    }
    
   public Usuario login(String ci, String pw){
       return servicioUsuario.verificarUsuario(ci, pw);
   }
   
   public boolean registrar(String nom, String ape, String ci, String pw, int tipoPerfil){
       return servicioUsuario.registrarUsuario(nom, ape, ci, pw, tipoPerfil);
   }
}
