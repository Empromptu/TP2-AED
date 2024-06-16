package aed;

import java.lang.reflect.Array;
import java.util.ArrayList;

import aed.Materia.CargoDocente;

public class SistemaSIU {

    private Trie<Carrera>  carreras;
    private Trie<Integer>  alumnos;

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias) {
        this.carreras = new Trie<>();
        this.alumnos = new Trie<>();
        
        for (InfoMateria infoMateria : infoMaterias) {
            Materia materia = new Materia<>();
            for (ParCarreraMateria parCarreraMateria : infoMateria.getParesCarreraMateria()) {
                String nombreCarrera = parCarreraMateria.getCarrera();
                String nombreMateria = parCarreraMateria.getNombreMateria();

                Carrera carreraExistente = carreras.buscar(nombreCarrera);
                if (carreraExistente == null) {
                    carreraExistente = new Carrera(nombreCarrera);
                    Object[] tupla = {carreraExistente, nombreMateria};
                    materia.agregarMaterias(tupla);
                    materia.setNombre(nombreMateria);
                    carreraExistente.setMaterias(nombreMateria, materia);
                    carreras.insertar(nombreCarrera, carreraExistente);
                }
                else{
                    String nuevo = "hola";
                    System.out.println(nuevo);
                }
            }
        }

        for (String alumno : libretasUniversitarias) {
            alumnos.insertar(alumno, 1);
        }
    }

    public void inscribir(String estudiante, String carrera, String materia) {
        Carrera c = carreras.buscar(carrera);
        if (c != null) {
            Materia m = c.getMaterias().buscar(materia);
            if (m != null) {
                m.agregarAlumno(estudiante);
            }
        }
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        Carrera c = carreras.buscar(carrera);
        Materia m = c.getMaterias().buscar(materia);
        m.agregarDocente(cargo);	    
    }

    public int[] plantelDocente(String materia, String carrera){
            Carrera c = carreras.buscar(carrera);
            Materia m = c.getMaterias().buscar(materia);
            return m.docentes();	        
    }

    public void cerrarMateria(String materia, String carrera){
    throw new UnsupportedOperationException("Método no implementado aún");	    
    }
    
    public int inscriptos(String materia, String carrera) {
        Carrera c = carreras.buscar(carrera);
        if (c != null) {
            Materia m = c.getMaterias().buscar(materia);
            if (m != null) {
                int totalAlumnos = m.cantidadAlumnos();
                return totalAlumnos;
            }
        }
        return 0;
    }


    public boolean excedeCupo(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] carreras(){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }

    public int materiasInscriptas(String estudiante){
        throw new UnsupportedOperationException("Método no implementado aún");	    
    }
}
