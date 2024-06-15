package main.java.aed;
//me lo cambio a main.java.aed??? ni idea
import aed.ListaEnlazada;
import aed.ListaEnlazadaMateria;

public class Materia {

    private Integer cantAlumnos;
    private Integer[] _docentes;
    private ListaEnlazada<String> _alumnos;
    private ListaEnlazada<T> _carreras;

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public Materia(){ //borre las variables me parece que al inicializar no son necesarias.
        this.cantAlumnos = 0; //me parece que no va a ser necesario porque en _alumnos.longitud() ya tira el mismo nro.
        this._docentes = new Integer[4];
        for(i = 0; i < 4; i++){
            _docentes[i] = 0;
        }
        ListaEnlazada<String> _alumnos = new ListaEnlazada<String>();
        ListaEnlazada<T> _carreras = new ListaEnlazada<T>(); //aca tengo que definir a T como tupla(memoria, nombreMateria)
    }

    //me esta faltando el mas importante que es agregar carrera  . . . 
    public void agregarCarrera(T[] tupla) {
        T[] informacion = new T[2];
        informacion[0] = tupla[0];
        informacion[1] = tupla[1]; //o ni siquiera, si yo estoy enviando una tupla tecnicamente podria hacer que 
        _carreras.agregarAdelante(informacion); //agregarAdelante(tupla) y listo, no? En ese caso deberia sacar T[] tupla
                                                // por T tupla nomas.
    } //no se si sera esto. 

    public void agregarDocente(CargoDocente docente) {
        Integer posicion = docente.ordinal();
        this.docentes[posicion] = this.docentes[posicion] +1;
    }
    
    public void agregarAlumno(String lu) {
        _alumnos.agregarAdelante(lu);
    }

    public int CantidadAlumnos() {
        return _alumnos.longitud();
    }

    public Integer[] Docentes() { //PlantelDocentes, no se que nombre poner porque no se
        return _docentes;         //como va a ser usado.
    }
}