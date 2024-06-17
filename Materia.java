package aed;

import java.util.ArrayList;

public class Materia<T> {

    private String _nombre;
    private int[] _docentes;
    private ListaEnlazada<String> _alumnos;
    private ArrayList<T> _materias;

    public Materia() {
        this._docentes = new int[4];
        for (int i = 0; i < 4; i++) {
            _docentes[i] = 0;
        }
        _alumnos = new ListaEnlazada<>();
        this._materias = new ArrayList<T>();
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        _nombre = nombre;
    }

    public void agregarMaterias(T materia) { //tupla<memoria,nombre>
        _materias.add(materia);
    }

    public void agregarDocente(int posicion) {
        _docentes[posicion]++;
    }

    public void agregarAlumno(String lu) {
        _alumnos.agregarAdelante(lu);
    }

    public int cantidadAlumnos() {
        return _alumnos.longitud();
    }

    public int[] docentes() {
        return _docentes;
    }

    public ArrayList<T> getMaterias() {
        return _materias;
    }
}

