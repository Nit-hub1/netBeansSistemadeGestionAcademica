
package logica.servicios;

import repositorio.EstudiantePersistencia;
import logica.entidades.*;
import java.util.ArrayList;

public class GestionEstudiante {
    
    private EstudiantePersistencia estudiantePersistencia;

    public GestionEstudiante() {
        estudiantePersistencia = new EstudiantePersistencia();
    }
    
    public ArrayList<String[]> obtenerAsignaturasEstudiante(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return estudiantePersistencia.obtenerAsignaturasEstudiante(cedula);
    }
    
    public boolean darseDeBaja(int idInscripcion) {
        if (idInscripcion <= 0) {
            return false;
        }
        return estudiantePersistencia.darseDeBajaCurso(idInscripcion);
    }
    
    public int obtenerTotalCreditosAprobados(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return 0;
        }
        return estudiantePersistencia.obtenerTotalCreditosAprobados(cedula);
    }
}
