/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

/**
 *
 * @author Owner
 */

import fachada.Fachada;

public class PruebaPromedios {
    public static void main(String[] args) {
        Fachada fachada = new Fachada();

        System.out.println("Simple Juan:        " + fachada.promedioSimpleEstudiante("22222222"));
        System.out.println("Ponderado Juan:     " + fachada.promedioPonderadoEstudiante("22222222"));
        System.out.println("Simple Ana:         " + fachada.promedioSimpleEstudiante("33333333"));
        System.out.println("Programacion I:     " + fachada.promedioPorAsignatura("Programación I"));
        System.out.println("Base de Datos I:    " + fachada.promedioPorAsignatura("Base de Datos I"));
        System.out.println("Institucional:      " + fachada.promedioGeneralInstitucional());
        System.out.println("Cedula inexistente: " + fachada.promedioSimpleEstudiante("99999999"));
        System.out.println("Cedula vacia:       " + fachada.promedioSimpleEstudiante(""));
    }
}