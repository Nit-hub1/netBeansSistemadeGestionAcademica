/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidades;

/**
 *
 * @author Owner
 */
import java.util.ArrayList;

public class ColeccionResultados {

    // Se devuelve cuando no hay notas para calcular promedio (mayus x es constante)
    public static final double SIN_PROMEDIO = -1;

    private ArrayList<ResultadoCurso> listaResultados;

    public ColeccionResultados() {
        listaResultados = new ArrayList<ResultadoCurso>();
    }

    public void agregarResultado(ResultadoCurso res) {
        listaResultados.add(res);
    }

    public ArrayList<ResultadoCurso> getListaResultados() {
        return listaResultados;
    }

    public int cantidadResultados() {
        return listaResultados.size();
    }

    public ResultadoCurso obtenerResultado(int i) {
        return listaResultados.get(i);
    }

    public double calcularPromedioSimple() {
        if (listaResultados.isEmpty()) {
            return SIN_PROMEDIO;
        }
        double suma = 0;
        for (int i = 0; i < listaResultados.size(); i++) {
            suma = suma + listaResultados.get(i).getNotaFinal();
        }
        return suma / listaResultados.size();
    }

    // suma de (nota final x créditos) / suma de créditos (creo q eso significa ponderado)
    public double calcularPromedioPonderado() {
        double sumaNotasPorCreditos = 0;
        int sumaCreditos = 0;
        for (int i = 0; i < listaResultados.size(); i++) {
            ResultadoCurso res = listaResultados.get(i);
            int creditos = res.getAsignatura().getCredito();
            sumaNotasPorCreditos = sumaNotasPorCreditos + res.getNotaFinal() * creditos;
            sumaCreditos = sumaCreditos + creditos;
        }
        if (sumaCreditos == 0) {
            return SIN_PROMEDIO;
        }
        return sumaNotasPorCreditos / sumaCreditos;
    }
}
