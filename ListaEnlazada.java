package aed;

import java.util.*;


public class ListaEnlazada<T> implements Secuencia<T>{ //¿que onda con implements secuencia?
    private Nodo _primero;      //si saco el implements se me jode valor y materia??
    private Nodo _ultimo; 
    private int cantNodos; 

    private class Nodo {
        T valor; 
        Nodo sig;
        //Nodo ant;
        Nodo(T v){
            valor = v; 
            }
    }

    public ListaEnlazada() {
        this._primero = this._ultimo = null;
        this.cantNodos = 0;
    }

    public int longitud() {
        return cantNodos;
    }
    
    public void agregarAdelante(T elem){ 
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

    public T obtener() {
        return _primero.valor;
    }
    
    public void eliminar() { //solo me interesa eliminar el primer elemento siempre.
        if(_primero.sig != null){
            _primero = _primero.sig ;
            cantNodos--;
        }
        else{
            _primero = _ultimo = null;
            cantNodos--;
        }
    }
}
