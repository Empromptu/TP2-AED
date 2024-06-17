package aed;

import java.util.ArrayList;

public class Trie<T> {
    private TrieNode<T> root;

    private class TrieNode<T> {
        private TrieNode<T>[] hijos;
        private T valor;

        @SuppressWarnings("unchecked")
        TrieNode() {
            hijos = (TrieNode<T>[]) new TrieNode[256];
            for (int i = 0; i < 256; i++) {
                hijos[i] = null;
            }
            this.valor = null;
        }
    }


    public Trie() {
        root = new TrieNode<>();
    }

    private boolean estaVacio(TrieNode<T> root){
        for (int i = 0; i < 256; i++)
            if (root.hijos[i] != null)
                return false;
        return true;
    }

    public void insertar(String palabra, T valor) {
        TrieNode<T> actual = root;
        for (int i = 0; i < palabra.length(); i++) {
            char ch = palabra.charAt(i);
            int index = (int) ch;
            if (actual.hijos[index] == null) {
                actual.hijos[index] = new TrieNode<>();
            }
            actual = actual.hijos[index];
        }
        actual.valor = valor;
    }

    public T buscar(String palabra) {
        TrieNode<T> actual = root;

        for (int i = 0; i < palabra.length(); i++) {
            char ch = palabra.charAt(i);
            int index = (int) ch;
            if (actual.hijos[index] == null) {
                return null;
            }
            actual = actual.hijos[index];
        }
        return actual.valor;
    }
    public String[] getClaves(){ 
        ArrayList<String> claves = new ArrayList<>(); // O(1)
        claves = this.obtenerClaves(); // O(|c|)
        int longitud = claves.size(); // O(1)
        String[] respuesta = new String[longitud]; // O(n), n = Cantidad de carreras -> O(|c|)?
        for (int i = 0; i < claves.size(); i++) { 
            respuesta[i] = claves.get(i);
        }
        return respuesta;
    }

    private ArrayList<String> obtenerClaves() { // O(|c|)
        ArrayList<String> claves = new ArrayList<>(); // O(1)
        obtenerClavesRec(root, "", claves); // O(|c|)
        return claves;
    }

    private void obtenerClavesRec(TrieNode<T> nodo, String prefijo, ArrayList<String> claves) { // O(|c|)
        if (nodo == null) {
            return;
        }
        if (nodo.valor != null) { 
            claves.add(prefijo); // O(1)
        }
        for (int i = 0; i < nodo.hijos.length; i++) { // O(|c|)
            if (nodo.hijos[i] != null) {
                char ch = (char) i;
                obtenerClavesRec(nodo.hijos[i], prefijo + ch, claves);
            }
        }
    }

    public void eliminar(String palabra) {
        eliminarRec(root, palabra, 0);
    }

    private void eliminarRec(TrieNode<T> nodo, String palabra, int index) {
        if (nodo == null) {
            return;
        }

        if (index == palabra.length()) {
            if (nodo.valor != null) {
                nodo.valor = null; // Eliminar la palabra
            }
            return;
        }

        char ch = palabra.charAt(index);
        int posicion = (int) ch;
        eliminarRec(nodo.hijos[posicion], palabra, index + 1);

        // Después de eliminar el nodo hijo, revisa si se debe eliminar este nodo
        if (nodo.hijos[posicion] != null && estaVacio(nodo.hijos[posicion]) && nodo.hijos[posicion].valor == null) {
            nodo.hijos[posicion] = null;
        }
    }

}