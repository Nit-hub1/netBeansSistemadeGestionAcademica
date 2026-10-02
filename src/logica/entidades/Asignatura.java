/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidades;

/**
 *
 * @author iamx2
 */
public class Asignatura {
    private String nombre;
    private int credito;

    public Asignatura(String nom, int cred) {
        nombre = nom;
        credito = cred;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int cred) {
        credito = cred;
    }
    
    
}
