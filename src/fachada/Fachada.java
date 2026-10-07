/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;
import java.util.ArrayList;
import logica.servicios.*;
import logica.entidades.*;
/**
 *
 * @author iamx2
 */
public class Fachada {
    // Clase de estudiante
    private GestionUsuario servicioUsuario;
    // Clase de curso
    private GestionCurso servicioCurso;

    public Fachada(){
        servicioUsuario = new GestionUsuario();
        servicioCurso = new GestionCurso();
    }
    
    // USUARIOSSS
    
   public Usuario login(String ci, String pw){
       return servicioUsuario.verificarUsuario(ci, pw);
   }
   
   public boolean registrar(String nom, String ape, String ci, String pw, int tipoPerfil){
       return servicioUsuario.registrarUsuario(nom, ape, ci, pw, tipoPerfil);
   }
   
   // CURSOSSS
   
   public ArrayList<String> nombresCursosDocente (String ciDocente){
       ColeccionCursos coleccion = servicioCurso.traerCursoPorDocente(ciDocente);
       ArrayList<String> listaNombres = new ArrayList<>();
       
       for(int i=0; i < coleccion.cantidadCursos(); i++){
           Curso c = coleccion.obtenerCurso(i);
           String descripcion = c.getIdCurso() + " - " + c.getAsignatura().getNombre() + " (" + c.getPeriodo() + ")";
           listaNombres.add(descripcion);
       }
       return listaNombres;
   }
   
   // INSCRIPCIONESSSS
   
   public ArrayList<String[]> obtenerEstudiantePorCurso(int idCurso){
       ColeccionInscripciones inscripciones = servicioCurso.traerEstudiantesPorCurso(idCurso);
       ArrayList<String[]> filas = new ArrayList<>();
       
       for(int i=0; i<inscripciones.cantidadInscripciones(); i++){
           Inscripcion ins = inscripciones.obtenerInscripciones(i);
           Estudiante est = ins.getEstudiante();
           
           String[] fila = new String[]{
               est.getCi(),
               est.getNombre(),
               est.getApellido(),
               "", // CALIFICACION ACTUAL UNUUUUUU (acá había -)
               "" // OBSERVACIÓN UWUUUU (acá había guion)
           };
           filas.add(fila);
       }
       return filas;
   }
   
   // Recibe el comentario de la ventana y lo pasa a la lógica
   public boolean guardarCalificacion(String cedulaEstudiante, int idCurso, double nota, String comentarioTarea){
       return servicioCurso.registrarNotaEstudiante(cedulaEstudiante, idCurso, nota, comentarioTarea);
   }
   
   public ArrayList<String[]> obtenerHistorialCalificaciones (String cedula, int idCurso){
       ColeccionCalificaciones cals = servicioCurso.obtenerHistorialCalificaciones(cedula, idCurso);
       ArrayList<String[]> res = new ArrayList<>();
       
       if(cals != null && cals.getListaCalificaciones() != null){
           ArrayList<Calificacion> lista = cals.getListaCalificaciones();
           for(int i = 0; i < lista.size(); i++){
               Calificacion c = lista.get(i);
               res.add(new String[]{
                   c.getFechaNota(),
                   c.getTareaCalificacion(),
                   String.valueOf(c.getNota())
               });
           }
       }
       return res;
   }
   
    // PROMEDIOSSS B) (devuelven texto listo para mostrar en una ventana)
    public String promedioSimpleEstudiante(String cedula) {
        return formatearPromedio(servicioCurso.promedioSimpleEstudiante(cedula));
    }

    public String promedioPonderadoEstudiante(String cedula) {
        return formatearPromedio(servicioCurso.promedioPonderadoEstudiante(cedula));
    }

    public String promedioPorAsignatura(String nombreAsignatura) {
        return formatearPromedio(servicioCurso.promedioPorAsignatura(nombreAsignatura));
    }

    public String promedioGeneralInstitucional() {
        return formatearPromedio(servicioCurso.promedioGeneralInstitucional());
    }

    private String formatearPromedio(double promedio) {
        if (promedio == ColeccionResultados.SIN_PROMEDIO) {
            return "Sin notas";
        }
        return String.format("%.2f", promedio);
        // Pasa un double a un texto el %.2f con 2 cifras significativas (dsp de la coma)
    }
    
    // cuando tengas q mostrar usa algo tipo lblPromedio.setText(fachada.promedioPonderadoEstudiante(cedula));
}
