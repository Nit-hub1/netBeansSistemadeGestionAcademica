/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;

import logica.entidades.*;
import logica.servicios.*;
import java.util.HashMap;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioPersistenciaTESTMAP {
    private Map<String, Usuario> usuarios = new HashMap<>();

    public UsuarioPersistenciaTESTMAP() {
        guardarUser(new Estudiante("Juan", "Palo", "4", "1234"));
        guardarUser(new Docente("María", "López", "3", "pass"));
    }

    public Usuario buscarCi(String ci) {
        return usuarios.get(ci);
    }

    public void guardarUser(Usuario usuario) {
        usuarios.put(usuario.getCi(), usuario);
    }
}