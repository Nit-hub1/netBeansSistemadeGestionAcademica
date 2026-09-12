/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidades;

/**
 *
 * @author iamx2
 */
public class Docente extends Usuario{
    
    public Docente (String nom, String ape, String cedula, String pw){
        super(nom, ape, cedula, pw);
    }
    
    @Override
    public String getRol(){
        return "Docente";
    }
}
