/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fachada;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author iamx2
 */
public class SistemadeGestionAcademica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String contraseñaReal = "hola123";
        String contraseñaHash = BCrypt.hashpw(contraseñaReal, BCrypt.gensalt());
        System.out.println("Contraseña Real: "+contraseñaReal);
        System.out.println("Contraseña Hash: "+contraseñaHash);
        boolean validarContraseña = BCrypt.checkpw("hola12", contraseñaHash);
        if(validarContraseña){
            System.out.println("Contraseña Valida");
        }else{
            System.out.println("Contraseña Erronea");
        }
    }
    
}
