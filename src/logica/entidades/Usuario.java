/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidades;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author iamx2
 */
public abstract class Usuario {
    private String nombre;
    private String apellido;
    private String ci;
    private String pwHash;
    
    public Usuario (String nom, String ape, String cedula, String pw){
        nombre = nom;
        apellido = ape;
        ci = cedula;
        setPwHash(pw);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pw) {
        if (pw != null && (pw.startsWith("$2a$") || pw.startsWith("$2b$"))) {
            pwHash = pw;
        } else {
            pwHash = BCrypt.hashpw(pw, BCrypt.gensalt());
        }
    }

    public boolean validarPw(String pw){
        return BCrypt.checkpw(pw, pwHash);
    }
    
    public abstract String getRol();
}
