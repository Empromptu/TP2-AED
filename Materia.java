package aed;

import java.util.ArrayList;

public class Materia {

    private int[] _docentes; // O(1)
    private ArrayList<String> _alumnos; // O(1)
    private ArrayList<Object[]> _materias; // O(1)

    // Constructor
    public Materia() { // O(1)
        this._docentes = new int[4]; // O(1)
        for (int i = 0; i < 4; i++) { // O(1)
            _docentes[i] = 0; // O(1)
        }
        this._alumnos = new ArrayList<String>(); // O(1)
        this._materias = new ArrayList<Object[]>(); // O(1)
    }

    public void agregarMaterias(Object[] materia) { // O(1)
        _materias.add(materia); // O(1)
    }

    public void agregarDocente(int posicion) { // O(1)
        _docentes[posicion]++; // O(1)
    }

    public void agregarAlumno(String lu) { // O(1)
        _alumnos.add(lu); // O(1)
    }

    public int cantidadAlumnos() { // O(1)
        return _alumnos.size(); // O(1)
    }

    public int[] docentes() { // O(1)
        return _docentes; // O(1)
    }

    public ArrayList<String> getAlumnos() { // O(1)
        return _alumnos; // O(1)
    }

    public ArrayList<Object[]> getMaterias() { // O(1)
        return _materias; // O(1)
    }

}

/////////////////////////////////////////////////////////////////
/* Invariante de Representacion */
/*
 * - No hay alumnos repetidos en el array "materia.getAlumnos()".
 * - No hay materias repetidas en el array "materia.getMaterias()"
 * - |materia.docentes| = 4
 * 
 */
