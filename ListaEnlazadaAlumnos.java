package aed; //que carajo pasa? quiza no lo necesitamos porque es algo que usamos nosotros?

import java.util.*;

public class ListaEnlazadaAlumnos<String>  implements Secuencia<T> { //¿que onda con implements secuencia?
    private Nodo _primero;
    private Nodo _ultimo; 
    private int cantNodos; //no se si lo necesito pero desp lo puedo borrar, asi se la longitud

    private class Nodo {
        String alumno;  // aca va a ir el nombre de la materia
        Nodo sig;
        //Nodo ant;

        Nodo(String a){
            alumno = a;
            }
    }

    public ListaEnlazada() {
        this._primero = this._ultimo = null;
        this.cantNodos = 0;
    }

    public int longitud() { //tampoco se si realmente la necesito, por si acaso.
        return cantNodos;
    }

    public void agregarAdelante(String elem){ //ahora voy a tener que agregar la direccion de memoria + el nombre de la materia.
        if(_ultimo == null && _primero == null){
            Nodo nuevo = new Nodo(elem);
            nuevo.sig = null;
            //nuevo.ant = null; sigo sin entender si necesitaria una lista doblemente enlazada
            _primero = nuevo;
            _ultimo = nuevo;
            cantNodos++;   
        }
        else{
            Nodo nuevo = new Nodo(elem);
            nuevo.sig = _primero;
            //nuevo.ant = null;
            //_primero.ant = nuevo;
            _primero = nuevo;
            cantNodos++;   
        }
    }
    public void eliminar() { //solo me interesa eliminar el primer elemento siempre.
        if(_primero.sig != null){
            _primero.sig = _primero;
            cantNodos--;
        }
        else{
            _primero = _ultimo = null;
            cantNodos--;
        }
    }
}