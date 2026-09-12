/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidades;

/**
 *
 * @author iamx2
 */
public class Usuario {
    private String nombre;
    private String apellido;
    private String cedula;
    private String rol;
    private String pwHash;
    private boolean activo;
    
    public Usuario(String cedula, String pwHash, String rol, String nombre, String apellido, boolean activo) {
        this.cedula = cedula;
        this.pwHash = pwHash;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.activo = activo;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivo(){
        return activo; // si esta activo = no "borre" al usuario
    }
    
    public void setActivo(boolean activo){
        this.activo=activo;
    }
}
