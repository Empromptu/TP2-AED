package aed;

import java.util.Iterator;

public class ListaEnlazada<T> implements Iterable<T> {

    private Nodo _primero;
    private Nodo _ultimo;
    private int cantNodos;

    private class Nodo {
        T valor;
        Nodo sig;

        Nodo(T v) {
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

    public void agregarAdelante(T elem) {
        if (_ultimo == null && _primero == null) {
            Nodo nuevo = new Nodo(elem);
            nuevo.sig = null;
            _primero = nuevo;
            _ultimo = nuevo;
            cantNodos++;
        } else {
            Nodo nuevo = new Nodo(elem);
            nuevo.sig = _primero;
            _primero = nuevo;
            cantNodos++;
        }
    }

    public T obtener() {
        return _primero.valor;
    }

    public void eliminar() {
        if (_primero.sig != null) {
            _primero = _primero.sig;
            cantNodos--;
        } else {
            _primero = _ultimo = null;
            cantNodos--;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListaEnlazadaIterator();
    }

    private class ListaEnlazadaIterator implements Iterator<T> {

        private Nodo actual = _primero;

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            T valor = actual.valor;
            actual = actual.sig;
            return valor;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}