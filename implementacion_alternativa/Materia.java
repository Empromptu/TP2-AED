package aed;

import java.util.ArrayList;

public class Materia {
    private TrieII _nombresAlt;
    private int[] _docentes;
    private TrieII _listaEstudiantes;
    private TrieII _carrerasAsociadas;

    public Materia(){
        this._nombresAlt = new TrieII();
        this._docentes = new int[4];
        this._listaEstudiantes = new TrieII();
        this._carrerasAsociadas = new TrieII();  
    }

    // El mapeo con el enum se hace en la operación antes de llamar al método
    public void agregarDocente(int docente) {
        this._docentes[docente] = this._docentes[docente] + 1;
    }

    public int[] docentes(){
        return this._docentes;
    }

    public void agregarNombre(String nombre){
        this._nombresAlt.insertar(nombre);
    }

    public String[] nombres(){
        return this._nombresAlt.listaDeClaves();
    }

    public void agregarCarrera(String carrera, ParCarreraMateria par){
        this._carrerasAsociadas.insertar(carrera, par);
    }

    public void inscribirEstudiante(String lu, ParCarreraMateria datos){
        this._listaEstudiantes.insertar(lu, datos);
    }

    public ParCarreraMateria datosDeEstudiante(String lu){
        ParCarreraMateria datos = (ParCarreraMateria) this._listaEstudiantes.obtenerValor(lu);
        return datos;
    }

    public int cantidadDeEstudiantes(){
        return this._listaEstudiantes.longitud();
    }

    public String[] listaDeEstudiantes(){
        return this._listaEstudiantes.listaDeClaves();
    }

    public ArrayList<ParCarreraMateria> carrerasAsociadas(){     
        ArrayList<ParCarreraMateria> pares = new ArrayList<>();
        String[] claves = this._carrerasAsociadas.listaDeClaves();
        for(String clave: claves){
            ParCarreraMateria parAux = (ParCarreraMateria) this._carrerasAsociadas.obtenerValor(clave);
            pares.add(parAux);
        }
        return pares;
    }

}
