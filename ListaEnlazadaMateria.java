package aed;

import java.util.*;

public class ListaEnlazadaMateria<T,String> implements Secuencia<T> { //¿que onda con implements secuencia?
    private Nodo _primero;
    private Nodo _ultimo; 
    private int cantNodos; //no se si lo necesito pero desp lo puedo borrar, asi se la longitud

    private class Nodo {
        T valor; //aca va a ir la posicion de memoria del trie (carreras,materia)
        String materia;  // aca va a ir el nombre de la materia
        Nodo sig;
        //Nodo ant;

        Nodo(T v, String m){
            valor = v;
            materia = m;
            }
    }

    public ListaEnlazada() {
        this._primero = this._ultimo = null;
        this.cantNodos = 0;
    }

    public int longitud() { //tampoco se si realmente la necesito, por si acaso.
        return cantNodos;
    }

    public void agregarAdelante(T elem, String m){ //ahora voy a tener que agregar la direccion de memoria + el nombre de la materia.
        if(_ultimo == null && _primero == null){
            Nodo nuevo = new Nodo(elem,m);
            nuevo.sig = null;
            //nuevo.ant = null; sigo sin entender si necesitaria una lista doblemente enlazada
            _primero = nuevo;
            _ultimo = nuevo;
            cantNodos++;   
        }
        else{
            Nodo nuevo = new Nodo(elem,m);
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
    //no creo necesitar el resto de operaciones?
    /*public void agregarAtras(T elem) {                                
        if(_ultimo == null && _primero == null){
            Nodo nuevo = new Nodo(elem);
            nuevo.sig = null;
            nuevo.ant = null;
            _primero = nuevo;
            _ultimo = nuevo;   
        }
        else{
            Nodo nuevo = new Nodo(elem);
            nuevo.ant = _ultimo;
            nuevo.sig = null;
            _ultimo.sig = nuevo;
            _ultimo = nuevo;
        }

    }

    public T obtener(int i) {
        int j = 0;
        T res = null;
        Nodo nuevo = new Nodo(_primero.valor);
        nuevo = _primero;
        nuevo.sig = _primero.sig;
        while(_primero != null){
            if (i == j){
                res = _primero.valor;
            }            
            _primero = _primero.sig;
            j = j + 1;
        }
        _primero = nuevo;
        return res;
    }

    public void eliminar(int i) {
        int j = 0;
        Nodo nuevo = new Nodo(_primero.valor);
        nuevo = _primero;
        nuevo.sig = _primero.sig;
        while(j <= i){
            if (i == 0){
                if(_primero.sig != null){
                    _primero = _primero.sig;
                    _primero.ant = null;
                    j = j + 1;
                }
                else{
                    _primero = _ultimo = null;
                    j = j + 1;
                }
            }
            else if (i == j){
                if(_primero.sig != null){
                    _primero.sig.ant = _primero.ant;
                    _primero.ant.sig = _primero.sig;
                    if(nuevo.valor.equals(_primero.ant.valor)){
                        j = j + 1;
                        _primero = _primero.ant;
                    }
                    else{
                        _primero = nuevo;
                        j = j + 1;
                    }
                }
                else {
                    _primero.ant.sig = null;
                    _ultimo = _primero.ant;
                    if(nuevo.valor.equals(_primero.ant.valor)){
                        _primero = _primero.ant;
                        j = j + 1;
                    }
                    else{
                        _primero = nuevo;
                        j = j + 1;
                    }
                }
            }
            else {           
                _primero = _primero.sig;
                j = j + 1;
            }
        }
    }

    public void modificarPosicion(int indice, T elem) {
        int i = 0;
        Nodo nuevo = new Nodo(_primero.valor);
        nuevo = _primero;
        nuevo.sig = _primero.sig;
        while (i <= indice){
            if(i == indice){
                if(i == 0){
                    _primero.valor = elem;
                    i = i + 1; 
                }
                else{
                    _primero.valor = elem;
                    _primero = nuevo;
                    i = i + 1; 
                }
            }
            else {
            _primero = _primero.sig;
            i = i + 1;                 
            }
        }
    }
        
    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> lista = new ListaEnlazada<>();
        Nodo puntero = new Nodo(null);

        if(_primero != null){
            puntero = _primero;
            
        }
        while(puntero != null){
            lista.agregarAtras(puntero.valor);
            puntero = puntero.sig;
        }
        return lista;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo puntero = lista._primero;
        while (puntero != null) {
            this.agregarAtras(puntero.valor);
            puntero = puntero.sig;
        }
    }
    
    @Override
    public String toString() {
        Nodo puntero = _primero;
        String res = "[";
        while (puntero != null) {
            if (puntero.sig == null) {
                res += puntero.valor.toString();
                break;
            }     
            res += puntero.valor.toString() + ", ";
            puntero = puntero.sig;
        }
        res += "]";
        return res;
    }

    private class ListaIterador implements Iterador<T> {
        private Nodo puntero;
        private boolean der;

        public ListaIterador() {
	        puntero = new Nodo(null);
            puntero.sig = _primero;
            der = true;
        }

        public boolean haySiguiente() {
            boolean res = false;
            if (!der) {
                res = puntero.valor != null;
            }
            else{
                res = puntero.sig != null;
            }
	        return res;
        }
        
        public boolean hayAnterior() {
            boolean res = false;
            if (!der) {
                res = puntero.ant != null;
            }
            else{
                res = puntero.valor != null;
            }
	        return res;
        }

        public T siguiente() {
            if (!der) {
                der = true;
            }
            else {
                puntero = puntero.sig;
            }   
	        return puntero.valor;
        }
        

        public T anterior() {
            if (der) {
                der = false;
            }
            else {
                puntero = puntero.ant;
            }
	        return puntero.valor;
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
 */